#!/bin/bash

ENGINE=${COMPOSE_ENGINE:-docker}
export CONTAINER_UID=${UID:-1000}

## These bind mount options improve performance for applications running
## inside of the container and accessing external files/directories
## Also, `:Z` makes everything work correctly with SELinux
export CONTAINER_VOLUME_OPTIONS=":Z,delegated"

## By default, use the `node_modules` directory directly from the frontend (The default for Linux hosts)
export CONTAINER_NODE_MODULES="${FRONTEND}/node_modules"

## Detect which Host OS this is running on (If OSTYPE does not exist, we're likely on Windows)
export HOST_OS=${OSTYPE:-windows_nt}

## OS Customizations Via Environment Variables
if [[ "${HOST_OS}" = linux* ]]; then
    ## Linux host, or it should be!
    printf "Detected LINUX host:\n"
fi

if [[ "${HOST_OS}" = windows*  ]]; then
    ## Windows, we believe...
    printf "Detected WINDOWS host:\n"
    ## Use a different directory for node_modules inside the container to avoid
    ## issues with modules compiled natively for Windows
    export CONTAINER_NODE_MODULES="./container_node_modules"
fi

if [[ "${HOST_OS}" = darwin* ]]; then
    ## MacOS, or something is VERY weird...
    printf "Detected DARWIN/MacOS host:\n"
    ## Use a different directory for node_modules inside the container to avoid
    ## issues with modules compiled natively for MacOS
    export CONTAINER_NODE_MODULES="./container_node_modules"
fi

## Check for existing Maven .m2 directory and create it if it does not exist
## This allows for caching of Maven artifacts outside of the container
if ! [[ -d ${HOME}/.m2/repository ]]; then
    mkdir -p ${HOME}/.m2/repository
fi

## Attempt to detect which container engine we should be using.
## By default, if docker is present it will be preferred
which docker-compose >> /dev/null
if [ $? -ne 0 ]; then
    which podman-compose >> /dev/null
    if [ $? -ne 0 ]; then
        printf "Docker/Podman Compose is not currently installed or is not in your PATH. Go HERE to install docker compose: https://docs.docker.com/compose/install/ or here for podman compose: https://github.com/containers/podman-compose\n\n"
    else
        ENGINE=podman
    fi
fi

if [ -e "${FRONTEND}" ] && [ -e "${BACKEND}" ]; then
    PWD_HOLDER=${PWD}

    ## Update git submodules for backend to retrieve the API Spec
    cd ${BACKEND}
    git submodule update --init
    cd ${PWD_HOLDER}

    ## Update git submodules for frontend to retrieve the API Spec
    cd ${FRONTEND}
    git submodule update --init
    cd ${PWD_HOLDER}

    ## Determine if we are using docker-compose or podman-compose
    if [ "${ENGINE}" = "podman" ]; then
        export CONTAINER_UID=0

        ## Podman does not support the `delegated` bind mount option
        ## so it gets removed here
        export CONTAINER_VOLUME_OPTIONS=":Z"

        ## Default to NOT BUILD the npm_plus_jre container
        BUILD_NPM_CONTAINER=false

        ## Check for existing npm_plus_jre container
        podman images | grep npm_plus_jre

        if [ "$?" == "0" ]; then ## IF the container image exists locally
            NPM_CONTAINER_BUILD_TIMESTAMP=$(podman inspect localhost/npm_plus_jre:latest --format "{{ .Created }}" | date +%s)

            ## If the container was built BEFORE the last modification time of the Dockerfile
            if [ ${NPM_CONTAINER_BUILD_TIMESTAMP} -le $(stat -c %Y Dockerfile.npm_plus_java_jre) ]; then
                BUILD_NPM_CONTAINER=true
            fi
        fi

        ## Only build the container if it does not exist, OR the Dockerfile has been modified
        ## since the last time the container was built
        if [ "${BUILD_NPM_CONTAINER}" == "true" ]; then
            podman-compose build
        fi
    else
        export POSTGRES_URI="postgresql://surveydb:5432"
    fi

    ## Execute the appropriate compose command for the current system
    "${ENGINE}-compose" pull --ignore-pull-failures -q
    "${ENGINE}-compose" up${EXTRA_COMPOSE_OPTIONS:""}
fi

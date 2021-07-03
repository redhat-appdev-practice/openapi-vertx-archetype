# OpenAPI And Vert.x Bootstrap Archetype (Work In Progress)

Following a [Contract-First API Development Approach](https://bit.ly/contract-first-api) can be daunting depending on the tools, languages, and frameworks you might like to use. This [Maven Archetype](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html) is a quick-start for building a new OpenAPI driven application in [Eclipse Vert.x](https://vertx.io/).

## Using This Archetype

```bash
git clone https://github.com/redhat-appdev-practice/openapi-vertx-archetype.git
cd openapi-vertx-archetype
mvn install
cd <workspace>
mvn archetype:generate -DarchetypeGroupId=com.redhat.runtimes \
                       -DarchetypeArtifactId=vertx-openapi-archetype \
                       -DarchetypeVersion=1.0-SNAPSHOT \
                       -Dpackage=com.redhat.runtimes \
                       -DgroupId=com.redhat.runtimes.vertx \
                       -DartifactId=vertx-cms \
                       -Dversion=0.0.1-SNAPSHOT \
                       -Dopenapi_app_database_library=jooq \
                       -Dopenapi_app_async_library=rxjava2 \
                       -Dopenapi_app_contract_uri=https://studio-ws.apicur.io/sharing/fb9d632f-6777-44c6-a22e-0a33d88a1d52?content=true \
                       -Dinteractive=false
```

## Options

* `package`
  * The Java package name to be used as the basis for code
* `groupId`
  * The Maven groupId for the generated project
* `artifactId`
  * The Maven artifactId for the generated project
* `version`
  * The Maven version for the generated project
* `openapi_app_contract_uri`
  * The Path or URL to an OpenAPI specification file in either YAML or JSON format
  * Examples:
    * https://petstore.swagger.io/v2/swagger.yaml
    * https://petstore.swagger.io/v2/swagger.json
    * /local/path/to/my/openapi.yml
* `openapi_app_database_library`
  * The Data Access library to be enabled
    * `jooq` - [jOOQ](https://jooq.org)
    * `hibernate` - [Hibernate Reactive](http://hibernate.org/reactive/)
* `openapi_app_async_library`=
  * The Async coordination API to enable
    * `vertx` - Used the built-in composable coordination
    * `rxjava2` - ReactiveX Streams  
    * `mutiny` - The SmallRye Mutiny API

## Current Status

- Able to generate a maven multi-module project
- Integrates OpenAPI Generator via Maven Plugin
- Generates JPA Entity classes from the OpenAPI contract
- Generates jOOQ Query DSL classes from the Hibernate/JPA entities
- Creates a *stub* Vert.x Main Verticle
- Allow to choose between jOOQ and Hibernate Reactive
- Allow to choose between basic Vert.x style callbacks, RxJava2, and Mutiny for async APIs

## Forthcoming Features

- Enable generating the Vert.x Web API Contract MainVerticle
- Add your idea to the GitHub issues!!!

## FAQ

- Why does it generate a Multi-module Maven project instead of a simple single Maven project?
  - This is a limitation of the jOOQ JPA generator and I found that I liked it this way anyhow.

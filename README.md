# OpenAPI And Vert.x Bootstrap Archetype

Following a [Contract-First API Development Approach](https://bit.ly/contract-first-api) can be daunting depending on the tools, languages, and frameworks you might like to use. This [Maven Archetype](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html) is a quick-start for building a new OpenAPI driven application in [Eclipse Vert.x](https://vertx.io/).

## Using This Archetype

```bash
git clone https://github.com/redhat-appdev-practice/openapi-vertx-archetype.git
cd openapi-vertx-archetype
mvn install
cd <workspace>
mvn archetype:generate -DarchetypeGroupId=com.zanclus.openapi.vertx \
                       -DarchetypeArtifactId=vertx-openapi-jooq-archetype \
                       -DarchetypeVersion=1.0-SNAPSHOT \
                       -Dpackage=com.redhat.runtimes \
                       -DgroupId=com.redhat.runtimes.vertx \
                       -DartifactId=vertx-cms \
                       -Dversion=0.0.1-SNAPSHOT \
                       -Dopenapi.app.useHibernateReactive=true \
                       -Dopenapi.app.useJavaObjectOrientedQuery=false \
                       -Dopenapi.app.contract.uri=https://studio-ws.apicur.io/sharing/fb9d632f-6777-44c6-a22e-0a33d88a1d52?content=true \
                       -Dinteractive=false
```
# OpenAPI And Vert.x Bootstrap Archetype (Work In Progress)

Following a [Contract-First API Development Approach](https://bit.ly/contract-first-api)
can be daunting depending on the tools, languages, and frameworks you might like to use.
This [Maven Archetype](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)
is a quick-start for building a new OpenAPI driven application in [Eclipse Vert.x](https://vertx.io/).

## Using This Archetype

```bash
mvn archetype:generate -DarchetypeGroupId=com.redhat.consulting \
                       -DarchetypeArtifactId=openapi-vertx-archetype \
                       -DarchetypeVersion=1.0.8 \
                       -Dpackage=com.redhat.runtimes \
                       -DgroupId=com.redhat.runtimes.vertx \
                       -DartifactId=vertx-petstore \
                       -Dversion=0.0.1-SNAPSHOT \
                       -Dopenapi_app_database_library=jooq \
                       -Dopenapi_app_async_library=rxjava2 \
                       -Dopenapi_app_contract_uri=https://petstore.swagger.io/v2/swagger.yaml \
                       -Dinteractive=false
```

The Archetype will create a maven multimodule project with a structure like this:

```
- Parent
  |
  +- models (POJOs for Hibernate/jOOQ ORMs)
  |
  +- data-access (optional jOOQ query DSL)
  |
  +- api (The Vert.x API stub where the business logic will be created)
```

### `models` Module: JPA Entities From OpenAPI Schemas

The models generated are from the **schemas** section of the OpenAPI Spec. The templates
used allow you to include annotations which will work with JPA. For example:

This OpenAPI Schema . . . 
```yaml
components:
  schemas:
    title: Todo
    description: 'A Todo list item'
    type: object
    x-java-class-annotations:
      - '@javax.persistence.Entity'                 ## JPA Annotations
      - '@javax.persistence.Table(name = "todos")'  ## JPA Annotations
    properties:
      title:
        type: string
      description:
        type: string
        x-java-field-annotations:
          - '@javax.persistence.Column(columnDefinition = "TEXT")'
      id:
        type: string
        format: uuid
        x-java-field-annotations:
          - '@javax.persistence.Id'
          - '@javax.persistence.Column(name = "id", updatable = false, nullable = false)'
          - '@javax.persistence.GeneratedValue(generator = "UUID")'
          - '@org.hibernate.annotations.GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")'
          - '@javax.persistence.Column(name = "id", updatable = false, nullable = false)'
      created:
        type: string
        format: datetime
        x-java-field-annotations:
          - '@javax.persistence.Column(name = "created", updatable = false, nullable = false)'
          - '@org.hibernate.annotations.CreationTimestamp'
      dueDate:
        type: string
        format: date
    example:
      title: My new todo
      description: This is a really cool todo item
      id: 81a9d97a-d42d-11eb-9edd-2bc577670ad3
```

Would be turned into this POJO:
```java
/**
 * A Todo list item
 */
@ApiModel(description = "A Todo list item")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-08-03T08:22:24.280350-04:00[America/New_York]")

@javax.persistence.Entity
@javax.persistence.Table(name = "todos")
public class Todo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @javax.persistence.Id
  @javax.persistence.GeneratedValue(generator = "UUID")
  @org.hibernate.annotations.GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @javax.persistence.Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  @javax.persistence.Column(columnDefinition = "TEXT")
  private String description;

  @JsonProperty("created")
  @org.hibernate.annotations.CreationTimestamp
  @javax.persistence.Column(name = "created", updatable = false, nullable = false)
  private OffsetDateTime created;

  @JsonProperty("dueDate")
  private OffsetDateTime dueDate;

  @JsonProperty("complete")
  private Boolean complete;

  @JsonProperty("author")
  private String author;

  public Todo id(UUID id) {
    this.id = id;
    return this;
  }

  // getters/setters/equals/hashcode methods 

}
```

These are provided for using custom [Hibernate OpenAPI Generator Templates](https://github.com/redhat-appdev-practice/hibernate-openapi-templates)

The templates provide the following extension attributes:

* `x-java-class-annotations`
  * These need to be at the same level as the `title` for a schema object and are added above the class definition
* `x-java-field-annotations`
  * These need to be added inside of the individual fields of the schema and will be added to the private field definition
* `x-java-getter-annotations`
  * These need to be added inside of the individual fields of the schema and will be added to the getter methods

### `data-access` Module: jOOQ Query DSL From JPA Entities

[jOOQ](https://www.jooq.org/) allows you to write queries for relation databases in a generated Java query DSL. In order
to create that DSL, it generally would analyze an existing database to formulate the DSL. That would require a DB-first
approach and we are trying to create a contract-first approach. To support this, we instead generate the Hibernate
models (Entities/POJOs) and use those models to generate a *fake* in-memory database and then jOOQ generates the DSL
from there. The documentation for this process is [HERE](https://www.jooq.org/doc/latest/manual/code-generation/codegen-jpa/).
Since this is complicated to set up, this Archetype does that setup for you using the POJOs generated from the `models`
module. You, as a developer using this archetype, just need to ensure that your `schemas` objects in the OpenAPI
specification have the `x-java-class-annotations` and `x-java-field-annotations` added to create proper JPA entities and
relationships.

With the jOOQ query DSL created, you can use it in Vert.x via [Jans Klingsporn](https://github.com/jklingsporn) 's
wonderful [implementation](https://github.com/jklingsporn/vertx-jooq) for Vert.x. See an example query below:

```java
ClassicQueryExecutor queryExecutor = new JDBCClassicGenericQueryExecutor(configuration,vertx);
Future<List<Todo>> updatedCustom = queryExecutor.execute(dslContext ->
                dslContext
                    .select()
                    .from(Tables.TODO)
                    .where(TODO.DUE_DATE.le(Date.now)))
                    .fetch()
                    .into(Todo.class);
```

### `api` Module: Vert.x Code Leveraging Hibernate Reactive or jOOQ

This is a work in progress and right now creates just a blank `MainVerticle` class

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
- Creates a **stub** Vert.x Main Verticle
- Allows you to choose between jOOQ and Hibernate Reactive
- Allows you to choose between basic Vert.x style callbacks, RxJava2, and Mutiny for async APIs

## Future Feature Ideas

- Add JKube Maven Plugin as an option to simplify K8s/OpenShift deployment
- Enable generating the Vert.x Web API Contract MainVerticle
- Generate Auth code for OpenAPI/OpenID/WebAuthn/FIDO2/Auth0
- Allow adding Tracing with OpenTracing
- Allow choosing logging framework
- Allow choosing metrics (Prometheus/Micrometer/etc...)
- Add your idea to the GitHub issues!!!

## FAQ

- Why does it generate a Multi-module Maven project instead of a simple single Maven project?
  - This is a limitation of the jOOQ JPA generator and I found that I liked it this way anyhow.

## Publishing To Maven Central

The build, testing, and publishing of this archetype is automated via GitHub
Actions using the Secrets defined on this repository. The user account is tied
to [Deven Phillips](https://github.com/infosec812/)' and used to publish via
the [JBoss Nexus Staging Repository](https://developer.jboss.org/docs/DOC-52892).

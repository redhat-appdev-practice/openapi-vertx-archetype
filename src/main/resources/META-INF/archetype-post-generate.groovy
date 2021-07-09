/* groovylint-disable CompileStatic */
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions

Properties properties = request.properties

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

// On UNIX-like systems, make the Maven wrapper script executable
String operatingSystem = System.getProperty("os.name").toLowerCase(Locale.ENGLISH)
if (!operatingSystem.contains("windows")) {
  Path mvnWrapper = Paths.get(projectPath.toString(), "mvnw")
  Files.setPosixFilePermissions(mvnWrapper, PosixFilePermissions.fromString("rwxrwxrwx"))
}

// Download/Copy OpenAPI file into project
Path openApiPath = Paths.get(request.outputDirectory, request.artifactId, "src", "main", "resources")
Path openApiFile = Paths.get(request.outputDirectory, request.artifactId, "src", "main", "resources", "openapi.yml")
openApiPath.toFile().mkdirs()
URL openApiSource = new URL(properties.getProperty('openapi_app_contract_uri'))
openApiPath.toFile() << openApiSource.openStream()


// Enable/Disable jOOQ DSL module
String dbLibrary = properties.getProperty("openapi_app_database_library", "jooq")

// If dbLibrary == 'hibernate', delete the jOOQ module dir and remove references from
// the parent pom
if (dbLibrary.contentEquals("hibernate")) {
  Path dataAccess = Paths.get(projectPath.toAbsolutePath().toString(), "modules", "data-access")
  dataAccess.toFile().deleteDir()
  def parentPom = Paths.get(projectPath.toAbsolutePath().toString(), "pom.xml").toFile()
  def sb = new StringBuilder();

  // Read parent pom and skip module line for `data-access`
  Scanner sc
  try {
    sc = new Scanner(parentPom)
    String currentLine
    while(sc.hasNext()){
      currentLine = sc.nextLine()
      if(currentLine.contains("<module>modules/data-access</module>")){
        continue
      }
      if (currentLine.trim().length()==0) {
        continue
      }
      sb.append(currentLine).append("\n")
    }
  } finally {
    sc.close()
  }

  // Empty the parent pom file
  PrintWriter pw = new PrintWriter(parentPom)
  pw.close()

  // Write the modified parent pom
  BufferedWriter writer = new BufferedWriter(new FileWriter(parentPom, true))
  writer.write(sb.toString())
  writer.close()
}
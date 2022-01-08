/* groovylint-disable CompileStatic */
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

Properties properties = request.properties

String dbLibrary = properties.getOrDefault("openapi_app_database_library", "jooq")

String operatingSystem = System.getProperty("os.name").toLowerCase(Locale.ENGLISH)

if (!operatingSystem.contains("windows")) {

  Path mvnWrapper = Paths.get(projectPath.toString(), "mvnw")

  Files.setPosixFilePermissions(mvnWrapper, PosixFilePermissions.fromString("rwxrwxrwx"))

}

// Download/Copy OpenAPI file into project
Path openApiPath = Paths.get(request.outputDirectory, request.artifactId, "modules", "api", "src", "main", "resources")
openApiPath.toFile().mkdirs()

String contractUri = properties.getProperty('openapi_app_contract_uri')
String apiFileName = 'openapi.yml'

URL openApiSource
if (contractUri.matches(/^[a-z]+:\/\/.*/)) {
  openApiSource = new URL(properties.getProperty('openapi_app_contract_uri'))
} else {
  Path openApiSourcePath = Paths.get(contractUri).toAbsolutePath()
  openApiSource = new URL("file://${openApiSourcePath.toString()}")
}
URLConnection conn = openApiSource.openConnection()

def contentType = conn.contentType

if (conn.contentType.contains('application/json') || contractUri.toLowerCase().endsWith('json')) {
  apiFileName = 'openapi.json'
}

Path openApiFile = Paths.get(openApiPath.toAbsolutePath().toString(), apiFileName)
openApiFile.toFile() << openApiSource.openStream()

if (contractUri.toLowerCase().endsWith('json')) {
  // Replace `openapi.yml` with `openapi.json` in models POM file

  def modelsPom = Paths.get(projectPath.toAbsolutePath().toString(), "modules", "models", "pom.xml").toFile()
  def sb = new StringBuilder();
  Scanner sc
  try {
    sc = new Scanner(modelsPom)
    String currentLine
    while(sc.hasNext()){
      currentLine = sc.nextLine()
      if(currentLine.contains("openapi.yml")) {
        def replacementLine = currentLine.replaceAll(/openapi\.yml/, 'openapi.json')
        sb.append(replacementLine).append("\n")
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


  // Empty the models pom file
  PrintWriter pw = new PrintWriter(modelsPom)
  pw.close()

  // Write the modified models pom
  BufferedWriter writer = new BufferedWriter(new FileWriter(modelsPom, true))
  writer.write(sb.toString())
  writer.close()
}

println "#########  DB LIBRARY: ${dbLibrary}"

def deleteModule = "";
switch (dbLibrary.toLowerCase()) {
  case "hibernate":
    deleteModule = 'modules/data-access'
    break
  case "jooq":
    deleteModule = 'modules/models'
    break
  default:
    deleteModule = 'modules/models'
    break

}

Path moduleToDelete = Paths.get(projectPath.toAbsolutePath().toString(), deleteModule)
moduleToDelete.toFile().deleteDir()

def parentPom = Paths.get(projectPath.toAbsolutePath().toString(), "pom.xml").toFile()
def sb = new StringBuilder();

// Read parent pom and skip module line for `data-access`
Scanner sc
try {
  sc = new Scanner(parentPom)
  String currentLine
  while(sc.hasNext()){
    currentLine = sc.nextLine()

    if(currentLine.trim().strip().contains(deleteModule)){
      continue
    } else if (currentLine.trim().length()==0) {
      continue
    } else if (!currentLine.endsWith("\n")) {
      sb.append(currentLine).append("\n")
    }
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
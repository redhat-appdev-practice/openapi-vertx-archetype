/* groovylint-disable CompileStatic */
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

Properties properties = request.properties

String dbLibrary = properties.getOrDefault("openapi_app_database_library", "jooq")

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
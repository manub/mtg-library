import net.manub.mtglibrary.{HealthCheck, MtgLibrary}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

object JettyLauncher {
  def main(args: Array[String]) {
    val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

    val server = new Server(port)
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addServlet(classOf[MtgLibrary], "/*")
    context.addServlet(classOf[HealthCheck], "/health-check/*")

    server.setHandler(context)

    server.start()
    server.join()
  }
}
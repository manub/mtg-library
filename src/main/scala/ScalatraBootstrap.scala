import net.manub.mtglibrary._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new MtgLibrary, "/*")
    context.mount(new HealthCheck, "/health-check/*")
  }
}

package net.manub.mtglibrary

import org.scalatest.FunSuite
import org.scalatra.test.scalatest.ScalatraSuite

class MtgLibrarySpec extends ScalatraSuite with FunSuite {

  addServlet(classOf[MtgLibrary], "/*")

  test("simple get") {
    get("/") {
      status should equal (200)
    }
  }
}


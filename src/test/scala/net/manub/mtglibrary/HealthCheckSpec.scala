package net.manub.mtglibrary

import org.scalatra.test.scalatest._
import org.scalatest.FunSuite
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.joda.time.DateTime

class HealthCheckSpec extends ScalatraSuite with FunSuite {

  implicit val formats = DefaultFormats

  addServlet(classOf[HealthCheck], "/health-check/*")

  test("simple get") {
    get("/health-check") {
      status should equal (200)

      val response = parse(body).extract[HealthCheckResponse]

      response.status should equal (HealthCheckStatus.SUCCESS.toString)

      //TODO: this needs a matcher
      assert(new DateTime(response.serverTimestamp).isBefore(DateTime.now))
    }
  }
}


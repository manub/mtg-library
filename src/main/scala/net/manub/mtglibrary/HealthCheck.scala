package net.manub.mtglibrary

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class HealthCheck extends MtgLibraryStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    HealthCheckResponse(HealthCheckStatus.SUCCESS.toString, System.currentTimeMillis())
  }

}

case class HealthCheckResponse(status: String, serverTimestamp: Long)

object HealthCheckStatus extends Enumeration {

  type HealthCheckStatus = Value
  val SUCCESS = Value("SUCCESS")

}
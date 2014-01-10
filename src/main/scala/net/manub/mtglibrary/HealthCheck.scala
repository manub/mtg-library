package net.manub.mtglibrary

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.mongodb.casbah.Imports._
import com.mongodb.ServerAddress

class HealthCheck extends MtgLibraryStack with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    val server = new ServerAddress("ds061928.mongolab.com", 61928)
    val credentials = MongoCredential("mtglibrary", "heroku_app20702501", "mtgpass".toCharArray)
    val mongoClient = MongoClient(server, List(credentials))
    val db = mongoClient("heroku_app20702501")

    val collection = db.getCollection("mtglibrary")

    HealthCheckResponse(HealthCheckStatus.SUCCESS.toString, db.name, collection.count(), System.currentTimeMillis())
  }

}

case class HealthCheckResponse(status: String, databaseName: String, numberOfElementStored: Long, serverTimestamp: Long)

object HealthCheckStatus extends Enumeration {

  type HealthCheckStatus = Value
  val SUCCESS = Value("SUCCESS")

}
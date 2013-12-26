import sbt._
import Keys._
import org.scalatra.sbt._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._
import com.typesafe.sbt.SbtStartScript

object MtgLibraryBuild extends Build {
  val Organization = "net.manub"
  val Name = "MTG Library"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.2"
  val ScalatraVersion = "2.2.2"

  lazy val project = Project(
    "mtg-library",
    file("."),
    settings = Seq(SbtStartScript.startScriptForClassesSettings: _*) ++ Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312" % "compile;container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar")),
        "org.scalatra" %% "scalatra-json" % "2.2.1",
        "org.json4s" %% "json4s-jackson" % "3.2.4",

        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.scalatra" %% "scalatra-scalatest" % "2.2.1" % "test"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) {
        base =>
          Seq(
            TemplateConfig(
              base / "webapp" / "WEB-INF" / "templates",
              Seq.empty, /* default imports should be added here */
              Seq(
                Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
              ), /* add extra bindings here */
              Some("templates")
            )
          )
      }
    )
  )
}

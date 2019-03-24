name := """play-mysql-audit"""

version := "2.7.x"

val gatlingVersion = "2.3.1"

inThisBuild(
  List(
    dependencyOverrides := Seq(
      "org.codehaus.plexus" % "plexus-utils" % "3.0.18",
      "com.google.code.findbugs" % "jsr305" % "3.0.1",
      "com.google.guava" % "guava" % "22.0"
    ),
scalaVersion := "2.12.8"
  )
)


lazy val GatlingTest = config("gatling") extend Test

lazy val root = (project in file(".")).enablePlugins(PlayJava, GatlingPlugin).configs(GatlingTest)
  .settings(
    inConfig(GatlingTest)(Defaults.testSettings)
  )
  .settings(
    scalaSource in GatlingTest := baseDirectory.value / "/gatling/simulation"
  )

libraryDependencies += guice
libraryDependencies += javaJpa
libraryDependencies += javaJdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.17.Final"
libraryDependencies += "io.dropwizard.metrics" % "metrics-core" % "3.2.1"
libraryDependencies += "com.palominolabs.http" % "url-builder" % "1.1.0"
libraryDependencies += "net.jodah" % "failsafe" % "1.0.3"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test
libraryDependencies += "io.gatling" % "gatling-test-framework" % gatlingVersion % Test

PlayKeys.externalizeResources := false

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

javacOptions ++= Seq(
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)

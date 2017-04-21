name := """restaurants"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  javaJdbc,
  cache,
  javaWs
)

fork in run := false
PlayKeys.externalizeResources := false
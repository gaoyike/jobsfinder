
organization := "Lift"

name := "jobfinder"

version := "2.5-M1"

scalaVersion := "2.9.1"

seq(webSettings :_*)

logLevel := Level.Info

EclipseKeys.withSource := true

transitiveClassifiers := Seq("sources")

scanDirectories := Nil

resolvers ++= Seq(
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Scala Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Scala" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Media4u101 Repository" at "http://www.media4u101.se:8081/nexus/content/repositories/releases/",
  "Media4u101 SNAPSHOT Repository" at "http://www.media4u101.se:8081/nexus/content/repositories/snapshots/"  
)

libraryDependencies ++= {
  val liftVersion = "2.5-M3" // Put the current/latest lift version here
  Seq(
    "mysql" % "mysql-connector-java" % "5.1.21",
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources(),
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-squeryl-record" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
    "com.damianhelme" %% "tbutils" % "0.1.0" % "compile",
    "net.liftweb" %% "lift-testkit" % liftVersion % "compile->default",
    "net.liftmodules" %% "lift-jquery-module" % (liftVersion+"-2.0"),
    "net.liftmodules" %% "fobo" % (liftVersion+"-0.7.2-SNAPSHOT") withSources(),
    "org.twitter4j" % "twitter4j-core" % "2.2.6",
    "net.liftweb" %% "lift-mongodb-record" % liftVersion
    )
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.3.v20111011" % "container",
  //"org.mortbay.jetty" % "jetty" % "6.1.22" % "container", // For Jetty 7
  "com.jolbox" % "bonecp" % "0.7.1.RELEASE" % "compile->default",
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "compile->default", // Logging
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
  "org.specs2" %% "specs2" % "1.6.1" % "test"
)



name := "signalfinder"
 
version := "1.0" 
      
lazy val `signalfinder` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.8.2",
  "org.apache.tika" % "tika-parsers" % "1.15",
  "org.apache.httpcomponents" % "fluent-hc" % "4.5",
  "commons-validator" % "commons-validator" % "1.6",
  "org.elasticsearch.client" % "rest" % "5.4.1",
  "joda-time" % "joda-time" % "2.9.9",
  "com.typesafe.play" %% "play-json" % "2.6.0",
  "org.scalaz" %% "scalaz-core" % "7.2.13",
  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % Test,
  "com.typesafe.akka" %% "akka-persistence" % "2.5.3",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.9"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )


fork in (Compile,run) := true
name := "spray-ssl-bug"

scalaVersion := "2.10.3"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.2.3",
	"io.spray" % "spray-can" % "1.2.0",
	"io.spray" % "spray-httpx" % "1.2.0"
)

fork in run := true

javaOptions in run ++= List(
  "-Djavax.net.ssl.trustStore=test.keystore",
  "-Djavax.net.ssl.trustStorePassword=janrain"
)
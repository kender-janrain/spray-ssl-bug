import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.io.IO
import akka.util.Timeout
import scala.util.control.NonFatal
import spray.can.Http
import spray.http.HttpResponse
import spray.httpx.RequestBuilding

object SpraySslBug extends App {
  implicit val actorSystem = ActorSystem("SpraySslBug")
  val io = IO(Http)

  import akka.pattern.ask
  import actorSystem.dispatcher
  implicit val askTimeout = Timeout(10.seconds)

  (io ? RequestBuilding.Get("https://localhost:8443")).mapTo[HttpResponse] map { response ⇒
    println(s"${response.status}")
    actorSystem.shutdown()
  } recover { case NonFatal(t) ⇒
    println(s"REQUEST FAILED: ${t.getClass.getSimpleName} - ${t.getMessage}")
    // note: NOT calling actorSystem.shutdown() here.
  }
}

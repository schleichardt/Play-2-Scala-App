import _root_.info.schleichardt.play2.basicauth._
import play.api.{Logger, Play, GlobalSettings}
import play.api.mvc.RequestHeader

object Global extends GlobalSettings {
  //export basicauthenabled=true
  lazy val basicAuthEnabled: Boolean = {
    val enabled = scala.util.Properties.envOrElse("basicauthenabled", false.toString).toBoolean
    Logger.info("basic auth enabled: " + enabled)
    enabled
  }
  val credentialSource = new CredentialsFromConfCheck

  override def onRouteRequest(request: RequestHeader) = {
    if (basicAuthEnabled) {
      requireBasicAuthentication(request, credentialSource) {super.onRouteRequest(request)}
    } else {
      super.onRouteRequest(request)
    }
  }
}

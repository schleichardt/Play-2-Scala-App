import _root_.info.schleichardt.play2.basicauth._
import play.api.{Logger, Play, GlobalSettings}
import play.api.mvc.RequestHeader

object Global extends GlobalSettings {
  //-Dbasic.auth.enabled=true
  lazy val basicAuthEnabled: Boolean = {
    val enabled = Play.current.configuration.getBoolean("basic.auth.enabled").getOrElse(false)
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

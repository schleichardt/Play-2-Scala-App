import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Play-2-Scala-App"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "info.schleichardt" %% "play-2-basic-auth" % "0.1-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += "schleichardts Github" at "http://schleichardt.github.com/jvmrepo/"
    )

}

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._
import scala_version_from_sbt_version.ScalaVersionFromSbtVersion

val sbt1 = "org.scala-sbt" % "sbt" % "1.12.9"

val scala2 = ScalaVersionFromSbtVersion(sbt1.revision)

val `scala-version-from-sbt-version` = projectMatrix
  .in(file("core"))
  .defaultAxes(VirtualAxis.jvm)
  .jvmPlatform(scalaVersions = Seq(scala2, "3.8.2"))
  .settings(
    Test / fork := true,
    evictionErrorLevel := Level.Warn,
    libraryDependencies += "org.scalatest" %% "scalatest-funspec" % "3.2.20" % Test,
    libraryDependencies ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq(
            sbt1 % Test,
            "org.scala-sbt" %% "main" % sbt1.revision % Provided
          )
        case "3" =>
          Seq(
            "org.scala-sbt" %% "librarymanagement-coursier" % sbtVersion.value % Provided,
            "org.scala-sbt" % "sbt" % sbtVersion.value % Test
          )
      }
    },
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq(
            "-Xsource:3",
            "-release:8",
          )
        case _ =>
          Nil
      }
    },
    scalacOptions ++= Seq(
      "-Werror",
      "-deprecation",
    ),
    pomExtra := (
      <developers>
        <developer>
          <id>xuwei-k</id>
          <name>Kenji Yoshida</name>
          <url>https://github.com/xuwei-k</url>
        </developer>
      </developers>
      <scm>
        <url>git@github.com:xuwei-k/scala-version-from-sbt-version.git</url>
        <connection>scm:git:git@github.com:xuwei-k/scala-version-from-sbt-version.git</connection>
      </scm>
    ),
    description := "get scala version from sbt version",
    organization := "com.github.xuwei-k",
    homepage := Some(url("https://github.com/xuwei-k/scala-version-from-sbt-version")),
    licenses := List(
      License.MIT
    ),
  )

val root = rootProject.autoAggregate.settings(
  scalaVersion := scalaVersion.value,
  publish / skip := true,
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    releaseStepCommandAndRemaining("publishSigned"),
    releaseStepCommandAndRemaining("sonaRelease"),
    setNextVersion,
    commitNextVersion,
    pushChanges
  )
)

package scala_version_from_sbt_version

import lmcoursier.internal.shaded.coursier

object ScalaVersionFromSbtVersion extends ScalaVersionFromSbtVersionCompat {
  def apply(sbtVersion: String): String = {
    val dependency = coursier.Dependency(
      coursier.Module(
        coursier.Organization(
          "org.scala-sbt"
        ),
        coursier.ModuleName(
          "sbt"
        ),
      ),
      VersionConstraint(sbtVersion)
    )
    if (sbtVersion.startsWith("2")) {
      reverseDependencyVersion(
        dependency,
        "org.scala-lang",
        "scala3-library_3",
      )
    } else {
      reverseDependencyVersion(
        dependency,
        "org.scala-lang",
        "scala-library",
      )
    }
  }

  private def reverseDependencyVersion(
    dependency: coursier.Dependency,
    targetGroupId: String,
    targetArtifactId: String
  ): String = {

    coursier.Fetch().addDependencies(dependency).runResult().detailedArtifacts0.map(_._1).collect {
      case x if (x.module.organization.value == targetGroupId) && (x.module.name.value == targetArtifactId) =>
        x.versionConstraint.asString
    } match {
      case Seq(x) =>
        x
      case Nil =>
        sys.error("not found")
      case xs =>
        sys.error(xs.toString)
    }
  }
}

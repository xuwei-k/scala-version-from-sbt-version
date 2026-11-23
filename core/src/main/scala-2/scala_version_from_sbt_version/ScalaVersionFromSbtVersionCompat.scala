package scala_version_from_sbt_version

import java.io.File
import lmcoursier.internal.shaded.coursier.Dependency
import lmcoursier.internal.shaded.coursier.Fetch.Result
import lmcoursier.internal.shaded.coursier.core.Publication
import lmcoursier.internal.shaded.coursier.util.Artifact

private[scala_version_from_sbt_version] object ScalaVersionFromSbtVersionCompat {
  def VersionConstraint(value: String): String = value

  final implicit class ResultOps(result: Result) {
    def detailedArtifacts0: Seq[(Dependency, Publication, Artifact, File)] = result.detailedArtifacts
  }

  final implicit class DependencyOps(dependency: Dependency) {
    def versionConstraint: String = dependency.version
  }

  final implicit class StringOps(s: String) {
    def asString: String = s
  }
}

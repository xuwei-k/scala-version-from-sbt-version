package scala_version_from_sbt_version

import java.io.File
import lmcoursier.internal.shaded.coursier.Dependency
import lmcoursier.internal.shaded.coursier.Fetch.Result
import lmcoursier.internal.shaded.coursier.core.Publication
import lmcoursier.internal.shaded.coursier.util.Artifact

trait ScalaVersionFromSbtVersionCompat { self: ScalaVersionFromSbtVersion.type =>
  def VersionConstraint(value: String): String = value

  protected final implicit class ResultOps(result: Result) {
    def detailedArtifacts0: Seq[(Dependency, Publication, Artifact, File)] = result.detailedArtifacts
  }

  protected final implicit class DependencyOps(dependency: Dependency) {
    def versionConstraint: String = dependency.version
  }

  protected final implicit class StringOps(s: String) {
    def asString: String = s
  }
}

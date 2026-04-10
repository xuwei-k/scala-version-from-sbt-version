package scala_version_from_sbt_version

trait ScalaVersionFromSbtVersionCompat { self: ScalaVersionFromSbtVersion.type =>
  def VersionConstraint(value: String): lmcoursier.internal.shaded.coursier.version.VersionConstraint =
    lmcoursier.internal.shaded.coursier.version.VersionConstraint(value)
}

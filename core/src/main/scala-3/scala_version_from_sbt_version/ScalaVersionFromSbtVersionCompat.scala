package scala_version_from_sbt_version

private[scala_version_from_sbt_version] object ScalaVersionFromSbtVersionCompat {
  def VersionConstraint(value: String): lmcoursier.internal.shaded.coursier.version.VersionConstraint =
    lmcoursier.internal.shaded.coursier.version.VersionConstraint(value)
}

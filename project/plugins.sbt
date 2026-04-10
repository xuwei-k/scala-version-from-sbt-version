addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.1")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.6")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.4.0")

Compile / unmanagedSourceDirectories ++= {
  val dir = baseDirectory.value.getParentFile / "core" / "src" / "main"

  Seq(dir / "scala", dir / "scala-3")
}

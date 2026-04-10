addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.1")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.6")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.4.0")

Compile / unmanagedSourceDirectories ++= {
  val dir = baseDirectory.value.getParentFile / "core" / "src" / "main"

  Seq(dir / "scala", dir / "scala-3")
}

Compile / sourceGenerators += Def.task {
  val objName = "MetaBuildInfo"
  val f = (Compile / sourceManaged).value / s"${objName}.scala"
  val src = s"""object ${objName} { def sbt2version: String = "${sbtVersion.value}" }"""
  IO.write(f, src)
  Seq(f)
}

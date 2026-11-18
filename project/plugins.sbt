addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.4.0")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.6.2")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.6.0")

libraryDependencies += "com.github.xuwei-k" %% "scala-version-from-sbt-version" % "0.1.0"

Compile / sourceGenerators += Def.task {
  val objName = "MetaBuildInfo"
  val f = (Compile / sourceManaged).value / s"${objName}.scala"
  val src = s"""object ${objName} { def sbt2version: String = "${sbtVersion.value}" }"""
  IO.write(f, src)
  Seq(f)
}

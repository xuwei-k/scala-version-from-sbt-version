# scala-version-from-sbt-version

`project/plugins.sbt`

```scala
libraryDependencies += "com.github.xuwei-k" %% "scala-version-from-sbt-version" % "version"
```

`build.sbt`

[example commit](https://github.com/xuwei-k/sbt-class-diagram/commit/bb5986a084524c0e51fe04b328fd010e9aaf59c0)


```diff
-crossScalaVersions += "3.8.2"
+def sbt2 = "2.0.0-RC11"
+
+crossScalaVersions += scala_version_from_sbt_version.ScalaVersionFromSbtVersion(sbt2)
 
 pluginCrossBuild / sbtVersion := {
   scalaBinaryVersion.value match {
     case "2.12" =>
       sbtVersion.value
     case _ =>
-      "2.0.0-RC11"
+      sbt2
   }
 }
```

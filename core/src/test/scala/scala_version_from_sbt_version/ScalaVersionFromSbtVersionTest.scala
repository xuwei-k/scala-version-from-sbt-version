package scala_version_from_sbt_version

import org.scalatest.funspec.AnyFunSpec

class ScalaVersionFromSbtVersionTest extends AnyFunSpec {
  it("sbt 1") {
    assert(ScalaVersionFromSbtVersion("1.12.9") == "2.13.18")
    assert(ScalaVersionFromSbtVersion("1.10.0") == "2.12.19")
    assert(ScalaVersionFromSbtVersion("1.9.0") == "2.12.18")
    assert(ScalaVersionFromSbtVersion("1.8.0") == "2.12.17")
  }

  it("sbt 2") {
    assert(ScalaVersionFromSbtVersion("2.0.0-RC11") == "3.8.2")
    assert(ScalaVersionFromSbtVersion("2.0.0-RC9") == "3.8.1")
    assert(ScalaVersionFromSbtVersion("2.0.0-RC8") == "3.7.4")
    assert(ScalaVersionFromSbtVersion("2.0.0-RC2") == "3.7.2")
  }
}

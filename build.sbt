name := "algorithms"

organization := "com.github.sadikovi"

scalaVersion := "2.10.5"

// check deprecation without manual restart
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

scalaSource in Compile := baseDirectory.value / "src"

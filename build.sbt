name := "SimpleBugReproduce"

version := "1.0"

scalaVersion := "2.10.6"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-streaming_2.10" % "1.6.1" % "provided",
    "com.typesafe" % "config" % "1.3.0"
  )
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.typesafe.config.**" -> "my_config.@1")
    .inLibrary("com.typesafe" % "config" % "1.3.0")
    .inProject
)

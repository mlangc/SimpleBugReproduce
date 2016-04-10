name := "SimpleBugReproduce"

version := "2.0"

scalaVersion := "2.11.8"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

libraryDependencies ++= {
  Seq(
    "org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided",
    "com.typesafe" % "config" % "1.3.0" % "provided"
  )
}
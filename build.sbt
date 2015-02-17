name := """functional-java"""

version := "1.0-SNAPSHOT"

javacOptions ++= Seq("-source", "1.8")

autoScalaLibrary := false //disable scala for this project

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.9",
  "org.slf4j" % "slf4j-simple" % "1.7.9",
  "com.google.guava" % "guava" % "18.0",
  "junit" % "junit" % "4.12" % Test,
   "com.novocode" % "junit-interface" % "0.11" % Test
)

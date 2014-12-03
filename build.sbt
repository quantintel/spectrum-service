name := "spectrum-service"

version := "0.0.1-SNAPSHOT"

lazy val `spectrumfinsvcs` = (project in file(".")).enablePlugins(PlayScala)

organization := "org.quantintel"

licenses     += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.11.4")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-unchecked", "-deprecation")

scalacOptions in (Compile, doc) ++= Seq("-unchecked", "-deprecation", "-diagrams", "-groups", "-implicits", "-skip-packages", "samples")

resolvers ++= Seq(
  "oss-sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )


libraryDependencies ++= Seq(
    "org.quantintel" % "spectrum-financial_2.11" % "0.0.1-SNAPSHOT")


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

/*

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>http://www.quantintel.com</url>
    <licenses>
      <license>
        <name>Apache 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:pauljbernard/scala-arm.git</url>
      <connection>scm:git:git@github.com:pauljbernard/quantintel/spectrum-service.git</connection>
    </scm>
    <developers>
      <developer>
        <id>pauljbernard</id>
        <name>Paul Bernard</name>
        <url>http://www.quantintel.com</url>
      </developer>
    </developers>)

    */
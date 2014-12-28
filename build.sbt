import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

//assemblySettings
seq(assemblySettings: _*)

seq(bintrayResolverSettings:_*)

lazy val `spectrumfinsvcs` = (project in file(".")).enablePlugins(PlayScala)

name := "spectrum-service"

version := "0.0.1-SNAPSHOT"

organization := "org.quantintel"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-unchecked", "-deprecation")

scalacOptions in (Compile, doc) ++= Seq("-unchecked", "-deprecation", "-diagrams", "-groups", "-implicits", "-skip-packages", "samples")

crossScalaVersions := Seq("2.11.4")

scalaVersion := "2.11.4"

resolvers ++=Seq("central" at "http://repo1.maven.org/maven2/",
  "Sonotype-public" at "https://oss.sonatype.org/content/repositories/public")


libraryDependencies ++= Seq(
  "org.json4s"                    %% "json4s-jackson"           % "3.2.10",
  "io.backchat.inflector"         %% "scala-inflector"          % "1.3.5",
  "commons-io"                    % "commons-io"                % "2.3" % "provided",
  "net.iharder"                   % "base64"                    % "2.3.8",
  "ch.qos.logback"                % "logback-classic"           % "1.0.13" % "provided",
  "org.rogach"                    %% "scallop"                  % "0.9.5",
  "junit"                         % "junit"                     % "4.11" % "test",
  "org.scalatest"                 %% "scalatest"                % "2.1.7" % "test",
  "com.wordnik"                   % "swagger-codegen_2.11.1"    % "2.0.17",
  "com.wordnik"                   % "swagger-play2_2.11"        % "1.3.11" ,
  "com.wordnik"                   % "swagger-play2-utils_2.11"  % "1.3.11",
  "io.dropwizard"                 % "dropwizard-metrics"        % "0.7.1",
  "org.clapper"                   %% "grizzled-slf4j"           % "1.0.2"
)

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

libraryDependencies ++= Seq(
    "org.quantintel" % "spectrum-financial_2.11" % "0.0.1-SNAPSHOT")


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )



packageOptions <+= (name, version, organization) map {
  (title, version, vendor) =>
    Package.ManifestAttributes(
      "Created-By" -> "Simple Build Tool",
      "Built-By" -> System.getProperty("user.name"),
      "Build-Jdk" -> System.getProperty("java.version"),
      "Specification-Title" -> title,
      "Specification-Version" -> version,
      "Specification-Vendor" -> vendor,
      "Implementation-Title" -> title,
      "Implementation-Version" -> version,
      "Implementation-Vendor-Id" -> vendor,
      "Implementation-Vendor" -> vendor
    )
}


publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

artifact in (Compile, assembly) ~= { art =>
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

homepage := Some(new URL("http://www.quantintel.org"))

parallelExecution in Test := false

startYear := Some(2014)

licenses += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

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


mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case x if Assembly.isConfigFile(x) =>
      MergeStrategy.concat
    case PathList(ps@_*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
      MergeStrategy.rename
    case PathList("META-INF", xs@_*) =>
      (xs map { _.toLowerCase}) match {
        case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
          MergeStrategy.discard
        case ps@(x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
          MergeStrategy.discard
        case "plexus" :: xs =>
          MergeStrategy.discard
        case "services" :: xs =>
          MergeStrategy.filterDistinctLines
        case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
          MergeStrategy.filterDistinctLines
        case _ => MergeStrategy.deduplicate
      }
    case _ => MergeStrategy.first // overrides the default fallback MergeStrategy of deduplicate
  }
}

assemblySettings
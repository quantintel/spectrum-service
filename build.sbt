import AssemblyKeys._
import sbt.Keys._

assemblySettings

name := "spectrum-service"

version := "0.0.1-SNAPSHOT"

organization := "org.quantintel"

licenses  += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

lazy val `spectrumfinsvcs` = (project in file(".")).enablePlugins(PlayScala)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-unchecked", "-deprecation")

scalacOptions in (Compile, doc) ++= Seq("-unchecked", "-deprecation", "-diagrams", "-groups", "-implicits", "-skip-packages", "samples")

scalaVersion := "2.11.4"

crossScalaVersions := Seq("2.11.4")

resolvers ++= Seq(
  "Sonotype-public" at "https://oss.sonatype.org/content/repositories/public",
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "JBoss Repository" at "https://repository.jboss.org/nexus/content/groups/public/",
  "snapshots-repo" at "http://scala-tools.org/repo-snapshots",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "apache-releases" at "http://repository.apache.org/content/repositories/releases/",
  "oss-sonatype-releases" at "http://oss.sonatype.org/content/repositories/releases",
  "central" at "http://repo1.maven.org/maven2/",
  "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "scala-tools.org" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
  "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"
)

libraryDependencies ++= Seq(
  jdbc ,
  anorm ,
  cache ,
  ws,
  filters,
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
  "org.clapper"                   %% "grizzled-slf4j"           % "1.0.2",
  "org.jboss.spec.javax.transaction" % "jboss-transaction-api_1.1_spec" % "1.0.1.Final"
      excludeAll(ExclusionRule(organization = "com.google.guava")),
  "org.infinispan"        % "infinispan-embedded"   % "7.0.3.Final"
      excludeAll(ExclusionRule(organization = "com.google.guava")),
  "org.quantintel" % "spectrum-financial_2.11" % "0.0.1-SNAPSHOT"
)


artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { mergeStrategy => {
  case entry => {
    val strategy = mergeStrategy(entry)
    if (strategy == MergeStrategy.deduplicate) MergeStrategy.first
    else strategy
  }
}}

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
publishArtifact in (Compile, packageBin):= false
publishArtifact in (Compile, packageDoc):= false
publishArtifact in (Compile, packageSrc):= false

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
      <url>git@github.com:pauljbernard/quantintel/spectrum-service.git</url>
      <connection>scm:git:git@github.com:pauljbernard/quantintel/spectrum-service.git</connection>
    </scm>
    <developers>
      <developer>
        <id>pauljbernard</id>
        <name>Paul Bernard</name>
        <url>http://www.quantintel.com</url>
      </developer>
    </developers>)



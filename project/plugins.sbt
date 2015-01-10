logLevel := Level.Error

//resolvers += "sbt-assembly-resolver-0" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"

//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

//resolvers += Resolver.url("bintray-sbt-plugin-releases",
//  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")

//addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.6")

//addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.1")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.3")

// assembly plugin
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.2")
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")
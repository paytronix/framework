import Dependencies._

organization in ThisBuild          := "net.liftweb"

version in ThisBuild :=  "2.6.3"

homepage in ThisBuild              := Some(url("http://www.liftweb.net"))

licenses in ThisBuild              += ("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

startYear in ThisBuild             := Some(2006)

organizationName in ThisBuild      := "WorldWide Conferencing, LLC"

scalaVersion in ThisBuild          := "2.12.10"

credentials in ThisBuild += Credentials(Path.userHome / ".sbt" / ".credentials")

libraryDependencies in ThisBuild ++= Seq(specs2, specs2Common, specs2Matcher, specs2MatcherExtra, scalacheck, scalatest)

// Settings for Sonatype compliance
pomIncludeRepository in ThisBuild  := { _ => false }

scmInfo in ThisBuild               := Some(ScmInfo(url("https://github.com/lift/framework"), "scm:git:https://github.com/lift/framework.git"))

pomExtra in ThisBuild              :=  Developers.toXml

initialize <<= (name, version, scalaVersion) apply printLogo

resolvers  in ThisBuild           ++= Seq(
  "snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
)

val LocalMaven = "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
val PaytronixNexus = "https://nexus.corp.paytronix.com/nexus/"
val PaytronixSnapshots = "paytronix-snapshots" at PaytronixNexus + "content/repositories/snapshots"
val PaytronixReleases = "paytronix-releases" at PaytronixNexus + "content/repositories/releases"

resolvers in ThisBuild            ++= Seq(LocalMaven, PaytronixSnapshots, PaytronixReleases)
resolvers in ThisBuild            ++= Seq("maven-central" at "https://nexus.corp.paytronix.com/nexus/repositories/central/content/")
resolvers in ThisBuild             += Resolver.mavenLocal

publishTo in ThisBuild := {
    if (isSnapshot.value) Some(PaytronixSnapshots)
    else                  Some(PaytronixReleases)
}

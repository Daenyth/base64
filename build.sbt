import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "base64 root project"

lazy val root = project.in(file("."))
  .aggregate(base64.jvm,base64.js)
  .settings(commonSettings)
  .settings(skip in publish := true)

lazy val base64 = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .settings(commonSettings)
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.6" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    name := "Base64",
    organization := "com.github.marklister",
    version := "0.2.5",
    homepage := Some(url("https://github.com/marklister/base64")),
    startYear := Some(2013),
    description := "Small, idiomatic base64 implementation",
    licenses += ("BSD Simplified", url("http://opensource.org/licenses/BSD-SIMPLIFIED")),

    pomExtra := (
      <scm>
        <url>git@github.com:marklister/base64.git</url>
        <connection>scm:git:git@github.com:marklister/base64.git</connection>
      </scm>
        <developers>
          <developer>
            <id>marklister</id>
            <name>Mark Lister</name>
            <url>https://github.com/marklister</url>
          </developer>
        </developers>),
    scalacOptions in(Compile, doc) ++= Opts.doc.title("Base64"),
    scalacOptions in(Compile, doc) ++= Seq("-implicits")
  )

  .jvmSettings(
    initialCommands in console := """import com.github.marklister.base64.Base64._"""
  )

val commonSettings = Seq(
  scalaVersion := "2.12.8",
  crossScalaVersions := Seq("2.12.8", "2.11.12", "2.13.0-M5")
)
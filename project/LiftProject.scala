import sbt._
import Keys._
//import com.github.siasia._
//import PluginKeys._
import WebPlugin._
import WebappPlugin._

object LiftProjectBuild extends Build {
    override lazy val settings = super.settings ++ buildSettings

    lazy val buildSettings = Seq(
        organization := "net.liftweb",
        version      := "0.0.1",
        scalaVersion := "2.9.2")

    def yourWebSettings = webSettings ++ Seq(
        // If you are use jrebel
        scanDirectories in Compile := Nil,
        port in container.Configuration := 8080)

    lazy val liftQuickstart = Project(
        id = "lift-quickstart",
        base = file("."),
        settings = defaultSettings ++ yourWebSettings)

    // https://github.com/siasia/xsbt-web-plugin/wiki
    //seq(com.github.siasia.WebPlugin.webSettings :_*)
    seq(webSettings :_*)

    lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
        name := "lift-quickstart",
        resolvers ++= Seq(
            "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases", 
            "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
            "snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
            "releases"      at "http://oss.sonatype.org/content/repositories/releases")

        // https://github.com/siasia/xsbt-web-plugin/wiki
        /*override def libraryDependencies = sbtVersion(v => v match {
            case "0.11.0" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.0-0.2.8"
            case "0.11.1" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.1-0.2.10"
            case "0.11.2" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.2-0.2.11"
            case "0.11.3" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.3-0.2.11.1"
            case x if (x.startsWith("0.12")) => "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"
        }) ++ super.LibraryDependencies*/

        libraryDependencies ++= {sbtVersion(v => v match {
            case "0.11.0" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.0-0.2.8"
            case "0.11.1" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.1-0.2.10"
            case "0.11.2" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.2-0.2.11"
            case "0.11.3" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.3-0.2.11.1"
            case x if (x.startsWith("0.12")) => "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"
        })},

        libraryDependencies ++= {
            val liftVersion = "2.5-M1"
            Seq(
            "net.liftweb"       %% "lift-webkit"        % liftVersion.value.toString        % "compile",
            "net.liftweb"       %% "lift-mapper"        % liftVersion.value.toString        % "compile",
            "net.liftmodules"   %% "lift-jquery-module" % (liftVersion + "-1.0"),
            "org.eclipse.jetty" % "jetty-webapp"        % "8.1.7.v20120910"  % "container,test",
            "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
            "ch.qos.logback"    % "logback-classic"     % "1.0.6",
            "org.specs2"        %% "specs2"             % "1.12.1"           % "test",
            "com.h2database"    % "h2"                  % "1.3.167"
            )
        },

        // compile options
        scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
        javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

        // show full stack traces
        //testOptions in Test += Tests.Argument("-oF")
    )
}


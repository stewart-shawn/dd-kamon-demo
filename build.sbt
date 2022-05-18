
name := """demo"""
organization := "com.demo"

version := "1.0-SNAPSHOT"

val datadogPackageName = "com.datadoghq"
val datadogAgentName = "dd-java-agent"
val datadogAgentVersion = "0.100.0"
val ddJavaAgent = datadogPackageName % datadogAgentName % datadogAgentVersion

val jvmArgs = s"""
                 |addJava "-javaagent:$${app_home}/../lib/$datadogPackageName.$datadogAgentName-$datadogAgentVersion.jar"
                 |addJava "-XX:+FlightRecorder"
                 |addJava "-XX:FlightRecorderOptions=stackdepth=256"
                 |addJava "-Ddd.profiling.enabled=true"
                 |""".stripMargin

val kamonVersion = "2.5.1"
val kamonCore    = "io.kamon" %% "kamon-core" % kamonVersion
val kamonDatadog   = "io.kamon" %% "kamon-datadog" % kamonVersion
val kamonPrometheus = "io.kamon" %% "kamon-prometheus" % kamonVersion
val kamonInstrumentAkka = "io.kamon" %% "kamon-akka" % kamonVersion
val kamonInstrumentHttp = "io.kamon" %% "kamon-akka-http" % kamonVersion
val kamonInstrumentIo = "io.kamon" %% "kamon-cats-io" % kamonVersion
val kamonInstrumentExecutors = "io.kamon" %% "kamon-executors" % kamonVersion
val kamonInstrumentJdbc = "io.kamon" %% "kamon-jdbc" % kamonVersion
val kamonInstrumentLogback = "io.kamon" %% "kamon-logback" % kamonVersion
val kamonInstrumentMongo = "io.kamon" %% "kamon-mongo" % kamonVersion
val kamonInstrumentOkHttp = "io.kamon" %% "kamon-okhttp" % kamonVersion
val kamonInstrumentPlay = "io.kamon" %% "kamon-play" % kamonVersion
val kamonInstrumentScalaFuture = "io.kamon" %% "kamon-scala-future" % kamonVersion
val kamonInstrumentJvm = "io.kamon" %% "kamon-system-metrics" % kamonVersion

val kamonDependencies = Seq(
  kamonCore,
  kamonDatadog,
  kamonPrometheus,
  kamonInstrumentAkka,
  kamonInstrumentHttp,
  kamonInstrumentIo,
  kamonInstrumentExecutors,
  kamonInstrumentJdbc,
  kamonInstrumentLogback,
  kamonInstrumentMongo,
  kamonInstrumentOkHttp,
  kamonInstrumentPlay,
  kamonInstrumentScalaFuture,
  kamonInstrumentJvm
)

lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala, JavaAgent)
    .settings(
      bashScriptExtraDefines += jvmArgs,
      libraryDependencies ++= kamonDependencies :+ ddJavaAgent
    )

scalaVersion := "2.12.15"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.demo.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.demo.binders._"

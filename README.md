# dd-kamon-demo

When both the Datadog and Kanela agent are enabled, and the stage script is run, we are seeing various java.lang.ClassCastExceptions. 
</br></br>
Ex:
>An error occurred while trying to apply an advisor: java.lang.ClassCastException: class akka.actor.ActorCell cannot be cast to class kamon.instrumentation.akka.instrumentations.HasActorMonitor (akka.actor.ActorCell and kamon.instrumentation.akka.instrumentations.HasActorMonitor are in unnamed module of loader 'app')

### Steps to reproduce
1. <code>sbt stage</code>
11. <code>./target/universal/stage/bin/demo</code>
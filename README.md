# Quasar Gradle Template

A [Parallel Universe Quasar](http://www.paralleluniverse.co/quasar/) Gradle template for standalone Java 1.7+ applications..

This is a port of the Quasar Maven Archetype at http://github.com/puniverse/quasar-maven-archetype.

## Getting started

Just edit `gradle/user-props.gradle`. You might want to add JVM arguments and system properties, then feel free to play with the code or give it a try as it stands.

Currently 2 instrumentation methods can be chosen through the `method` property: `agent` and `aot`. To run them:

```
./gradlew -Pmethod=agent run
./gradlew -Pmethod=agent test
./gradlew -Pmethod=aot clean run
./gradlew -Pmethod=aot test
```

# micronaut-logbook-example

Created project using mn cli
```shell script
mn create-app --jdk=14 --test=spock micronaut-logbook-example
```
Create a `HelloController` like the (micronaut example)[https://github.com/micronaut-projects/micronaut-examples/blob/master/hello-world-java/src/main/java/example/HelloController.java]

Create `HelloControllerSpec` for the hello endpoint like the one in the groovy (example)[https://github.com/micronaut-projects/micronaut-examples/tree/master/hello-world-groovy/src/test/groovy/example]
Create the supporting `HelloClient` class

Add logbook dependency 
```
  implementation(platform("org.zalando:logbook-bom:2.1.2"))

  implementation("org.zalando:logbook-core")
  implementation("org.zalando:logbook-json")
  implementation("org.zalando:logbook-logstash")
  implementation("org.zalando:logbook-netty")
```

The logbook (documentation)[https://github.com/zalando/logbook] directs to the (micronaut netty pipeline)[https://docs.micronaut.io/snapshot/guide/index.html#nettyPipeline] code snippet for reference.

Create the class `LogbookPipelineCustomizer`

Remove `@Requires(beans = Logbook.class)` and update the constructor as follows

```java
    public LogbookPipelineCustomizer() {
        this.logbook = Logbook.create();
    }
```


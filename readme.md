# Soyuz Loge
This **5 minutes** library will make your logs cleaner and easier to read / navigate.
This is not one more logging library - we just wrap `slf4j` api and make it more strict.

## 10 seconds description
This Java library will split your events from params. This will make your logs easier to read / write / navitage

## Usual approach to log data
Here is most commonly used style to log process:
```
My service is sending mail "Hello world" to earth@gmail.com
```
## Problems
Why we think that this log entry is bad?
1. It's hard to split event (`sending mail`) from params (subject - `Hello world`, recipient - `earch@gmail.com`)
2. It will be pretty hard to find this row in logs
3. It's not possible to parse
4. It requires us to construct correct English sentence

## Suggestion
1. Let's split event and params
2. (optional) Let's write event in a short way

```
mail.send: {subject=hello world, recipient=earch@gmail.com}
```

> Please note that we use short (technical) way to describe event. Instead of writing human readable phrase (like "We are sending mail") we use short event name ("mail.send")
> To construct your best event name we suggest: you to 
> 1. form event names same as package names - from most common to most specific. 
> 2. don't use spaces
> 3. split words with upper case / `-` / `_` (e.g. `user.register.e.notUnique`)
> 4. split parts with `.`

## Java implementation
We pass params as a map. You need a simple way to create maps. In examples below we will use [to.map](https://github.com/thedocs-io/soyuz-is-to) but you are free to use any other convenient way to create maps (e.g. [Guava ImmutableMap.of](http://google.github.io/guava/releases/snapshot/api/docs/com/google/common/collect/ImmutableMap.html)).

### Maven
```
<dependency>
    <groupId>io.thedocs</groupId>
    <artifactId>soyuz-loge</artifactId>
    <version>1.1</version>
</dependency>
```

### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'io.thedocs:soyuz-loge:1.1'
}
```

### Examples

> Always use `loge` as variable name

> This example depends on [soyuz-is-to](https://github.com/thedocs-io/soyuz-is-to)

```java
package io.thedocs.soyuz.log;

import io.belov.soyuz.utils.to;

public class LoggerEventsExamples {

    private static final LoggerEvents loge = LoggerEvents.getInstance(LoggerEventsExamples.class);

    public static void main(String[] args) {
        Exception e = new IllegalStateException();

        loge.warn("user.register.e.notUnique", to.map("mail", "hello@gmail.com")); //user.register.e.notUnique: {mail=hello@gmail.com}
        loge.debug("user.login", to.map("mail", "hello@gmail.com")); //user.login: {mail=hello@gmail.com}
        loge.info("task.created", to.map("user", "hello@gmail.com", "label", "Improve documentation", "type", "todo")); //task.created: {label=Improve documentation, type=todo, user=hello@gmail.com}
        loge.error("task.process.e", to.map("id", 123, "type", "todo"), e); //task.process.e: {id=123, type=todo}
    }

}
```
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

## Java implementation
We pass params as a map. You need a simple way to create maps. In examples below we will use [to.map](https://github.com/fedotxxl/soyuz-is-to) but you are free to use any other convinient way to create maps (e.g. [Guava ImmutableMap.of](http://google.github.io/guava/releases/snapshot/api/docs/com/google/common/collect/ImmutableMap.html)).

### Maven

### Gradle

### Examples


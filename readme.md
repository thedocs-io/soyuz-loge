# Soyuz Loge
This **5 minutes** library will make your logs cleaner and easier to read / navigate.
This is not one more logging library - we just wrap `slf4j` api and make it more strict.

## 10 seconds description
This Java library will split your events from params. This will make your logs easier to read / write / navitage

## Idea
We think that any log entry may be splitted into two parts:
1. What happened (event)?
2. With whoom (params)?

Here is simple log row:
```
My service is sending mail "Hello world" to earth@gmail.com
```
We can read it and get useful info:
1. Event: sending mail
2. Params: subject - "Hello world", recipient - earch@gmail.com

### Problems
Why we think that this log entry is bad?
1. It's hard to split event from params
2. It will be pretty hard to find this row in logs
3. It's not possible to parse
4. It requires us to construct correct English sentence

### Suggestion
1. Let's split event and params
2. (optional) Let's write event in a short way


```
mail.send: {subject: hello world, recipient: earch@gmail.com}
```

## Java implementation
### Maven

### Gradle

### Examples


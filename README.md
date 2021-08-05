# ? LightDrop

LightDrop is a lightweight library that allows you to create decorator-based commands with <a href="https://github.com/DV8FromTheWorld/JDA">JDA</a>.

## Features

<ul>
    <li>Creating commands in a method.</li>
    <li>Adding permissions and permission message.</li>
    <li>Catch exceptions from commands.</li>
    <li>Adding global filters to the command's middleware.</li>
</ul>

## Installation

### With maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
        <groupId>me.neiizun</groupId>
        <artifactId>LightDrop</artifactId>
        <version>latest</version> <!--Replace with the latest version.-->
    </dependency>
</dependencies>
```

### With gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
repositories {
    dependencies {
        implementation 'com.github.NeiiZun:LightDrop:latest' // Replace with the latest version.
    }
}
```
## Usage

### Hook LightDrop

```java
JDA jda = JDABuilder.createDefault("your token").build();
        
new LightDrop().hook(jda);
```

### Create your first command

```java
public class MyCommand {
    @Command(name = "mycommand")
    public void myCommand(CommandContext context) {
        context.getChannel().sendMessage("Hello " + context.getAuthor().getName()).complete();
    }
}
```

```java 
JDA jda = JDABuilder.createDefault("your token").build();
        
new LightDrop().hook(jda)
    .map(new MyCommand());
```

![](images/img1.png)

### Full documentation available <a href="https://neiizun.gitbook.io/lightdrop/">here</a>

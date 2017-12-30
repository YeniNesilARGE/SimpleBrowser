# Simple Browser
Simple Browser is a simple file browser.

  - Explore file system.
  - Choose a file to open.
  - Select which application want to open it.

# Applications in Simple Browser 
  - Everyone can develop an application to use in Simple Browser which called Simple Application

##### Calendar
-   CalendarApp is basicly calendar as you understand by its name :) The application lists of planned events, such as birthday, anniversary etc... One of the feature is that can be used as remainder with a specified message and date so stop forgetting something and use CalendarApp.
-   Developed by [Enes Kamil Yılmaz][enesky]

![Calendar](https://github.com/YeniNesilARGE/SimpleBrowser/blob/master/yeninesilarge/images/calendar.png?raw=true)

##### Paint
-   PaintApplication is also an image editor. It's a simplified version of Paint in Windows. Drawing shapes, deleting an area, stroking an area, coloring area and etc. It's all up to you. Just pick a tool from left panel and start drawing. If you dont like it, just undo and continue it. After all just save it!
-   Developed by [Bilal Ekrem Harmansa][bilalekremharmansa]

![Paint](https://github.com/YeniNesilARGE/SimpleBrowser/blob/master/yeninesilarge/images/paint.png?raw=true)

##### Reditor
-  Reditor is an image editor. The applicaiton comes with features like filters, brightness control, rotating images and etc. on images You're free to choose whatever you want. Just use a filter on your photo, apply frame. If you don't like it, press undo and complete your changes. Time to save it and post on Instagram!
- Developed by [Furkan Sarihan][furkansarihan]

![Reditor](https://github.com/YeniNesilARGE/SimpleBrowser/blob/master/yeninesilarge/images/reditor.png?raw=true)

##### VideoPlayer
-   VideoPlayer is one of the interesting applicaitons in the Simple Browser. The application simply displays frames in succession. It use to understand how movies and videos work. Just loads frames into the VideoPlayerApplication and ready to watch. If movie is too fast, set it's speed from bottom. Did you miss something ? Just press to backward button and enjoy!
- Developed by [Salim Sahin][salimsah]

![VideoPlayer](https://github.com/YeniNesilARGE/SimpleBrowser/blob/master/yeninesilarge/images/videoplayer.png?raw=true)

## Tech

Simple Browser uses pure Java JDK. It's written in Java 8 and SimpleBrowser is an open source project on GitHub.

I must say that, Simple Browser might be needed some modified for never versions of Java 9 because of reflection. Take a look at it [here.][reflectionAccess]

### Installation

As mentioned above, Simple Browser needs Java JDK. We didn't test on before versions of Java 8 so not sure if its work on earlier version of Java 8.


Install Java SDK and set enviroments.(JAVA_HOME)

```sh
$ git clone https://github.com/YeniNesilARGE/SimpleBrowser.git
$ cd SimpleBrowser
```
Compile java classes..

```sh
$ JAVA_HOME=jdk path
$ ./build.sh
```

Collect the compiled classes into JAR file..

```sh
$ ./makeJar.sh
$ java -jar simple-browser.jar
```

Applicaitons configuration..

Simple Browser has some built in applications and configurations. Create a directory in home directory named 'yna' and copy application configurations to the created directory
```sh
$ mkdir ~/yna
$ cd built-in apps
$ cp Calendar.app ~/yna
$ cp Paint.app ~/yna
$ cp Reditor.app ~/yna
$ cp VideoPlayer.app ~/yna
```

### Todos
 - PaintApplication has some problems when using redo. Sometimes redos two drawings at a time which must redo one at a time.
 
**This page was made by [Dillinger]**

[github]: <https://github.com/YeniNesilARGE/SimpleBrowser>
[bilalekremharmansa]: <https://github.com/bilalekremharmansa>
[enesky]: <https://github.com/EnesKy>
[furkansarihan]: <https://github.com/furkansarihan>
[salimsah]: <https://github.com/salimsah>
[yna]: <https://github.com/YeniNesilARGE/SimpleBrowser>
[reflectionAccess]: <http://mail.openjdk.java.net/pipermail/jigsaw-dev/2017-March/011763.html>
[dillinger]: <http://dillinger.io>
  

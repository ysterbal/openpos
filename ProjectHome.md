## Introduction ##

This project is in the phase of forming. It contains the source code from OpenbravoPOS Version 2.30.2 with some minor modifications. The source code has been spitted into separate modules and the build system has been moved from ant to maven.
If you are familiar with maven and subversion you can jump right in:

```
svn checkout http://openpos.googlecode.com/svn/trunk/ openpos-read-only
cd openpos-read-only/openpos-parent
mvn package
```

This will build the project and create a jar file with all dependencies in the **openpos-launcher/target** directory. You can launch this file with:

```
java –jar openpos-launcher-2.4-SNAPSHOT-jar-with-dependencies.jar
```

If you are interested in hacking the code, I recommend Eclipse or better the SpringSource Tool  Suite (http://www.springsource.com/downloads/sts). Install the Subversion plugin for eclipse (if you don't have it already) and add a new SVN Repository Location in the SVN Repository Perspective, then checkout each module as a project.

See GettingStarted for some more info.

## Ideas ##

Here are some ideas for the project:
  * add NFC Support for NFC payments, NFC gift cards and NFC employee tokens for time recording and access control (see nfctools for java link)
  * add Android based UI ordering system using Android Tablets and Smartphones
  * create a Plugin interface for easy extendibility without having to modify the code base
  * create a Web interface to be able to manage the POS. Keep only cashier relevant staff as Swing based app.

## Example ##

Now if you want to extend the project please have a look at the openpos-api and openpos-commands modules. Here you will find a pattern how you can add new functionality and replace code without breaking existing code. In the openpos-commands module I replaced the code which creates the bottom panel with my own. My version creates a bottom panel with buttons to launch a music player with different styles of music. You can also stop the player (by killing the task) and open the volume control.

![http://openpos.googlecode.com/svn/resources/openpos_commands.png](http://openpos.googlecode.com/svn/resources/openpos_commands.png)

To do this I created some interfaces which allow me to replace the original code with my own. If you now build the project with
```
mvn package –Pcommands
```
This will enable the commands profile in the launcher POM file. This profile basically adds openpos-command as a dependency. Now you can add the following line into your openbravopos.properties file:
```
bottom.panel.creator=org.openpos.commands.CommandButtonsBottomPanelCreator
```
If you launch the app again then you should see the new bottons at the bottom of the app.


I hope that together we can extend this nice POS System with more features. Please don’t hesitate if you want to contribute to the project.

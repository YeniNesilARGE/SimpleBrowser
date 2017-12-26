#deleting class files.
find -type f -name '*.class' -exec rm {} \; 

javac yeninesilarge/brow/*.java
javac yeninesilarge/application/*.java
javac yeninesilarge/util/*.java

javac yeninesilarge/image/*.java
#javac yeninesilarge/image/editor/*.java
javac yeninesilarge/image/paint/*.java
javac yeninesilarge/calendarapp/*.java
#javac yeninesilarge/videoplayer/*.java

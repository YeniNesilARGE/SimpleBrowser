#deleting class files.
find -type f -name '*.class' -exec rm {} \; 

#javac yeninesilarge/calenderapp/*.java

javac yeninesilarge/*.java
javac yeninesilarge/image/*.java
javac yeninesilarge/image/editor/*.java
javac -g yeninesilarge/image/paint/*.java

#javac yeninesilarge/videoplayer/*.java

javac yeninesilarge/util/*.java
javac yeninesilarge/application/*.java
javac yeninesilarge/brow/*.java


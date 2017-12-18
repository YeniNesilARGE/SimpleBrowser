#deleting class files.
find -type f -name '*.class' -exec rm {} \; 

#javac yeninesilarge/calenderapp/*.java

javac yeninesilarge/image/*.java
#javac yeninesilarge/image/editor/*.java
javac -g yeninesilarge/image/paint/*.java


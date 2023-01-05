mkdir -p out
cd java || exit
javac -h ../native -d ../out Array.java Main.java UnsafeMemory.java
cd ../
java -Djava.library.path=build -cp out Main

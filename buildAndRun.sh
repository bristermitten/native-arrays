rm -rf build
mkdir build
cd build || exit
cmake ../native
make
cd ../

./runJava.sh

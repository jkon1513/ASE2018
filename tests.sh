#vars
current_time=`date +%Y-%m-%d.%H:%M:%S`

# run the tests and output to file
cd LionGps
./gradlew test | tee ../logs/travis-report-${current_time}

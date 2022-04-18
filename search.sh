echo 'Searching phone numbers in folder: '$1
[ ! -f target/java-challenge-1.0-SNAPSHOT.jar ] && mvn clean package -q -Dmaven.test.skip=true
java -jar target/java-challenge-1.0-SNAPSHOT.jar $1 >&1 | tee result.txt
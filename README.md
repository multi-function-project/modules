# modules

BUILD_TARGET_FUNCTION=hello-function
./mvnw clean package -P lambda -D skipTests -f ./base-function/${BUILD_TARGET_FUNCTION}/pom.xml

./mvnw clean package -P lambda -D skipTests --projects ./base-function/hello-function

./mvnw help:effective-pom --projects ./base-function/hello-function
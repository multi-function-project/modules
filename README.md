# modules

BUILD_TARGET_FUNCTION=hello-function
./mvnw clean package -P lambda -D skipTests -f ./base-function/${BUILD_TARGET_FUNCTION}/pom.xml
# Interviewer - Much Better Than The Rest
----------------------------------------

The src/ratpack/samples directory contain samples of the redis and security config files.

## Getting Started

Check this project out, cd into the directory and run:

    ./gradlew run

This will start the ratpack app in a development mode. In your browser go to `http://localhost:5050`.

Building a docker image and running it can be done with the following commands:

    ./gradlew dockerBuildImage
    docker images # just for information
    docker run --rm -it lukesplace-net-api-ratpack-java
    # the running server must be terminated with Ctrl-C

If you wish to deploy to google cloud, then modify the gradle build file and do this:

    ./gradlew profileSetup dockerBuildImage -Penvironment=production
    docker tag lukesplace-net-api-ratpack-java gcr.io/luketest1/lukesplace-net-api-ratpack-java:latest
    docker push gcr.io/luketest1/lukesplace-net-api-ratpack-java:latest

On Google Cloud, you can follow these instructions: https://codelabs.developers.google.com/codelabs/cloud-springboot-kubernetes/#5


## More on Docker

* Visit https://www.docker.com
* Docker Gradle Plugin: https://github.com/bmuschko/gradle-docker-plugin


## More on Ratpack

To learn more about Ratpack, visit http://www.ratpack.io


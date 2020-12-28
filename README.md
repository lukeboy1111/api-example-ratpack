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
    docker run --rm -it lukesplace.net/ratpack
    # the running server must be terminated with Ctrl-C


## More on Docker

* Visit https://www.docker.com
* Docker Gradle Plugin: https://github.com/bmuschko/gradle-docker-plugin


## More on Ratpack

To learn more about Ratpack, visit http://www.ratpack.io


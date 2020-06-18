# Spring Cloud Data Flow Source stream application 

Custom spring boot source application that polls data stream from Finnhub.io. 
We have added reactive programming support to leverage the Java Integration DSL for our source implementation.
The project is based on Spring Cloud Stream and we have used the spring cloud dependency management.

Spring Cloud Stream provides a couple of ways to implement a source application. 
Besides native spring cloud stream annotation you are also free to use Spring Integration. 

## Overall Design about the application and platform design 

More info about the design of the overall application can be found [here](
https://github.com/Galileo1/weird.io.case-study/blob/master/design/Design.md).

## Setup the platform 

More information about setting the platform can be found [here](
https://github.com/Galileo1/weird.io.case-study/blob/master/README.md).

# BUILD THE PROJECT

## Prerequisites for the project

 * Apache Maven 3.3.x
 * Java 1.8.x

## Build Locally

```bash
mvn clean install
```

## Publish docker image

```bash
mvn clean package jib:build
```


# Stream Deployments 

We can either use the server console or the spring data flow shell cli. The installation can be downloaded from [here](https://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-server-local/1.7.4.RELEASE/spring-cloud-dataflow-server-local-1.7.4.RELEASE.jar)

```bash 
java -jar spring-cloud-dataflow-shell-1.7.4.RELEASE.jar
```
## configuring the data flow server

Note the how to get the server url is documented in [here](https://github.com/Galileo1/weird.io.case-study/blob/master/README.md)

```bash
server-unknown:>dataflow config server http://server-address
```
 
# Registering application

If the Data Flow Server and shell are not running on the same host, point the shell to the Data Flow server URL:

```bash
 
  app register --name http --type source 
    --uri docker://gaurva/spring-cloud-data-flow-source-stream:rev2test
    
```

# For the application to work you need to register the processor and the sink application as well

```bash
 app register --name transform --type processor 
    --uri docker://gaurva/spring-cloud-data-flow-processor-stream:rev2test5
  
  app register --name log --type sink 
    --uri docker:springcloudstream/log-sink-rabbit:1.2.0.RELEASE
```

# Creating Streams 

```bash
    stream create --definition "http | transform | log" --name quotes

```

# Deploying the streams 

```bash
 stream deploy --name quotes

```

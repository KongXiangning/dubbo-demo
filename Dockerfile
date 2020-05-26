FROM 163583/maven:v1 as builder

WORKDIR /usr/local/

COPY . /usr/local/build/

#RUN git clone https://github.com/KongXiangning/dubbo-demo.git

WORKDIR /usr/local/build/

#RUN git checkout developer && mvn clean install -Dmaven.test.skip=true
RUN  mvn clean install -Dmaven.test.skip=true

FROM openjdk:8u212-jdk-alpine3.9 as prod

WORKDIR /usr/local/

COPY --from=0 /usr/local/build/springboot-dubbo-consumer/target/springboot-dubbo-consumer-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","springboot-dubbo-consumer-0.0.1-SNAPSHOT.jar"]

FROM openjdk:14-jdk-alpine
VOLUME /tmp
COPY run.sh .
COPY application/build/libs/application.jar .
ENTRYPOINT ["/run.sh"]
FROM java:8
VOLUME /tmp
EXPOSE 8081
ADD web-flux.jar app.jar
RUN bash -c 'touch /app.jar'
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
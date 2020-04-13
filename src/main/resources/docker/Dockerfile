FROM registry-k8s.yesbank.in/openjdk:jre-alpine-hardened
ENV FC_LANG en-US
ENV LC_CTYPE en_US.UTF-8

ENV APP_DIR /app
VOLUME /tmp
ARG JAR_FILE
ADD wait-for-it.sh $APP_DIR/
ADD ${JAR_FILE} $APP_DIR/app.jar
WORKDIR $APP_DIR
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","app.jar"]
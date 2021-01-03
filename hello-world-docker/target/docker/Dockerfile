FROM openjdk:8-jdk-alpine
# 挂载目录
VOLUME /tmp
# COPY or ADD to image
COPY hello-world-docker-1.0.0.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","app.jar"]
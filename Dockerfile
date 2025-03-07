FROM maven:3.8.3-openjdk-17 AS build
# 复制项目文件到容器中
COPY . /app
# 设置工作目录
WORKDIR /app
# 构建项目

#RUN microdnf clean all && \
#    microdnf install dnf && \
#    dnf install -y freetype fontconfig
RUN mvn clean package  --settings settings.xml

FROM openjdk:17-jdk-slim
# 复制生成的 jar 文件到容器中
COPY --from=0 /app/chatgpt-bootstrap/target/*.jar /app/app.jar

#COPY /app/chatgpt-bootstrap/target/*.jar /app/app.jar

RUN apt-get update && \
    apt-get install -y fontconfig && \
    apt-get install -y libfreetype6 

# 设置工作目录
WORKDIR /app
# 暴露端口
EXPOSE 3002
# 设置时区为 Asia/Shanghai
ENV TZ=Asia/Tokyo
# 设置容器的时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 定义启动命令

ENTRYPOINT ["sh","-c","java -jar app.jar --spring.profiles.actvie=prod "]

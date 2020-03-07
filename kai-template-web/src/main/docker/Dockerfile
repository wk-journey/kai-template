#指定构建机器的基础镜像FROM
java:8

#指定于外界交互的端口
EXPOSE 9505

#指定持久化目录
VOLUME /tmp

#将本地文件添加到容器中
ADD kai-template-web.jar /app.jar

#构建镜像时执行的命令
RUN bash -c 'touch /app.jar'

#配置容器，使其可执行化
ENTRYPOINT ["java","-jar","/app.jar"]
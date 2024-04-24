FROM openjdk:17

COPY ./build/libs/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul" , "/app.jar"]
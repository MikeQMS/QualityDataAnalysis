FROM adoptopenjdk/openjdk16:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "--add-opens java.base/java.lang=ALL-UNNAMED", "-jar", "target/QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar"]
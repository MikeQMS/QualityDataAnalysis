FROM adoptopenjdk/openjdk11
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./target/QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar /usr/app/
EXPOSE 1234
CMD ["java", "--add-opens=java.base/java.lang=ALL-UNNAMED", "-jar", "QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar"]
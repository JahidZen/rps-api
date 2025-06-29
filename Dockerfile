# --- Build Stage ---
# This stage uses a base image with Gradle and Java to build the app
# UPDATED to use jdk-21
FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src
COPY . .
# This command runs inside the container to build your JAR file
RUN gradle build --no-daemon

# --- Run Stage ---
# This stage uses a lightweight image with only Java, making it efficient
# UPDATED to use jdk-21
FROM openjdk:21-jdk-slim
WORKDIR /app
# Copy only the built JAR file from the previous stage
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
# Tell Render that your app uses port 8080
EXPOSE 8080
# The command to run your app
ENTRYPOINT ["java", "-jar", "app.jar"]
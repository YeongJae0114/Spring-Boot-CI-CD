# 단계 1: Gradle과 JDK를 설치하여 애플리케이션 빌드
FROM eclipse-temurin:21-jdk-alpine as build

# 필요한 패키지 설치
RUN apk add --no-cache curl unzip bash

# Gradle 설치
ENV GRADLE_VERSION=7.5.1
RUN curl -fsSL https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip -o gradle-bin.zip \
    && unzip gradle-bin.zip -d /opt \
    && rm gradle-bin.zip \
    && ln -s /opt/gradle-$GRADLE_VERSION/bin/gradle /usr/bin/gradle

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 애플리케이션 빌드
RUN ./gradlew build --no-daemon

# 단계 2: 실제 실행 환경
FROM eclipse-temurin:21-jre-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 jar 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 실행할 JAR 파일 지정
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew .
COPY gradle gradle
RUN chmod +x ./gradlew

# 의존성 파일 복사 및 다운로드
# 이렇게 미리 해두면 소스에서 변경사항이 생겼을 때 필요한 소스부분만 다시 빌드하면 되고,
# 의존성 부분은 변경이 없으므로 캐싱된 의존성을 그대로 가져와 빌드 속도 향상에 도움
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew --no-daemon dependencies

# 소스코드 복사 및 빌드
COPY ./ ./
RUN ./gradlew --no-daemon clean build -x test

# 실행 스테이지
FROM openjdk:17-jdk-slim
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/app.jar"]

# 1-bosqich: Loyihani yig'ish (Build)
FROM maven:3.9.12-openjdk-17 AS build
WORKDIR /app
# Hamma fayllarni konteynerga nusxalash
COPY . .
# .jar faylni tayyorlash (testlarni o'tkazib yuboramiz)
RUN mvn clean package -DskipTests

# 2-bosqich: Tayyor ilovani ishga tushirish
FROM openjdk:17-jdk-slim
WORKDIR /app
# Build bosqichidan tayyor jar faylni nusxalab olish
COPY --from=build /app/target/*.jar app.jar
# Render-dagi portni dinamik qabul qilish
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
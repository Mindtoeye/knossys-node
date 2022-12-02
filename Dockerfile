FROM openjdk:8-jdk-alpine
RUN apk add --no-cache curl tar bash procps maven

RUN curl -s https://knossys.com/banner.txt

RUN mkdir /app
WORKDIR /app
COPY . /app

RUN echo "Packaging ..."
RUN mvn package

RUN echo "Assembling ..."
RUN mvn compile assembly:single

CMD java -cp "./target/KNode-jar-with-dependencies.jar" com.knossys.rnd.net.KNode
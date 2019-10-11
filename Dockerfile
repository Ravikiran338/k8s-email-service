FROM openjdk:8
MAINTAINER Radiant Digital
ADD target/*.jar /msa-email-service.jar
RUN bash -c 'touch /msa-email-service.jar'
CMD ["java","-Dspring.profiles.active=docker","-jar","/msa-email-service.jar"]
EXPOSE 7777

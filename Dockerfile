FROM tomcat:8.5.84-jre17-temurin
COPY build/libs/ReceiptTask-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
FROM openjdk:11
COPY . /var/www/java  
WORKDIR /var/www/java

ADD JavaCoreServer.jar JavaCoreServer.jar
ADD loginpage.html loginpage.html
ADD look_vm.html look_vm.html
ADD mainpage.html mainpage.html
ADD order_vm.html order_vm.html

CMD java -jar JavaCoreServer.jar

EXPOSE 8080
EXPOSE 3308
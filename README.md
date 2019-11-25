First open Workbench, for MySQL. create schema there as mydb.or with anynamecheck the username and password.
all 3 should be same in application.properties in spring project....

download the folder.
import in eclipse-> as Maven project.
open the command prompt.
go in folder directory spring-boot-jpa.. here u can see src and pom.xml file.
run command:   mvn clean install
if it run successfully, then go to eclipse.. 
select the project. right click- Maven->Update Project.
Open java file: com.SpringBootJpaApplication.java and run as Spring Boot app.
Go to chrome: and open http://localhost:8080/swagger-ui.html#/
here in userContorller, you can see all operation.. and can perform anything.. 

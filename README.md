#Database Setup
-

Before starting the application
-
1.Edit the application.properties file

2.Replace datasource.url, datasource.username and datasource.password with your credentials

3.In the url do not forget to mention **createTableIfNotExists=true**, the table will be created by JPA/Hibernate

jdbc:mysql://localhost:3306/sat_results?**createTableIfNotExists=true**

4.In your database create a new schema, you can choose whatever name you want just do not forget to replace it in the url

5.Now you are good to go for using the application


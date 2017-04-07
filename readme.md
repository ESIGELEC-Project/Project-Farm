##This is the documentation of my project of ProjectFarm


Function that have achieved:
    1. as an owner, you can add new idea to the website, add documents to the projects that you have uploaded
    2. as an evaluator, you can evaluate all the projects and of cause view all of the projects.
    3. if you do not log in, click on the button "learn more" in the home page you can see all the projects that have uploaded.
    4. the website can check your login name or password is correct or not. It also can redirect to different pages according 
        to different identity.
    5. this project use sqlite as the back-end database.
    6. the sample that using memory as database is uploaded on Microsoft Azure cloud platform which is 
    
        www.projectfarm.azurewebsites.net

Function that have not achieve or can be improved:
    1. the uploaded document is not very functional, the files that have uploaded are stored in some place which should be 
        configured later. Up till now, database can only store the file name.
    2. in the future, i want to use the MySQL service in Azure platform to publish the project including many other
        configuration which i still have not enough time to implement it.
    3. to run the project, you have to download commons-fileupload-1.3.1.jar, commons-io-2.4.jar, sqlite-jdbc-3.8.11.2.jar. And put them into directory of WebContent/WEB-INF/lib.




Configuration:

    1. the url of the database should be changed according to different OS. In my laptop, I am using Mac OS which is stored 
        in the absolute directory of my project which is
         "jdbc:sqlite:/Users/Frank/Documents/workspace_JEE/ProjectFarm/src/ProjectFarm.db" (in the initialize() function of
          the model.db.DBUtil.java class)
    2. some user account for testing:
        Owner:
        john@acme.com : 123
        mary@acme.com : 123
        paul@acme.com : 123
        
        Evaluator:
        sarah@geek.com : 456
        thibault@geek.com : 456
        george@geek.com : 456
        


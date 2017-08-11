## This is the documentation of my project of ProjectFarm

Function that have achieved:
    1. as an owner, you can add new idea to the website, add documents to the projects that you have uploaded
    2. as an evaluator, you can evaluate all the projects and of cause view all of the projects.
    3. if you do not log in, click on the button "learn more" in the home page you can see all the projects that have uploaded.
    4. the website can check your login name or password is correct or not. It also can redirect to different pages according 
        to different identity.
    5. this project has changed to use mysql as the back-end database.

Function that have not achieve or can be improved:
    1. the uploaded document is not very functional, the files that have uploaded are stored in some place which should be 
        configured later. Up till now, database can only store the file name. I think this functionality could be achieved later
        by using hdfs as the store file system. This feature will be implemented in a new branch called hadoop.
    2. (deprecated)to run the project, you have to download commons-fileupload-1.3.1.jar, commons-io-2.4.jar, sqlite-jdbc-3.8.11.2.jar. And put them into directory of WebContent/WEB-INF/lib.
    3. In order to be more structured, I will use other mvc framework instead in the future which will also implemented in hadoop branch.
Configuration:
    1. some user account for testing:
        Owner:
        john@acme.com : 123
        mary@acme.com : 123
        paul@acme.com : 123
        
        Evaluator:
        sarah@geek.com : 456
        thibault@geek.com : 456
        george@geek.com : 456


## mysql configuration
    To use mysql as database, you can add a mysql docker container. To do so, run
`docker run --name mysql --publish 6603:3306 -d -e MYSQL_ROOT_PASSWORD=123 -e MYSQL_DATABASE=ProjectFarm -v ~/dev/Project-Farm/data:/var/lib/mysql mysql`
Notice in mac, the docker ip address is localhost while in linux the docker ip address is 172.17.0.1 (might vary accordingly)
when you start the mysql container, you should dump the data/mysql.sql into the ProjectFarm database `mysql ProjectFarm < 'data/mysql.sql'`

## Start project
    To start the whole project run `docker-compose up`. On the other hand, if you want to start a single service such as mysql, use the
    `start-separate-service.sh` script. This is good for testing.


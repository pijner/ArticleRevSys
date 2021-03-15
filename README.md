# ArticleRevSys
A website to publish and score reviews for scientific articles

The application consists of a landing page and two main webpages:
 - index.xhtml: landing page for the web-app with navigation links to the other two pages
 - new_review.xhtml: page to create a new review and submit to DB server
 - lookup.xhtml: page to retrieve all reviews from the DB with an option to score each review. The scoring functionality is implemented using a dialog to avoid un-necessary redirection and reduce complexity.

Notes:
 - There is no User Account Management (UAM) currently implemented in this application. As such, users are free to score a review multiple times. Furthermore, they can score their own review too. This can be handeled by preventing users to score reviews with the same `user_id` or by adding a UAM module to the app. As per requirement specifications, this was avoided for simplicity.

## Usage
### Using docker compose
1. Clone the repo to your computer
2. Navigate to the repo using the terminal
3. Issue the following command
        
        $ docker-compose up

4. Wait till you see the following message 

        parawaweb | Boot Command deploy returned with result SUCCESS : PlainTextActionReporterSUCCESSDescription: deploy AdminCommandApplication deployed with name ArticleRevSys-1.0.
        payaraweb |     [name=ArticleRevSys-1.0
        payaraweb | |#]

5. Go to `http://localhost:8080/ArticleRevSys-1.0/`
6. To stop containers, go to the project repo and issue the following command in the terminal
    
        $ docker-compose down

### Using NetBeans12.0
1. Clone the repo and open it as a project in NetBeans12.0
2. Start the MySQL server and create the database using the script in `database/create.sql`. The database will be initialized with 0 entries
3. Go to `DBConnector.java` and update the connection parameters in the constructor.
4. Run the project


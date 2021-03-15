package com.pijner.articlerevsys.connector;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 * This class handles all communication to the database. The connection
 * parameters are defined in the default constructor.
 *
 * @author Prahar Ijner
 */
public class DBConnector {

    private final String serverName;
    private final int portNumber;
    private final String user;
    private final String password;
    private final String databaseName;
    private final MysqlDataSource dataSource;

    public DBConnector() {
        this.serverName = "localhost";
        this.portNumber = 3306;
        this.user = "root";
        this.password = "dbPassword";
        this.databaseName = "ARS";
        this.dataSource = new MysqlDataSource();
        connectDataSource();
    }

    public DBConnector(String serverName, int portNumber, String user, String password, String databaseName) {
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.dataSource = new MysqlDataSource();
        connectDataSource();
    }

    public final void connectDataSource() {
        String url = String.format(
                "jdbc:mysql://%s:%d/%s?allowPublicKeyRetrieval=true&useSSL=false",
                this.serverName,
                this.portNumber,
                this.databaseName);
        dataSource.setURL(url);
        dataSource.setUser(this.user);
        dataSource.setPassword(this.password);
    }

    /**
     * Function to close result set, statement, and connection. Always call this
     * after executing a query
     *
     * @param rs ResultSet that reads the result of a query
     * @param st Statement that executes the query
     * @param cn Connection obtained from data source
     */
    private void close(ResultSet rs, Statement st, Connection cn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * function to write a given review to the DB
     *
     * @param r: Review object to be written to the database
     * @return String indicating pass or fail (status of writing)
     */
    public String writeReview(Review r) {
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            User u = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
            u.updateID();

            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = String.format(
                    "INSERT INTO reviews "
                    + "(user_id, title, url, summary, posneg, majorPoints, minorPoints, rec) "
                    + "VALUES (%d, '%s\', '%s', '%s', '%s', '%s', '%s', '%s' );",
                    u.getUser_id(),
                    r.getTitle(),
                    r.getUrl(),
                    r.getSummary(),
                    r.getPosneg(),
                    r.getMajorPoints(),
                    r.getMinorPoints(),
                    r.getRecommendation()
            );
            dbStatement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return "fail";
        } finally {
            this.close(null, dbStatement, dbConnection);
        }
        return "pass";
    }

    /**
     * function to retrieve a user with a given name
     *
     * @param name; String representing the name to look up
     * @return User object containing name and user_id
     */
    public User getUserByName(String name) {
        Connection dbConnection = null;
        Statement dbStatement = null;
        ResultSet result = null;
        User queriedUser = null;

        try {
            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = String.format(
                    "SELECT * FROM users WHERE "
                    + "name = '%s';", name
            );
            result = dbStatement.executeQuery(query);

            while (result.next()) {
                queriedUser = new User(result.getString("name"), result.getInt("user_id"));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(result, dbStatement, dbConnection);
        }
        return queriedUser;
    }

    /**
     * function to add a user to the DB. Since the user table only consists of
     * name and user_id (which is auto-generated), we only need the name
     *
     * @param name: String representing user's name
     */
    public void addUser(String name) {
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = String.format(
                    "INSERT INTO users (name) VALUES ('%s');",
                    name
            );

            dbStatement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(null, dbStatement, dbConnection);
        }
    }

    /**
     * function to add score to the database
     *
     * @param review_id: int representing the ID of the review scored
     * @param user_id: int representing the ID of the user who scored
     * @param score: int representing the score
     */
    public void addScore(int review_id, int user_id, int score) {
        Connection dbConnection = null;
        Statement dbStatement = null;

        try {
            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = String.format(
                    "INSERT INTO scores (user_id, score, review_id) "
                    + "VALUES (%d, %d, %d);",
                    user_id, score, review_id
            );
            dbStatement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(null, dbStatement, dbConnection);
        }
    }

    /**
     * function to load all reviews from the database
     *
     * @return ArrayList<> containing all the reviews
     */
    public ArrayList<Review> getReviews() {
        Connection dbConnection = null;
        Statement dbStatement = null;
        ResultSet result = null;
        ArrayList<Review> reviewList = null;

        try {
            dbConnection = dataSource.getConnection();
            dbStatement = dbConnection.createStatement();
            String query = String.format("SELECT * FROM reviews "
                    + "JOIN users ON reviews.user_id = users.user_id "
                    + "LEFT JOIN ( "
                    + "SELECT sum(score)/count(score) AS avg_score, "
                    + "count(score) AS num_scores, "
                    + "review_id "
                    + "FROM scores "
                    + "GROUP BY review_id) as averages "
                    + "ON averages.review_id = reviews.review_id;"
            );

            result = dbStatement.executeQuery(query);
            reviewList = new ArrayList<>();

            while (result.next()) {
                reviewList.add(
                        new Review(
                                result.getString("name"),
                                result.getString("title"),
                                result.getString("url"),
                                result.getString("summary"),
                                result.getString("posneg"),
                                result.getString("majorPoints"),
                                result.getString("minorPoints"),
                                result.getString("rec"),
                                result.getInt("num_scores"),
                                result.getFloat("avg_score"),
                                result.getInt("review_id")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close(result, dbStatement, dbConnection);
        }

        return reviewList;
    }
}

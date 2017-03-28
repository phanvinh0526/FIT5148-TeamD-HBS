/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author VinhPQ
 */
public class OracleDBConnectionUtil {
    private static OracleDBConnectionUtil oracleDb;
    private static final String URL         = "jdbc:oracle:thin:@hippo.its.monash.edu.au:1521:FIT5148a";
    private static final String USERNAME    = "S28520262";
    private static final String PASSWORD    = "TeamD";
    private static final String ENCODING    = "utf-8";
    
    private Connection connection;
    private String url;
    private String user;
    private String password;
    private String characterEncoding;
    
    private OracleDBConnectionUtil() throws SQLException{
        this.url = URL;
        this.user = USERNAME;
        this.password = PASSWORD;
        this.characterEncoding = ENCODING;
        
        Properties info = new Properties();
        info.setProperty("user", this.user);
        info.setProperty("password", this.password);
        info.setProperty("characterEncoding", this.characterEncoding);
        try {
            Driver dri = new OracleDriver();
            this.connection = DriverManager.getConnection(this.url, info);
        } catch (SQLException e) {
            Logger.getLogger(OracleDBConnectionUtil.class.getName()).log(Level.SEVERE, "Connection: ", e);
            System.out.println("Cannot create a new connection with OracleDB");
        }
    }
    
    public static OracleDBConnectionUtil getInstance() throws SQLException{
        if(oracleDb==null){
            oracleDb = new OracleDBConnectionUtil();
        }
        return oracleDb;
    }
    
    public static void closeConnection(){
        if(oracleDb!=null){
            try {
                oracleDb.connection.close();
                Logger.getLogger(OracleDBConnectionUtil.class.getName()).log(Level.SEVERE, "OracleDB Connection's Closed!");
                System.out.println("Connection to OracleDB is closed!");
            } catch (SQLException e) {
                Logger.getLogger(OracleDBConnectionUtil.class.getName()).log(Level.SEVERE, "Connection: ", e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
}

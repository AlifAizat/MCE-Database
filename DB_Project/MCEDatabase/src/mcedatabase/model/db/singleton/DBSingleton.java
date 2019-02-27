/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.singleton;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import mcedatabase.model.db.dao.db.DbFactoryDao;

/**
 *
 * @author alifaizat
 */
public class DBSingleton {
    
    
    //Static instance for DBSingleton
    private static DBSingleton dbSingleton_instance = null;
    
    //Variable of the connection
    public static Connection con = null;
    
    //Variable of DaoFactory
    public static DbFactoryDao dbDaoFactory = null;

    //Private constructor for DBSingleton
    private DBSingleton() {

        this.con = ConnectDB();
        this.dbDaoFactory = new DbFactoryDao();
    }
    
    /*
    * Create a connection to the mysql server
    */
    private Connection ConnectDB(){
        
        Connection conBuff = null;
        try {
            
            String user = "alifaizat";
            String password = "alifaizat";
            String database = "alifaizatdb";
            String host = "jdbc:mysql://db4free.net:3306/alifaizatdb";
            
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(user);
            dataSource.setPassword(password);
            dataSource.setDatabaseName(database);
            dataSource.setServerName("db4free.net");
            dataSource.setPort(3306);

            //conBuff = dataSource.getConnection();
            conBuff = DriverManager.getConnection(host, user, password);
            
            System.out.println("Connection made !");
        } catch (SQLException ex) {
            System.out.println("Connection failed : it's null");
            System.out.println("Connection failed : " + ex.getMessage());            
        }
        
        return conBuff;
    }
    
    //Static method to create instance of DBSingleton
    public static DBSingleton getInstance() throws SQLException{
        
        if(dbSingleton_instance == null)
        {
            dbSingleton_instance = new DBSingleton();
        }
        
        return dbSingleton_instance;
    }
    
    
}

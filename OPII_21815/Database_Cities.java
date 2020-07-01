/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jimge
 */
public class Database_Cities {
     public static Connection connectionToDatabase() {
        try{
        Class.forName("oracle.jdbc.driver.OracleDriver");             
            String url ="jdbc:oracle:thin:@//oracle12c.hua.gr:1521/orcl";
            try{
                Connection conn =(Connection) DriverManager.getConnection(url,"it21815","it21815");                                
                System.out.print("Successful connection.\n");
                return conn;
            } catch(SQLException ex){
                ex.printStackTrace();  
                System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode()); 
            }                                                
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }                   
        return null;
    }
   
    public static void selectToDatabase(Connection conn,ArrayList<City> cities){        
        System.out.print("Please wait while we are retreiving given cities...\n");
        try{
            Statement stmt = conn.createStatement(); 
            ResultSet rs=stmt.executeQuery("SELECT * from Database_Cities");
            while(rs.next())  {
                cities.add(new City(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getDouble(8),rs.getString(9),rs.getInt(10),rs.getInt(11)));
            }         
            for(int i = 0 ; i< cities.size(); i++){
               System.out.println(cities.get(i).city_name); 
            }
            
            stmt.close(); 
        }catch(SQLException | NullPointerException sqe){
            sqe.printStackTrace();
        }                 
    }           
     
    public static void insertToDatabase(Connection conn,City city){     
        try{
            //Πρόσθεσα να παίρνει η βάση δεδομένων τα νέα στοιχεία όπως και να τα σώζει εκεί.
              Statement stmt = conn.createStatement();
              String query = ("INSERT INTO Database_Cities(cities_name,cities_countrycode,cities_transportation,cities_museum,cities_CafeRestaurantBar,cities_weather,cities_lat,cities_lon,cities_landscape,cities_pressure,cities_humidity)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?)");
              PreparedStatement preparedStmt = conn.prepareStatement(query);              
              preparedStmt.setString (1, city.city_name);
              preparedStmt.setString (2,city.city_countrycode );
              preparedStmt.setString (3, city.city_transportation);
              preparedStmt.setString (4, city.city_museum);
              preparedStmt.setString (5, city.city_CafeRestaurantBar);
              preparedStmt.setString (6, city.city_weather);
              preparedStmt.setDouble(7, city.city_lat);          
              preparedStmt.setDouble(8, city.city_lon);
              preparedStmt.setString (9, city.city_landscape);
              preparedStmt.setInt(10, city.city_pressure);
              preparedStmt.setInt(11, city.city_humidity);
              preparedStmt.executeUpdate();          
              System.out.print("*Cities added successfully in the database!*\n");
        }catch(SQLException sql){
            System.err.println("Got an exception!");
            System.err.println(sql.getMessage());
        }
    }
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *En esta clase se hace la coneccion de NetBeans con la base de datos cinedb en MySQL
 * @author José Alvarez and Omar Castelan
 */
public class MySQLConnection {
    public static Connection get(){
    Connection connection = null;
    try{
    connection = DriverManager.getConnection("jdbc:mysql://localhost/cinedb?user=root&password=omarneko62");
    }catch(SQLException ex){
        System.err.print("Error: " + ex.getMessage());
    
    
    }
    return connection;
    
    }
}

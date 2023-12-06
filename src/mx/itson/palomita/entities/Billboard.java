/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.itson.palomita.persistence.MySQLConnection;

/**
 *
 * 
 * @author José Alvarez and Omar Castelan
 */
public class Billboard {
private int funcionId;
private Movie movieId;
private Hall hallId;
private String date;

    public int getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(int funcionId) {
        this.funcionId = funcionId;
    }

   

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
    }

 
/*@pharam String filter
    * El método getAll realiza una consulta a una tabla llamada "funcion" en una base de datos, utilizando un filtro proporcionado. Luego,
    * crea una lista de objetos "Billboard" con la información obtenida de la consulta. Para cada fila recuperada, se crea un objeto "Billboard" al cual se le asignan propiedades como la fecha, el ID de la función,
    * y se busca información adicional en las tablas "hall" y "movie" para asignar a las propiedades relacionadas. Finalmente, los objetos "Billboard" se agregan a una lista, que se devuelve como resultado del método.
    * Se manejan excepciones en caso de errores durante la ejecución de la consulta.
    * 
    */
   public static  List<Billboard> getAll(String filter){
    List<Billboard> billboard = new ArrayList();
    try{
        Connection conection = MySQLConnection.get();
        PreparedStatement statement = conection.prepareStatement("SELECT * FROM funcion WHERE hallId LIKE ?");
        statement.setString(1,"%"+ filter+"%");
        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            Billboard billboard2 = new Billboard();
            List<Hall> hall = new ArrayList();
            hall = Hall.gatAll("");
            List<Movie> movie = new ArrayList();
            movie= Movie.gatAll("");
        for(Hall h : hall){
            resultSet.getInt(3);
            if(resultSet.getInt(3) == h.getIdHall()){
                billboard2.setHallId(h);
            }
        }
        for(Movie m : movie){
            resultSet.getInt(2);
            if(resultSet.getInt(2) == m.getMovieId()){
                billboard2.setMovieId(m);
            }
        }
        billboard2.setDate(resultSet.getString(4));
        billboard2.setFuncionId(resultSet.getInt(1));
         billboard.add(billboard2);
           
        }
    }catch(SQLException ex){
        System.out.println("Error: "+ ex.getMessage());
    }
    return billboard;
}
   /*@pharam int movieId, int idHall, String date
    *Se define un método para almacenar información sobre funciones de cine en una base de datos MySQL.
    * Recibe como parámetros el identificador de la película, el de la sala de cine y la fecha de la función.
    * La conexión a la base de datos se establece a través de la clase MySQLConnection, 
    * y luego se ejecuta una consulta SQL de inserción para agregar una nueva entrada a la tabla "palomitafunction" con los valores proporcionados.
    * El método devuelve un booleano que indica si la operación de inserción fue exitosa. 
    */
   public boolean save( int movieId, int hallId, String date){
       boolean result = false;
       try{
           Connection conexion = MySQLConnection.get();
    String query = "INSERT INTO funcion (movieId, hallId, date) VALUES (?, ?, ?)";
    PreparedStatement statement = conexion.prepareStatement(query);
    statement.setInt(1, movieId);
    statement.setInt(2, hallId);
    statement.setString(3, date);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    
       }catch(Exception ex){
           
       }
    return result;
   }
  /*  @pharam int idFunction, String date, int movieId, int idHall
   * Se define un método para actualizar información de funciones de cine en una base de datos MySQL. 
    * El método recibe como parámetros el identificador de la función, la nueva fecha, el nuevo identificador de película y el nuevo identificador de sala de cine. 
    * Primero, establece una conexión a la base de datos a través de la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de actualización que modifica la fila correspondiente en la tabla "palomitafunction" con los nuevos valores proporcionados.
    * El método devuelve un booleano indicando si la operación de actualización fue exitosa, verificando si exactamente una fila fue afectada por la consulta. 
    * @pharam int idFunction, String date, int movieId, int idHall*/
     public boolean update(int funcionId, int movieId, int hallId, String date){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "UPDATE funcion SET movieId = ?, hallId = ?, date=? WHERE funcionId = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(1, movieId);
    statement.setInt(2, hallId);
    statement.setString(3, date);
    statement.setInt(4, funcionId);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    /*@pharam int idFunction
    *Se define un método para eliminar una entrada de funciones de cine en una base de datos MySQL.
    * El método toma como parámetro el identificador de la función que se desea eliminar. Primero, establece una conexión a la base de datos utilizando la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de eliminación que elimina la fila correspondiente en la tabla "palomitafunction" basándose en el identificador proporcionado.
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de eliminación.
    */
     public boolean delete(int funcionId){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "DELETE FROM funcion WHERE funcionId = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(1, funcionId);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

        
        
}


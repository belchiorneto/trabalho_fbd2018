/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpoPeer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author iranilda
 */
public class DbConn {
   public static Connection con;
   public static Statement stmt;
   // variaveis de conexão
   public static String server = "localhost";
   public static String porta = "1433";
   public static String bd = "fbd_trabalho";
   public static String usuario = "Ufc";
   public static String senha = "Ufc123";
   //"jdbc:sqlserver://"+server+":"+porta+";databaseName="+bd+";user="+usuario+";password="+senha;
   //jdbc:sqlserver://"+server+":"+porta+";databasename="+bd+";integratedsecurity=true
   public static String connectionUrl = "jdbc:sqlserver://"+server+":"+porta+";databasename="+bd+";integratedsecurity=true";
   public static void OpenConnection(){
       try{
            con = DriverManager.getConnection(connectionUrl);           
            stmt = con.createStatement();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
   }
   
   public static Statement getStatment(){
       return stmt;
   }
   
   
}

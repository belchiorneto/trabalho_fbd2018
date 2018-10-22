/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpoPeer;

import java.sql.ResultSet;
import java.sql.SQLException;
import static SpoPeer.DbConn.stmt;

/**
 *
 * @author iranilda
 */
public class DbUtils {
    public static void Insert(String campos, String data, String table){
       String SQL = "INSERT INTO " + table + " (" +campos+ ") VALUES (" + data + ");";
System.out.println(SQL);
       try{
           stmt.executeUpdate(SQL);
       }catch (SQLException e) {
            e.printStackTrace();
       }
   }
    public static ResultSet Lista(String SQL){
       System.out.println(SQL);
       ResultSet rs = null;
       try{
            rs = stmt.executeQuery(SQL);
       }catch (SQLException e) {
            e.printStackTrace();
       }
       return rs;
   }
}

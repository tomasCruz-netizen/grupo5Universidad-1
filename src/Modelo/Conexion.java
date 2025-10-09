
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {
    private String usuario;
    private String url;
    private String password;
    
    private static Connection conexion= null;
    

    public Conexion(String url, String usuario, String password) {
        this.usuario = usuario;
        this.url = url;
        this.password = password;
       
    }
      
    public Connection buscarConexion () {
        if(conexion ==null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url,usuario, password);
            } catch (ClassNotFoundException ex) {
                System.out.println("No es posible establecer connection");
                //JOptionPane.showMessageDialog(null,"No es posible establecer la conexion...");
            }catch (SQLException ex){
                System.out.println("No es posible conectarse a la base de datos");
                //JOptionPane.showMessageDialog(null, "Base de datos no encontrada...");
                }
            
        }
        return conexion;
    }
}

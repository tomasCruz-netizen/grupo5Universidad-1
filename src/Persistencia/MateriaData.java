/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Brian D
 */
public class MateriaData {
     private Connection conn= null;

    public MateriaData(Conexion conexion) {
        
        this.conn= conexion.buscarConexion();
    }
    
    public void guardarMatria (Materia mate){
        
        String query= "INSERT INTO  materia ( nombre, año, estado) VALUES (?,?,?,?,)";
        try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, mate.getNombre());
        ps.setInt(2, mate.getAnio());
        ps.setBoolean(3, mate.isEstado());
           
    
        ps.executeUpdate();
        
        } catch (SQLException e){
            System.out.println("Error en el insertar "+ e.getMessage());
               
        }
       
    }
    
    
    public void actualizarMateria (Materia mate){
    
    
       String query=" UPDATE materia set  nombre=?, año=?, estado=? where idMateria=? "; 
    
    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, mate.getNombre());
        ps.setInt(2, mate.getAnio());
        ps.setBoolean(3, mate.isEstado());
       ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error de actualizacion "+ e.getMessage());      
        }
    }
    
    
    
     public void bajaLogica(Materia mate){
         //este metodo cambia solo el estado del alumno
        
       String query=" UPDATE materia set estado=? where idMateria=? "; 
    
    
     try {
        PreparedStatement ps = conn.prepareStatement(query);
       ps.setBoolean(1, mate.isEstado());
       ps.setInt(2, mate.getIdMateria());
       
        ps.executeUpdate();
       
        } catch (SQLException e){
            System.out.println("Error baja logica "+ e.getMessage());
               
        }
       
    }
     
     
     public void eliminarMateria(Materia mate){
             
       String query=" Delete from materia where idMateria=? "; 
    
     try {
        PreparedStatement ps = conn.prepareStatement(query);
   
         ps.setInt(1, mate.getIdMateria());
          ps.executeUpdate();
       
        } catch (SQLException e){
            System.out.println("Error eliminar materia " + e.getMessage());
               
        } 
    }
     
     
     
     
     public void selectTodo() {
     
     //obtener todos los alumnos
          
          String sql="Select * from materia";
          
          
          
            
        try {
          PreparedStatement    ps = conn.prepareStatement(sql);
            
             ResultSet resultado= ps.executeQuery();
             
             while (resultado.next()){
             
             System.out.println("ID MATERIA"+resultado.getInt("idMateria"));
             System.out.println("NOMBRE "+resultado.getString("nombre"));
             System.out.println("AÑO "+resultado.getInt("anio"));
             System.out.println("ESTADO"+resultado.getBoolean("estado"));         
           
             
             }            
            
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error mostrar tablas "+ ex.getMessage() );
        
        }
     
     }
     
     
     
     
     public void selectEspecifico(Materia mate) {
     
          String sql="Select * from materia where idMateria=? ";
          
          PreparedStatement ps;
           
        try {
            ps = conn.prepareStatement(sql);
            
             ps.setInt(1, mate.getIdMateria());     
            
             ResultSet resultado= ps.executeQuery();
             
             while (resultado.next()){
             
              System.out.println("ID MATERIA"+resultado.getInt("idMateria"));
             System.out.println("NOMBRE "+resultado.getString("nombre"));
             System.out.println("AÑO "+resultado.getInt("anio"));
             System.out.println("ESTADO"+resultado.getBoolean("estado"));     
             }            
            
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error mostrar tablas por id"+ ex.getMessage() );
        
        }
          
          // ESTO DEVUELVE UN RESULSET, es como una matriz con columnas y filas
         // resulset le pregunta si hay una fila para recorrer con el metodo next
          
       
     }
     
     
     
    
}

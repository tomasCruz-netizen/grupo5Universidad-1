/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author fatimaalcaraz
 */
public class AlumnoData {
    
    private Connection conn= null;
    
    

    public AlumnoData() {
    }
    
    
    public AlumnoData(Conexion conexion) {
        
        this.conn= conexion.buscarConexion();
    }
    
    public void guardarAlumno (Alumno alum){
        
        String query= "INSERT INTO  alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES (?,?,?,?,?)";
        try {
        PreparedStatement ps = conn.prepareStatement(query);
        LocalDate fechaUtil = alum.getFechaNacimiento();
        java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaUtil);
        ps.setInt(1, alum.getDni());
        ps.setString(2, alum.getApellido());
        ps.setString(3, alum.getNombre());
        ps.setDate(4, fechaSQL);
        ps.setBoolean(5, alum.isEstado());
        ps.executeUpdate();
        
        } catch (SQLException e){
            System.out.println("Error... ");
            
            
           
               
        }
       
    }
    
    
    public void actualizarAlumno(Alumno alum1){
    
    
       String query=" UPDATE alumno set dni=? , apellido=?, nombre=?, fechaNacimiento=?,  estado=? where idAlumno=? "; 
    
    try {
        PreparedStatement ps = conn.prepareStatement(query);
        LocalDate fechaUtil = alum1.getFechaNacimiento();
        java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaUtil);
        ps.setInt(1, alum1.getDni());
        ps.setString(2, alum1.getApellido());
        ps.setString(3, alum1.getNombre());
        ps.setDate(4, fechaSQL);
        ps.setBoolean(5, alum1.isEstado());
        ps.setInt(6, alum1.getIdAlumno());
     
        ps.executeUpdate();
       
        } catch (SQLException e){
            System.out.println("Error... ");      
        }
    }
    
    
    
     public void bajaLogica(int id){
         //este metodo cambia solo el estado del alumno
        
       String query=" UPDATE alumno set estado=0 where idAlumno=? "; 
    
    
     try {
        PreparedStatement ps = conn.prepareStatement(query); 
        ps.setInt(1, id);
        int exito=ps.executeUpdate();
        if(exito ==1){
            JOptionPane.showMessageDialog(null, "Alumno dado de baja");
        }
       
        } catch (SQLException e){
            System.out.println("Error... ");
               
        }
       
    }
     
     
     public void eliminarAlumno(int id){
             
       String query=" Delete from alumno where idAlumno=? "; 
    
     try {
          PreparedStatement ps = conn.prepareStatement(query);
   
          ps.setInt(1, id);
          ps.executeUpdate();
       
        } catch (SQLException e){
            System.out.println("Error... ");
               
        } 
    }
     
     
     
     
     public ArrayList<Alumno> listarAlumnos() {
     
     //obtener todos los alumnos
          
          String sql="Select * from alumno where estado= 1";
          ArrayList <Alumno> alumnos= new ArrayList();
         
          
            
        try {
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultado= ps.executeQuery();
             
             while (resultado.next()){
                 
              Alumno alumno= new Alumno();
              alumno.setIdAlumno(resultado.getInt("idAlumno"));
              alumno.setDni(resultado.getInt("dni"));
              alumno.setApellido(resultado.getString("apellido"));
              alumno.setNombre(resultado.getString("nombre"));         
              alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
              alumno.setEstado(resultado.getBoolean("estado"));
              
              alumnos.add(alumno);
             }   
             ps.close();
            
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error de conexion" );
        
        }
        return alumnos;
     }
     
     
     
     
     public Alumno buscarAlumno(int id) {
     
          String sql="Select * from alumno where idAlumno=? ";
          
         
          Alumno alumno= null;
           
        try {
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, id); 
             ResultSet resultado= ps.executeQuery();
             
             if(resultado.next()){
              alumno= new Alumno(); 
              alumno.setIdAlumno(id);
              alumno.setDni(resultado.getInt("dni"));
              alumno.setApellido(resultado.getString("apellido"));
              alumno.setNombre(resultado.getString("nombre"));         
              alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
              alumno.setEstado(resultado.getBoolean("estado"));
           
             }  else{
                 
                 JOptionPane.showMessageDialog(null, "No se encontro el alumno");
             }          
             ps.close();
             
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error de conexion" );
        
        }
        
        return alumno;
          // ESTO DEVUELVE UN RESULSET, es como una matriz con columnas y filas
         // resulset le pregunta si hay una fila para recorrer con el metodo next
          
       
     }
     
     public Alumno buscarAlumnoPorDni(String dni) {
     
          String sql="Select * from alumno where dni=? ";
          Alumno alumno= null;
           
        try {
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, dni); 
             ResultSet resultado= ps.executeQuery();
             
             if(resultado.next()){
              alumno= new Alumno(); 
              alumno.setIdAlumno(resultado.getInt("idAlumno"));
              alumno.setDni(resultado.getInt("dni"));
              alumno.setApellido(resultado.getString("apellido"));
              alumno.setNombre(resultado.getString("nombre"));         
              alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento").toLocalDate());
              alumno.setEstado(resultado.getBoolean("estado"));
           
             }  else{
                 
                 JOptionPane.showMessageDialog(null, "No se encontro el alumno");
             }          
             ps.close();
             
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error de conexion" );
        
        }
        
        return alumno;
          // ESTO DEVUELVE UN RESULSET, es como una matriz con columnas y filas
         // resulset le pregunta si hay una fila para recorrer con el metodo next
          
       
     }
     
     
     

 }
     

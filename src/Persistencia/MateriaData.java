/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Brian D
 */
public class MateriaData {
     private Connection conn= null;

     
    public MateriaData() {
        
    }
     

    public MateriaData(Conexion conexion) {
        
        this.conn= conexion.buscarConexion();
    }
    
    public void guardarMateria (Materia mate){
        
        String query= "INSERT INTO  materia ( nombre, año, estado) VALUES (?,?,?)";
        try {
        PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, mate.getNombre());
        ps.setInt(2, mate.getAnio());
        ps.setBoolean(3, mate.isEstado());
           
    
        ps.executeUpdate();
        ps. close();
        
        } catch (SQLException e){
            System.out.println("Error  "+ e.getMessage());
               
        }
       
    }
    public  Materia mostrarMateriaID(int id) {

          String sql="Select * from materia where idMateria=? ";
          Materia mate= new Materia();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

             ps.setInt(1,id);

             ResultSet resultado= ps.executeQuery();

             if (resultado.next()){


                 mate.setNombre(resultado.getString("nombre"));
                 mate.setAnio(resultado.getInt("año"));
                 mate.setEstado(resultado.getBoolean("estado"));
             }
            ps.close();
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error mostrar tablas por nombre"+ ex.getMessage() );

        }

        return mate;


     }
    
    public void actualizarMateria (Materia mate){
    
    
       String query=" UPDATE materia set  nombre=?, año=?, estado=? where idMateria=? "; 
      
                
    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, mate.getNombre());
        ps.setInt(2, mate.getAnio());
        ps.setBoolean(3, mate.isEstado());
        ps.setInt(4, mate.getIdMateria());
       
       ps.executeUpdate();
          ps.close();
       
        } catch (SQLException e){
            System.out.println("Error de actualizacion "+ e.getMessage());      
        }
    

    
    }
    
    
    
     public void bajaLogica(int id){
         //este metodo cambia solo el estado del alumno
        
       String query=" UPDATE materia set estado=0 where idMateria=? "; 
    
    
     try {
        PreparedStatement ps = conn.prepareStatement(query);
       
       ps.setInt(1, id);
       
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
     
     
     
     
     public ArrayList <Materia> selectTodo() {
     
     
          
          String sql="Select * from materia";
          
          ArrayList <Materia> materias = new ArrayList ();
          
            
        try {
          PreparedStatement ps = conn.prepareStatement(sql);
            
             ResultSet resultado= ps.executeQuery();
             
             while (resultado.next()){
                 Materia mat = new Materia();
                 mat.setIdMateria(resultado.getInt("IdMateria"));
                 mat.setNombre(resultado.getString("nombre"));
                 mat.setAnio(resultado.getInt("año"));
                 mat.setEstado(resultado.getBoolean("estado"));
                 
                 materias.add(mat);
             
             }            
            ps.close();
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error mostrar tablas "+ ex.getMessage() );
        
        }
        return materias ;
     }
     
     
     
     
     public Materia mostrarMateria(String nombre) {
     
          String sql="Select * from materia where nombre=? ";
          Materia mate= new Materia();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
             ps.setString(1,nombre);  
            
             ResultSet resultado= ps.executeQuery();
             
             if (resultado.next()){
                
                 mate.setIdMateria(resultado.getInt("idMateria"));
                 mate.setNombre(resultado.getString("nombre"));
                 mate.setAnio(resultado.getInt("año"));
                 mate.setEstado(resultado.getBoolean("estado"));
             }            
            ps.close();
        } catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "Error mostrar tablas por nombre"+ ex.getMessage() );
        
        }
          
        return mate;
          
       
     }
     
     
     
    
}

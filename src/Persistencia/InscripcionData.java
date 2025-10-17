
package Persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class InscripcionData {

    private Connection con = null;
    AlumnoData aludata = new AlumnoData();
    MateriaData matedate = new MateriaData();

    public InscripcionData(Conexion conexion) {
        con = conexion.buscarConexion();
    }

    public void inscripcion(Inscripcion ins) {

        String sql = "INSERT INTO `inscripcion`( `nota`, `idAlumno`, `idMateria`) VALUES (?,?,?) ";
                
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          
                ps.setDouble(1, ins.getNota());
                ps.setInt(2, ins.getIdAlumno());
                ps.setInt(3, ins.getIdMateria());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {

                ins.setIdInscripto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "inscripcion realizada");
            }
         
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo conectar a la BD");
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, double nota) {

        String sqr = "UPDATE `inscripcion` SET nota=? WHERE idAlumno=? AND idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sqr);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas == 1) {

                JOptionPane.showMessageDialog(null, "alumno actualizado");

            }

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "no se pudo conectar a BD");

        }

    }

    public ArrayList<Inscripcion> mostrarInscripto() {

        String sql = "SELECT * FROM inscripcion";
        ArrayList<Inscripcion> lista = new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Inscripcion ins = new Inscripcion();
                ins.setIdInscripto(rs.getInt("idInscripto"));
                ins.setIdAlumno(rs.getInt("idAlumno"));
                ins.setIdMateria(rs.getInt("idMateria"));
                  ins.setNota(rs.getDouble("nota"));
               
              
              
              
                lista.add(ins);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo conectar a la BD");
        }

        return lista;

    }

    public void eliminarInscripcion(int idAlumno, int idMateria) {

        String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND IdMateria=? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas == 1) {
                JOptionPane.showMessageDialog(null, "inscripcion eliminada ");

            }

            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se pudo conectar a la BD");
        }

    }
    
   public ArrayList <Materia> mostrarMateriasNOInscriptas(int idAlumno){
       ArrayList<Materia> materiasNo = new ArrayList<>();
            String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria NOT in (SELECT idMateria FROM inscripcion WHERE inscripcion.idAlumno = ? ) ";
      try {
             PreparedStatement ps = con.prepareStatement(sql);
             
             ps.setInt(1, idAlumno);
             ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setIdMateria(rs.getInt("idMateria"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAnio(rs.getInt("año"));
            materia.setEstado(rs.getBoolean("estado"));
            materiasNo.add(materia);
        }
        
        ps.close();
      
      }catch (SQLException e){
      
              JOptionPane.showMessageDialog(null, "Error en la conexion");
      }
       return materiasNo;
   }
    
   public ArrayList <Materia> mostrarMateriasInscriptas(int idAlumno){
    
          ArrayList<Materia> materias = new ArrayList<>();
       
    String sql = "SELECT inscripcion.idMateria,nombre,año,materia.estado FROM inscripcion,materia WHERE inscripcion.idMateria= materia.idMateria  AND inscripcion.idAlumno = ?;";
 
    
    try {
        PreparedStatement ps = con.prepareStatement(sql);
       
        ps.setInt(1, idAlumno); 
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Materia materia = new Materia();
            materia.setIdMateria(rs.getInt("idMateria"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAnio(rs.getInt("año"));
            materia.setEstado(rs.getBoolean("estado"));
            materias.add(materia);
        }
        
        ps.close();
    } catch (SQLException ex) {
       
        JOptionPane.showMessageDialog(null, "error 404 al obtener materias inscriptas ");
       
    }
    
    return materias; 
}
}

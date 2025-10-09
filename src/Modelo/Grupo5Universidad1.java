/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Persistencia.AlumnoData;

/**
 *
 * @author Sutara
 */
public class Grupo5Universidad1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // Conectando con la base de datos
      Conexion con = new Conexion("jdbc:mariadb://localhost:3306/grupogp5universidad", "root","");
    
      con.buscarConexion();
     
 
      
    //LocalDate fecha1=LocalDate.of(1990,3,27);
    //Alumno alum=new Alumno(1,"34845223","Alcaraz", "Fatima",fecha1,true ); 
    AlumnoData alumno1=new AlumnoData(con);
    //alumno1.actualizarAlumno(alum);
    /*alumno1.guardarAlumno(alum);
     
    LocalDate fecha2=LocalDate.of(1986,8,19);
    Alumno alum1=new Alumno(-1,"31123456","Ballerini", "Gabriela",fecha2,true ); 
    AlumnoData alumno2=new AlumnoData(con);
    alumno2.guardarAlumno(alum1);
    
    LocalDate fecha3=LocalDate.of(2001,12,5);
    Alumno alum2=new Alumno(-1,"40567890","Cruz", "Tomas",fecha3,true ); 
    AlumnoData alumno3=new AlumnoData(con);
    alumno3.guardarAlumno(alum2);
    
    LocalDate fecha4=LocalDate.of(1997,6,15);
    Alumno alum3=new Alumno(-1,"37900123","Delicia", "Brian",fecha4,true ); 
    AlumnoData alumno4=new AlumnoData(con);
    alumno4.guardarAlumno(alum3);
   
    LocalDate fecha5=LocalDate.of(1999,7,10);
    Alumno alum4=new Alumno(-1,"39000789","Alvarez", "Diego",fecha4,true ); 
    AlumnoData alumno5=new AlumnoData(con);
    alumno5.guardarAlumno(alum4);*/
     
    

      
    //actualizamos fecha y nombre
    /*LocalDate fecha2=LocalDate.of(1999,9,15);
    Alumno alumnito2=new Alumno(5,"39000789","Alvarez", "Martin",fecha2,false );   
    AlumnoData alumn6=new AlumnoData(con);*/
    
    //alumn6.actualizarAlumno(alumnito2);
    //alumn6.bajaLogica(alumnito2);
    //alumn6.eliminarAlumno(alumnito2);
    //alumno1.selectEspecifico(alum);
        System.out.println("------------------------------------");
       Alumno alumnoEncontrado= alumno1.buscarAlumnoPorDni("31123456");
        System.out.println(alumnoEncontrado.getApellido());
        System.out.println(alumnoEncontrado.getDni());
        System.out.println("------------------------------------");
   
    }
    
}

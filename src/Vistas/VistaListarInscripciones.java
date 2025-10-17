
package Vistas;

import Modelo.Alumno;
import Modelo.Conexion;
import Modelo.Inscripcion;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.InscripcionData;
import Persistencia.MateriaData;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class VistaListarInscripciones extends javax.swing.JInternalFrame {

    
      private Conexion con = new Conexion("jdbc:mariadb://localhost:3306/grupogp5universidad", "root","");
    private ArrayList <Alumno> listaAlumnos;
    
    private AlumnoData alumdata ;
   
    private InscripcionData inscrData;
    private DefaultTableModel modelo;
    
    
    
    
    
    public VistaListarInscripciones() {
    
        
        inscrData=new InscripcionData(con);
        alumdata=new AlumnoData(con);
        modelo=new DefaultTableModel();
        initComponents();
        cargarCabecera();
        cargarAlumnos();
        listaAlumnos=new ArrayList<Alumno>();
        
        
        
               
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablaInscriptos = new javax.swing.JTable();
        Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        cbAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnoActionPerformed(evt);
            }
        });

        jtablaInscriptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtablaInscriptos);

        Salir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione el alumno");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lista de Inscripciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Salir)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnoActionPerformed
      
        borrarFilaColumnas();
   
   Alumno nuevo = (Alumno)cbAlumno.getSelectedItem();
   int id = nuevo.getIdAlumno();
    
     for (Inscripcion alu : inscrData.mostrarInscripto() ) {
         if(id== alu.getIdAlumno()){
             modelo.addRow(new Object[] {
            alu.getIdInscripto(),
            alu.getIdAlumno(),
            alu.getIdMateria(),
            alu.getNota()
                   
             
        });
         
               
         }
                
              }
 
              
        
    }//GEN-LAST:event_cbAlumnoActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
      dispose();
    }//GEN-LAST:event_SalirActionPerformed

   

    
    
     public void cargarCabecera(){
        
        modelo.addColumn("Id Incripto");
        modelo.addColumn("Id Alumno");
        modelo.addColumn("Materia");
        modelo.addColumn("Nota");
        
        jtablaInscriptos.setModel(modelo);
        
    }

    public void cargarAlumnos(){
        
        
        
     listaAlumnos=alumdata.listarAlumnos();
          
        for (Alumno alumno : listaAlumnos) {
            
            cbAlumno.addItem(alumno);
            
        }
        
       
        
    
    }
    
    public void borrarFilaColumnas(){
         int indice = modelo.getRowCount()-1;
            for(int i=indice; i>=0 ;i --){
                 modelo.removeRow(i);
                     }
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salir;
    private javax.swing.JComboBox<Alumno> cbAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtablaInscriptos;
    // End of variables declaration//GEN-END:variables
}

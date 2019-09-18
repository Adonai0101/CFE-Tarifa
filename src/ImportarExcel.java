
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class ImportarExcel extends javax.swing.JFrame {
    
    Funciones f =  new Funciones();
    
     Conexion con =  new Conexion();
     Connection cn = con.conectar();


    public ImportarExcel() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/cfe.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Tarifas = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Ruta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tecnologia.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Seleccione la tarifa a Importar");

        Tarifas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Tarifas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GDBT", "GDBTH", "GDMTO", "PDBT", "RABT", "RAMT", "APBT", "APMT", "DIST" }));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/flecha-hacia-la-izquierda.png"))); // NOI18N
        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busqueda.png"))); // NOI18N
        jButton2.setText("Buscar Archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Ruta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(Tarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(Ruta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(35, 35, 35)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Tarifas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Ruta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean ban = true;
        int bandera  = 0;
        if(Ruta.getText().equals("")){
            JOptionPane.showMessageDialog(this, "No se ha Seleccionado archivo","",JOptionPane.WARNING_MESSAGE);
            bandera = 1;
        }
        else{
            String temp =  Ruta.getText();
            String var = f.tipoArchivo(temp);
            if(var.equals(".xlsx" ) || var.equals(".xlsx")){
                ban =  false;
                bandera = 0;
            }
            else{
                ban = true;
                bandera = 1;
                JOptionPane.showMessageDialog(this, "Tipo de archivo invalido\n solo se admiten archivos \n tipo .xlsx","",JOptionPane.WARNING_MESSAGE);
            }       
        }
        
        if(bandera == 0){
            /*Elegimos la tabla a Importar*/
            String TableName = Tarifas.getSelectedItem().toString();
            
            int resp = JOptionPane.showConfirmDialog(this, "Recuerda que esta Accion podria dañar la base de Datos \n \n Desea Continuar","",JOptionPane.WARNING_MESSAGE);
            if(resp == 0){
               
               try{
                    /*Eliminamos los Registros de la base de datos*/
                    PreparedStatement pps =  cn.prepareStatement("DELETE FROM " + Tarifas.getSelectedItem());
                    pps.executeUpdate();
                    
               }catch(Exception e){}
               
               /*Leeamos el archivo excel*/
                String archivo = Ruta.getText();
                File file =  new File(archivo);
                LeerExcel obj = new LeerExcel(file);
               
               
               
               
               switch(f.tipoConsulta(Tarifas.getSelectedItem() + "")){
                    case 0:
                    break;
                    case 1:
                        
                        for(int x = 1; x < obj.arg.length; x++){
                            String[] temporal = obj.arg[x].split(" ");
                            try{
                                PreparedStatement pps =  cn.prepareStatement("INSERT INTO "+ TableName +" ('Anio','Mes','TRANSMISION','DISTRIBUCION','CENACE','SUMINISTRO','SCNMEM','ENERGIA-B','ENERGIA-I','ENERGIA-P','CAPACIDAD')VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                                pps.setString(1,temporal[0]);
                                pps.setString(2,temporal[1]);
                                pps.setString(3,temporal[2]);
                                pps.setString(4,temporal[3]);
                                pps.setString(5,temporal[4]);
                                pps.setString(6,temporal[5]);
                                pps.setString(7,temporal[6]);
                                pps.setString(8,temporal[7]);
                                pps.setString(9,temporal[8]);
                                pps.setString(10,temporal[9]);
                                pps.setString(11,temporal[10]);

                                pps.executeUpdate();
                                
                               
                            }catch(Exception e){}    
                            JOptionPane.showMessageDialog(this, "Se importo Correctamente");
                        }                                                  
                    break;
                    
                    case 2:
                            for(int x = 1; x < obj.arg.length; x++){
                            String[] temporal = obj.arg[x].split(" ");
                                try{
                                    
                                    PreparedStatement pps =  cn.prepareStatement("INSERT INTO "+ TableName +" ('Anio','Mes','TRANSMISION','DISTRIBUCION','CENACE','SUMINISTRO','SCNMEM','ENERGIA','CAPACIDAD')VALUES(?,?,?,?,?,?,?,?,?)");
                                    pps.setString(1,temporal[0]);
                                    pps.setString(2,temporal[1]);
                                    pps.setString(3,temporal[2]);
                                    pps.setString(4,temporal[3]);
                                    pps.setString(5,temporal[4]);
                                    pps.setString(6,temporal[5]);
                                    pps.setString(7,temporal[6]);
                                    pps.setString(8,temporal[7]);
                                    pps.setString(9,temporal[8]);

                                    pps.executeUpdate();
                                    
                                    
                                }catch(Exception e){}        
                             }
                            JOptionPane.showMessageDialog(this, "Se importo Correctamente");
                    break;
               }
                
            } // fin resp

           Ruta.setText(""); // limpiamos la Ruta
        }
        else{
            //JOptionPane.showMessageDialog(this, "Tipo de archivo invalido\n solo se admiten archivos \n tipo .xlsx","",JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser dlg =  new JFileChooser();
        int op = dlg.showSaveDialog(this);
        if(op == JFileChooser.APPROVE_OPTION){
            File f =  dlg.getSelectedFile();
            Ruta.setText(f.toString());
           
            System.out.println("La ruta del archivo es " + Ruta.getText());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportarExcel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ruta;
    private javax.swing.JComboBox<String> Tarifas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

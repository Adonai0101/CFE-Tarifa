
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ExportarExcel extends javax.swing.JFrame {
    
        /*Conectamos a la BD*/
//        Conexion con =  new Conexion();
//        Connection cn = con.conectar();
        String arg[][];
        String header[];
        
        /*Importamos la funciones*/
        Funciones f =  new Funciones();
        
        String TableName = "";

    public ExportarExcel() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/cfe.png")).getImage());
        TableName = Tarifas.getSelectedItem() + "";
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Tarifas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Ruta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exportar .png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione la Tarifa a Exportar");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Tarifas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Tarifas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GDBT", "GDBTH", "GDMTO", "PDBT", "RABT", "RAMT", "APBT", "APMT", "DIST" }));
        Tarifas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TarifasItemStateChanged(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exp.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel5.setText("Exportar");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Ruta");

        Ruta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Guardar como");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Tarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40)
                        .addComponent(Ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Tarifas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(Ruta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TarifasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TarifasItemStateChanged

        
        TableName = Tarifas.getSelectedItem() + "";
    }//GEN-LAST:event_TarifasItemStateChanged

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
            
        
        Conexion con =  new Conexion();
        Connection cn = con.conectar();
        
            TableName = Tarifas.getSelectedItem() + "";
            
            int NumeroFilas = 0;
            int NumeroColumnas = 0;
            
            switch(f.tipoConsulta(TableName)){
                case 0:
                    JOptionPane.showMessageDialog(this, "No Disponible");
                    NumeroColumnas = 10; // esto es super momentanio
                    break;
                case 1:
                    NumeroColumnas = 12;
                    break;
                case 2:
                    NumeroColumnas = 10;
                    break;    
            }
            
            
            /*----------------------------------------------*/
             String sql = "SELECT COUNT(*) FROM " +TableName;
            
            Statement st;
            try{
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                NumeroFilas = Integer.parseInt(rs.getString(1));
            }catch(Exception e){}
            
            /*
                Creamos el arreglo con dimenciones de la tabla de la bd
                Esto Para utilizar nuestro Metodo y crear El archivo Excel
            */
            
            arg = new String[NumeroFilas + 1][NumeroColumnas];
            header =  new String[NumeroColumnas];
            
            //Gfuardamos la base de datos en un arreglo
            
            
            
            /*
                Verificamos que tarifa es para haci hacer la exportacion
            */
            switch(f.tipoConsulta(TableName)){
                case 0:
                    JOptionPane.showMessageDialog(this, "No Disponible");
                    NumeroColumnas = 10; // esto es super momentanio
                    break;
                case 1:
//                    header[0]  = "ID";
                    header[0]  = "AÑO";
                    header[1] = "MES";
                    header[2]  = "TRANSMICION";
                    header[3]  = "DISTRIBUCION";
                    header[4]  = "CENACE";
                    header[5]  = "SUMINISTRO";
                    header[6]  = "SCNMEM";
                    header[7]  = "ENERGIA-B";
                    header[8]  = "ENERGIA-I";
                    header[9]  = "ENERGIA-P";
                    header[10]  = "CAPACIDAD";
                    
                    
                    sql = "SELECT * FROM " + TableName;
                    System.out.println(sql);
                    int cont = 0;
                    try{
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        
                        while(rs.next()){
                            
                            arg[cont][0] = rs.getString(2);
                            arg[cont][1] = rs.getString(3);
                            arg[cont][2] = rs.getString(4);
                            arg[cont][3] = rs.getString(5);
                            arg[cont][4] = rs.getString(6);
                            arg[cont][5] = rs.getString(7);
                            arg[cont][6] = rs.getString(8);
                            arg[cont][7] = rs.getString(9);
                            arg[cont][8] = rs.getString(10);
                            arg[cont][9] = rs.getString(11);
                            arg[cont][10] = rs.getString(12);
                            //arg[cont][11] = rs.getString(12);
                            cont++; 
                        }
                    }catch(Exception e){}
                    
                    
                    
                    
                    break;
                case 2:
                    //header[0]  = "ID";
                    header[0]  = "AÑO";
                    header[1] = "MES";
                    header[2]  = "TRANSMICION";
                    header[3]  = "DISTRIBUCION";
                    header[4]  = "CENACE";
                    header[5]  = "SUMINISTRO";
                    header[6]  = "SCNMEM";
                    header[7]  = "ENERGIA";
                    header[8]  = "CAPACIDAD";
                    
                    
                    sql = "SELECT * FROM " + TableName;
                    System.out.println(sql);
                    int contt = 0;
                    try{
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        
                        while(rs.next()){
                            
                            arg[contt][0] = rs.getString(2);
                            arg[contt][1] = rs.getString(3);
                            arg[contt][2] = rs.getString(4);
                            arg[contt][3] = rs.getString(5);
                            arg[contt][4] = rs.getString(6);
                            arg[contt][5] = rs.getString(7);
                            arg[contt][6] = rs.getString(8);
                            arg[contt][7] = rs.getString(9);
                            arg[contt][8] = rs.getString(10);
                            
                            contt++; 
                        }
                    }catch(Exception e){}
                    break;    
            }
                       
           /*Generamos el EXEL*/
           
           if(Ruta.getText().equals("")){
               int resp = JOptionPane.showConfirmDialog(this, "No has Seleccionado una ruta.. \n Si deseas continuar el archivo se guardara como \n\n 'exp.xls' en el directorio Rais \n \n","",JOptionPane.WARNING_MESSAGE);
               System.out.println(resp);
               if(resp == 0){
                   try{
                        //f.generarExel(arg, "./" + NombreArchivo.getText() + ".xls"); // Prueba
                        f.EscribirEXCEL(header,arg, Ruta.getText() + ".xlsx");
                        JOptionPane.showMessageDialog(this, "Archivo Generado");
                        Ruta.setText("");
                     }catch(Exception e ){JOptionPane.showMessageDialog(this, "Algo salio Mal: " + e);}
               }
           }
           else{
               try{
                    //f.generarExel(arg, "./" + NombreArchivo.getText() + ".xls"); // Prueba
                    f.EscribirEXCEL(header,arg, Ruta.getText() + ".xlsx");
                    JOptionPane.showMessageDialog(this, "Archivo Generado");
                    Ruta.setText("");
                }catch(Exception e ){JOptionPane.showMessageDialog(this, "Algo salio Mal: " + e);}
           }

            /*aqui cerramos la coneccion a la bd*/
            con.cerrar();
            
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser dlg =  new JFileChooser();
        int op = dlg.showSaveDialog(this);
        if(op == JFileChooser.APPROVE_OPTION){
            File f =  dlg.getSelectedFile();
            Ruta.setText(f.toString());
            System.out.println("Ya te dije q si sigue");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ExportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExportarExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExportarExcel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ruta;
    private javax.swing.JComboBox<String> Tarifas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
public class CostosTarifas extends javax.swing.JFrame {
    
        /*Conectamos a la BD*/
        Conexion con =  new Conexion();
        Connection cn = con.conectar();
        
        /*Importamos Funciones auxiliares*/
        Funciones f = new Funciones();
        
        String TableName = ""; // Variable Guarda el nombre de la tabla a la que se le ban a aplicar los cambios
        
        String consulta; //Variable que guarda la Consulta a hacer
        
        String Clave = ""; //Guardara el id de lña bd para hacer operaciones
        
        boolean bandera =  false;
        
        

    public CostosTarifas() {
        initComponents();
        // estas dos lineas son para cerra de forma mas adecuada nuestro frame 
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/cfe.png")).getImage());
        LlenarComboAnio();
        LlenarComboMes();
        
        ResTarifa.setText(TipoTarifa.getSelectedItem() + ""); // obtener el tipo tarifa en laver
        
        /*Obtenemos el nombre de la tabla a la que se van a hacer los cambios*/
        
        TableName = TipoTarifa.getSelectedItem() + "";
        consulta = "SELECT * FROM " + TableName;
      
        switch(f.tipoConsulta(TableName)){
            case 0:
                JOptionPane.showMessageDialog(this, "No Disponible");
                break;
            case 1:
                ConsultaUno(consulta);
                break;
            case 2:
                ConsultaDos(consulta);
                EnergiaI.setEnabled(false);
                EnergiaP.setEnabled(false);
                
                LabelEnergiaI.setEnabled(false);
                LabelEnergiaP.setEnabled(false);
                
                LabelEnergiaB.setText("Energia");
                
                LabelCapacidad.setText("Capacidad");
                break;    
        }
        
        /*Deshabilitar Botones*/
        Modificar.setEnabled(false);
        Eliminar.setEnabled(false);
        Copiar.setEnabled(false);
        
        /*Aqui se hace todo para ver la fecha y relog*/
        Date sistFecha = new Date();
        SimpleDateFormat formato=new SimpleDateFormat("dd-MMM-YYYY");
        fecha.setText(formato.format(sistFecha));
        //hora del sistema
        Timer tiempo=new Timer(100,new CostosTarifas.horas());
        tiempo.start();
    }
    
     class horas implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            Date sistHora =  new Date();
            String pmAm = "hh:mm:ss a";
            SimpleDateFormat format = new SimpleDateFormat(pmAm);
            Calendar hoy =  Calendar.getInstance();
            hora.setText(String.format(format.format(sistHora),hoy));
        }
    }
     

    public void ConsultaUno(String sql){ /*Esta Consulta es para unicamente dos tablas */
        DefaultTableModel modelo =  new DefaultTableModel();
        Tabla.setModel(modelo);
        modelo.addColumn("id");
        modelo.addColumn("Año");
        modelo.addColumn("Mes");
        modelo.addColumn("TRANSMISION");
        modelo.addColumn("DISTRIBUCION");
        modelo.addColumn("CENACE");
        modelo.addColumn("SUMINISTRO");
        modelo.addColumn("SCNMEM");
        modelo.addColumn("ENERGIA-B");
        modelo.addColumn("ENERGIA-I");
        modelo.addColumn("ENERGIA-P");
        modelo.addColumn("CAPACIDAD-D");
        String datos[] =  new String[12];
        Statement st;
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                  
                modelo.addRow(datos);
            }
            Tabla.setModel(modelo);
        }catch(Exception e){System.out.println(e + " Consulta uno");}
    }
    
    
        
     public void ConsultaDos(String sql){ /*Esta Consulta es para la Mayoria de las tablas*/
        DefaultTableModel modelo =  new DefaultTableModel();
        Tabla.setModel(modelo);
        modelo.addColumn("id");
        modelo.addColumn("Año");
        modelo.addColumn("Mes");
        modelo.addColumn("TRANSMISION");
        modelo.addColumn("DISTRIBUCION");
        modelo.addColumn("CENACE");
        modelo.addColumn("SUMINISTRO");
        modelo.addColumn("SCNMEM");
        modelo.addColumn("ENERGIA");
        modelo.addColumn("CAPACIDAD");
        String datos[] =  new String[10];
        
        Statement st;
        try{
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);                  
                modelo.addRow(datos);
            }
            Tabla.setModel(modelo);
        }catch(Exception e){System.out.println(e + " Consulta Dos");}
    }
    
    
    /*Metodos Auxiliares*/
    public void LlenarComboAnio(){
        Date fecha =  new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy");
        int AnioActual =  Integer.parseInt(formato.format(fecha));
        int inicio = AnioActual - 2;
        int cont = 0;
        while(inicio <= AnioActual){
            ComboAnio.addItem(AnioActual - cont +"");
            cont++;
            inicio++;
        } 
    }
    
    public void LlenarComboMes(){
        String[] Arreglo = {"Enero", "Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        Date fecha =  new Date();
        SimpleDateFormat formato = new SimpleDateFormat("MM");
        int mes =  Integer.parseInt(formato.format(fecha)) - 1;
        int cont = 0;
        while(cont < 12){
            cont++;
            comboMes.addItem(Arreglo[mes]);
            if(mes == 11){
                mes = 0;
            }
            else{
                mes++;
            }
        }  
    }
    public void limpiar(){
        Transmicion.setText("");
        Distribucion.setText("");
        Cenace.setText("");
        Suministro.setText("");
        SCNMEM.setText("");
        EnergiaB.setText("");
        EnergiaI.setText("");
        EnergiaP.setText("");
        Capacidad.setText("");
        /*rECUPERAMOS EL COLOR ORIGINAL DE LOS lABELS*/
        L1.setForeground(Color.BLACK);
        
        
        /*Deshabilitar Botones*/
        Modificar.setEnabled(false);
        Eliminar.setEnabled(false);
        Copiar.setEnabled(false);
        /*Activamos agregar*/
        Agregar.setEnabled(true);
        
        bandera = false;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ResTarifa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ComboAnio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboMes = new javax.swing.JComboBox<>();
        L1 = new javax.swing.JLabel();
        Transmicion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LabelEnergiaB = new javax.swing.JLabel();
        LabelEnergiaI = new javax.swing.JLabel();
        LabelEnergiaP = new javax.swing.JLabel();
        LabelCapacidad = new javax.swing.JLabel();
        Distribucion = new javax.swing.JTextField();
        Cenace = new javax.swing.JTextField();
        Suministro = new javax.swing.JTextField();
        SCNMEM = new javax.swing.JTextField();
        EnergiaB = new javax.swing.JTextField();
        EnergiaI = new javax.swing.JTextField();
        EnergiaP = new javax.swing.JTextField();
        Capacidad = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TipoTarifa = new javax.swing.JComboBox<>();
        Agregar = new javax.swing.JLabel();
        Eliminar = new javax.swing.JLabel();
        Modificar = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Copiar = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Costos de tarifas ");

        Tabla.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Año", "Mes", "TRANSMISION-1", "DISTRIBUCION", "CENACE-1", "SUMINISTRO", "SCNMEM-1", "ENERGIA-B", "ENERGIA-I", "ENERGIA-P", "CAPACIDAD-D"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Tipo de Tarifa:");

        ResTarifa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ResTarifa.setText("ResTarifa");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busqueda.png"))); // NOI18N

        jTextField1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTextField1.setText("Campo para hacer busqueda");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Año");

        ComboAnio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Mes");

        comboMes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        L1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        L1.setText("Transmición");

        Transmicion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Transmicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TransmicionKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Distribución");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("CENACE -1 ");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Suministro");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("SCNMEM -1");

        LabelEnergiaB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LabelEnergiaB.setText("Energia - B");

        LabelEnergiaI.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LabelEnergiaI.setText("Energia - I");

        LabelEnergiaP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LabelEnergiaP.setText("Energia - P");

        LabelCapacidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        LabelCapacidad.setText("Capacidad - D");

        Distribucion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Distribucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DistribucionKeyTyped(evt);
            }
        });

        Cenace.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cenace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CenaceKeyTyped(evt);
            }
        });

        Suministro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Suministro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SuministroKeyTyped(evt);
            }
        });

        SCNMEM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SCNMEM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SCNMEMKeyTyped(evt);
            }
        });

        EnergiaB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        EnergiaB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EnergiaBKeyTyped(evt);
            }
        });

        EnergiaI.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        EnergiaI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EnergiaIKeyTyped(evt);
            }
        });

        EnergiaP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        EnergiaP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EnergiaPKeyTyped(evt);
            }
        });

        Capacidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CapacidadKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Tipo de Tarifa");

        TipoTarifa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TipoTarifa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GDBT", "GDBTH", "GDMTO", "PDBT", "RABT", "RAMT", "APBT", "APMT", "DIST" }));
        TipoTarifa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipoTarifaItemStateChanged(evt);
            }
        });

        Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AgregarMouseClicked(evt);
            }
        });

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Eliminar.png"))); // NOI18N
        Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarMouseClicked(evt);
            }
        });

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Modificar.png"))); // NOI18N
        Modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarMouseClicked(evt);
            }
        });

        jLabel20.setText("Agregar");

        jLabel21.setText("Modificar");

        jLabel22.setText("Eliminar");

        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar.png"))); // NOI18N
        Limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LimpiarMouseClicked(evt);
            }
        });

        jLabel24.setText("Limpiar");

        Copiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copia.png"))); // NOI18N
        Copiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CopiarMouseClicked(evt);
            }
        });

        jLabel26.setText("Copiar");

        fecha.setText("fecha");

        hora.setText("Hora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Modificar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Eliminar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Limpiar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel24)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecha)
                        .addGap(24, 24, 24)
                        .addComponent(hora))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Copiar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(LabelEnergiaB)
                            .addComponent(LabelEnergiaI)
                            .addComponent(LabelEnergiaP)
                            .addComponent(LabelCapacidad)
                            .addComponent(L1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(EnergiaP)
                                .addComponent(EnergiaI)
                                .addComponent(SCNMEM)
                                .addComponent(EnergiaB)
                                .addComponent(Capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Distribucion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Transmicion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Cenace)
                                .addComponent(Suministro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TipoTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(ResTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(496, 496, 496))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResTarifa))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(ComboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(TipoTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L1)
                            .addComponent(Transmicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Distribucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Cenace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Suministro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(SCNMEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelEnergiaB)
                            .addComponent(EnergiaB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelEnergiaI)
                            .addComponent(EnergiaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelEnergiaP)
                            .addComponent(EnergiaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelCapacidad)
                            .addComponent(Capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Limpiar)
                            .addComponent(Copiar)
                            .addComponent(Agregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(fecha)
                                .addComponent(hora))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TransmicionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TransmicionKeyTyped
       
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           L1.setForeground(Color.RED);
           
           evt.consume();
       } 
       if(f.Numero(evt.getKeyChar() + "")){
           L1.setForeground(Color.BLACK);
       }
       
       
       
    }//GEN-LAST:event_TransmicionKeyTyped

    private void DistribucionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DistribucionKeyTyped
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_DistribucionKeyTyped

    private void CenaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CenaceKeyTyped
      if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_CenaceKeyTyped

    private void SuministroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuministroKeyTyped
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_SuministroKeyTyped

    private void SCNMEMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SCNMEMKeyTyped
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_SCNMEMKeyTyped

    private void EnergiaBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnergiaBKeyTyped
        if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_EnergiaBKeyTyped

    private void EnergiaIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnergiaIKeyTyped
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_EnergiaIKeyTyped

    private void EnergiaPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnergiaPKeyTyped
        if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_EnergiaPKeyTyped

    private void CapacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CapacidadKeyTyped
       if(!f.Numero(evt.getKeyChar() + "")){   
           f.sonido();
           evt.consume();
           
       } 
    }//GEN-LAST:event_CapacidadKeyTyped

    private void LimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_LimpiarMouseClicked

    private void AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarMouseClicked
        
        // VALIDAMOS QUE NO SE REPITA EL MES NI EL AÑO EN EL REGISTRO
       int x = 0;
        Statement st;
        
        try{
            //SELECT COUNT ('2019') from GDBT where mes = 'Mayo';
            String anio = ComboAnio.getSelectedItem().toString();
            String mes =  comboMes.getSelectedItem().toString();
            String sql = "SELECT COUNT('"+anio+"') FROM " + TableName+ " WHERE MES = '" + mes +"'" ; 
            
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            x = Integer.parseInt(rs.getString(1));
        
        }catch(Exception e){System.out.println("Algo Salio Muy Mal ");}     
        
        if(x != 0){
            String temp = "Registro Duplicado\n Usted esta intentando registrar una tarifa ya existente\n";
            JOptionPane.showMessageDialog(this, temp);
        }
        else{
            int error = 0;
            consulta = "SELECT *  FROM " + TableName;
        
            switch(f.tipoConsulta(TableName)){
                case 0:
                    JOptionPane.showMessageDialog(this, "No Disponible");
                    break;
                case 1:
                    if(Transmicion.getText().equals("")){error++;}
                    if(Distribucion.getText().equals("")){error++;}
                    if(Cenace.getText().equals("")){error++;}
                    if(Suministro.getText().equals("")){error++;}
                    if(SCNMEM.getText().equals("")){error++;}
                    if(EnergiaB.getText().equals("")){error++;}
                    if(EnergiaI.getText().equals("")){error++;}
                    if(EnergiaP.getText().equals("")){error++;}
                    if(Capacidad.getText().equals("")){error++;}
        
                    if (error == 0){
                        try{
                            PreparedStatement pps =  cn.prepareStatement("INSERT INTO "+ TableName +" ('Anio','Mes','TRANSMISION','DISTRIBUCION','CENACE','SUMINISTRO','SCNMEM','ENERGIA-B','ENERGIA-I','ENERGIA-P','CAPACIDAD')VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                            pps.setString(1,ComboAnio.getSelectedItem()+"");
                            pps.setString(2,comboMes.getSelectedItem()+"");
                            pps.setString(3,Transmicion.getText());
                            pps.setString(4,Distribucion.getText());
                            pps.setString(5,Cenace.getText());
                            pps.setString(6,Suministro.getText());
                            pps.setString(7,SCNMEM.getText());
                            pps.setString(8,EnergiaB.getText());
                            pps.setString(9,EnergiaI.getText());
                            pps.setString(10,EnergiaP.getText());
                            pps.setString(11,Capacidad.getText());

                            pps.executeUpdate();
                            JOptionPane.showMessageDialog(this,"Nuevo Registro Exitoso");

                        }catch(Exception e){JOptionPane.showMessageDialog(this,"Fatal Error " + e.getMessage(),"",JOptionPane.ERROR_MESSAGE);}
                    }
                    else{JOptionPane.showMessageDialog(null, "Revise todos los Campos", "Error de validación", JOptionPane.WARNING_MESSAGE);}
                    
                    ConsultaUno(consulta);
                    limpiar();
                    break;
                
                case 2:
                    
                    if(Transmicion.getText().equals("")){error++;}
                    if(Distribucion.getText().equals("")){error++;}
                    if(Cenace.getText().equals("")){error++;}
                    if(Suministro.getText().equals("")){error++;}
                    if(SCNMEM.getText().equals("")){error++;}
                    if(EnergiaB.getText().equals("")){error++;}
                    if(Capacidad.getText().equals("")){error++;}
                    
                    if (error == 0){
                        try{
                            PreparedStatement pps =  cn.prepareStatement("INSERT INTO "+ TableName +" ('Anio','Mes','TRANSMISION','DISTRIBUCION','CENACE','SUMINISTRO','SCNMEM','ENERGIA','CAPACIDAD')VALUES(?,?,?,?,?,?,?,?,?)");
                            pps.setString(1,ComboAnio.getSelectedItem()+"");
                            pps.setString(2,comboMes.getSelectedItem()+"");
                            pps.setString(3,Transmicion.getText());
                            pps.setString(4,Distribucion.getText());
                            pps.setString(5,Cenace.getText());
                            pps.setString(6,Suministro.getText());
                            pps.setString(7,SCNMEM.getText());
                            pps.setString(8,EnergiaB.getText());
                            pps.setString(9,Capacidad.getText());

                            pps.executeUpdate();
                            JOptionPane.showMessageDialog(this,"Nuevo Registro Exitoso");
                            limpiar();

                        }catch(Exception e){JOptionPane.showMessageDialog(this,"Fatal Error " + e.getMessage(),"",JOptionPane.ERROR_MESSAGE);}
                    }
                    else{JOptionPane.showMessageDialog(null, "Revise todos los Campos", "Error de validación", JOptionPane.WARNING_MESSAGE);}
                    
                    ConsultaDos(consulta);
                    break;      
            }   
        }
        
        
    }//GEN-LAST:event_AgregarMouseClicked

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        /*Aqui haremos la validacion si el Registyro ya Existe*/
        bandera = true;
        
        String sql = "SELECT COUNT(*) FROM " +TableName;
        int resultados = 0;    
            Statement st;
            try{
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                resultados = Integer.parseInt(rs.getString(1));
            }catch(Exception e){}
        
         switch(f.tipoConsulta(TableName)){
            case 0:
                JOptionPane.showMessageDialog(this, "No Disponible");
                break;
            case 1:
                try{
                    DefaultTableModel modelo =  new DefaultTableModel();
                    int fila = Tabla.rowAtPoint(evt.getPoint());

                        Transmicion.setText(Tabla.getValueAt(fila, 3).toString());
                        Distribucion.setText(Tabla.getValueAt(fila, 4).toString());
                        Cenace.setText(Tabla.getValueAt(fila, 5).toString());
                        Suministro.setText(Tabla.getValueAt(fila, 6).toString());
                        SCNMEM.setText(Tabla.getValueAt(fila, 7).toString());
                        EnergiaB.setText(Tabla.getValueAt(fila, 8).toString());
                        EnergiaI.setText(Tabla.getValueAt(fila, 9).toString());
                        EnergiaP.setText(Tabla.getValueAt(fila, 10).toString());
                        Capacidad.setText(Tabla.getValueAt(fila, 11).toString());

                        Clave = Tabla.getValueAt(fila, 0).toString();

                        /*Activamos Botones*/    
                        Modificar.setEnabled(true);
                        Eliminar.setEnabled(true);
                        Copiar.setEnabled(true);
                        /*Desactivamos agregar*/
                        Agregar.setEnabled(false);
                
              
                }catch(Exception e){System.out.println(e);}
                
                break;
            case 2:
                try{
                    DefaultTableModel modelo =  new DefaultTableModel();
                    int fila = Tabla.rowAtPoint(evt.getPoint());

                        Transmicion.setText(Tabla.getValueAt(fila, 3).toString());
                        Distribucion.setText(Tabla.getValueAt(fila, 4).toString());
                        Cenace.setText(Tabla.getValueAt(fila, 5).toString());
                        Suministro.setText(Tabla.getValueAt(fila, 6).toString());
                        SCNMEM.setText(Tabla.getValueAt(fila, 7).toString());
                        EnergiaB.setText(Tabla.getValueAt(fila, 8).toString());
                        Capacidad.setText(Tabla.getValueAt(fila, 9).toString());
                        

                        Clave = Tabla.getValueAt(fila, 0).toString();

                        /*Activamos Botones*/    
                        Modificar.setEnabled(true);
                        Eliminar.setEnabled(true);
                        /*Desactivamos agregar*/
                        Agregar.setEnabled(false);


                }catch(Exception e){System.out.println(e);}
                break;    
        }
        
        
    }//GEN-LAST:event_TablaMouseClicked

    private void EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMouseClicked
        if(bandera == true){
            int resp = JOptionPane.showConfirmDialog(this, "¿Está seguro que quiere eliminar el Rigistro?","",JOptionPane.WARNING_MESSAGE);

            if(resp == 0){
                try{
                    PreparedStatement pps =  cn.prepareStatement("DELETE FROM "+ TableName +" WHERE id = ?");
                    pps.setString(1,Clave);
                    pps.executeUpdate();
                    consulta = "SELECT * FROM " + TableName;

                    JOptionPane.showMessageDialog(this,"Tarifa Eliminada");   
                }catch(Exception e){JOptionPane.showMessageDialog(this,"Fatal Error " + e.getMessage());}
            }

            switch(f.tipoConsulta(TableName)){
                case 0:
                    JOptionPane.showMessageDialog(this, "No Disponible");
                    break;
                case 1:
                    ConsultaUno(consulta);
                    break;
                case 2:
                    ConsultaDos(consulta);
                    break;    
            }
            limpiar();
        }
        else{
            JOptionPane.showMessageDialog(this,"No se selecciono un elemento");
        }
        
    }//GEN-LAST:event_EliminarMouseClicked

    private void ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarMouseClicked
        if(bandera == true){
                int error = 0;
                switch(f.tipoConsulta(TableName)){
                    case 0:
                        JOptionPane.showMessageDialog(this, "No Disponible");
                        break;
                    case 1:
                        if(Transmicion.getText().equals("")){error++;}
                        if(Distribucion.getText().equals("")){error++;}
                        if(Cenace.getText().equals("")){error++;}
                        if(Suministro.getText().equals("")){error++;}
                        if(SCNMEM.getText().equals("")){error++;}
                        if(EnergiaB.getText().equals("")){error++;}
                        if(EnergiaI.getText().equals("")){error++;}
                        if(EnergiaP.getText().equals("")){error++;}
                        if(Capacidad.getText().equals("")){error++;}

                        if(error == 0){

                            try{                                                            
                                PreparedStatement pps =  cn.prepareStatement("Update "+TableName+" SET Anio = ?, Mes = ?, 'TRANSMISION' = ?, DISTRIBUCION = ?, 'CENACE' = ?, SUMINISTRO = ?, 'SCNMEM' = ?, 'ENERGIA-B' = ?, 'ENERGIA-I' = ?,'ENERGIA-P' = ?,'CAPACIDAD' = ? WHERE id = ?;");
                                    pps.setString(1,ComboAnio.getSelectedItem()+"");
                                    pps.setString(2,comboMes.getSelectedItem()+"");
                                    pps.setString(3,Transmicion.getText());
                                    pps.setString(4,Distribucion.getText());
                                    pps.setString(5,Cenace.getText());
                                    pps.setString(6,Suministro.getText());
                                    pps.setString(7,SCNMEM.getText());
                                    pps.setString(8,EnergiaB.getText());
                                    pps.setString(9,EnergiaI.getText());
                                    pps.setString(10,EnergiaP.getText());
                                    pps.setString(11,Capacidad.getText());

                                    pps.setString(12,Clave);

                                    pps.executeUpdate();

                                JOptionPane.showMessageDialog(this,"Tarifa Actualizada");

                            }catch(Exception e){JOptionPane.showMessageDialog(this,"Fatal Error" + e);}
                        }
                        else{JOptionPane.showMessageDialog(this,"Revisa los Campos de texto");}

                        ConsultaUno(consulta);
                        limpiar();

                        break;

                    case 2:

                        if(Transmicion.getText().equals("")){error++;}
                        if(Distribucion.getText().equals("")){error++;}
                        if(Cenace.getText().equals("")){error++;}
                        if(Suministro.getText().equals("")){error++;}
                        if(SCNMEM.getText().equals("")){error++;}
                        if(EnergiaB.getText().equals("")){error++;}
                        if(Capacidad.getText().equals("")){error++;}

                        if(error == 0){

                            try{                                                            
                                PreparedStatement pps =  cn.prepareStatement("Update "+TableName+" SET Anio = ?, Mes = ?, 'TRANSMISION' = ?, DISTRIBUCION = ?, 'CENACE' = ?, SUMINISTRO = ?, 'SCNMEM' = ?, 'ENERGIA' = ?, 'CAPACIDAD' = ? WHERE id = ?;");
                                    pps.setString(1,ComboAnio.getSelectedItem()+"");
                                    pps.setString(2,comboMes.getSelectedItem()+"");
                                    pps.setString(3,Transmicion.getText());
                                    pps.setString(4,Distribucion.getText());
                                    pps.setString(5,Cenace.getText());
                                    pps.setString(6,Suministro.getText());
                                    pps.setString(7,SCNMEM.getText());
                                    pps.setString(8,EnergiaB.getText());
                                    pps.setString(9,Capacidad.getText());

                                    pps.setString(10,Clave);

                                    pps.executeUpdate();

                                JOptionPane.showMessageDialog(this,"Tarifa Actualizada");

                            }catch(Exception e){JOptionPane.showMessageDialog(this,"Fatal Error" + e);}
                        }
                        else{JOptionPane.showMessageDialog(this,"Revisa los Campos de texto");}

                        ConsultaDos(consulta);
                        limpiar();


                        break;    
                }
                }
        
        else{
            JOptionPane.showMessageDialog(this,"No se selecciono un elemento");
        }
       
    }//GEN-LAST:event_ModificarMouseClicked

    private void TipoTarifaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipoTarifaItemStateChanged
        TableName = "";
        TableName = TipoTarifa.getSelectedItem() + "";
        
        ResTarifa.setText(TipoTarifa.getSelectedItem() + ""); // obtener 
        
        consulta = "SELECT * FROM " + TableName;
       
        
        switch(f.tipoConsulta(TableName)){
            case 0:
                JOptionPane.showMessageDialog(this, "No Disponible");
                break;
            case 1:
                ConsultaUno(consulta);
                
                EnergiaI.setEnabled(true);
                EnergiaP.setEnabled(true);
                
                LabelEnergiaI.setEnabled(true);
                LabelEnergiaP.setEnabled(true);
                
                LabelEnergiaB.setText("Energia-B");
                
                LabelCapacidad.setText("Capacidad-D");
                
                break;
            case 2:
                ConsultaDos(consulta);
                EnergiaI.setEnabled(false);
                EnergiaP.setEnabled(false);
                
                LabelEnergiaI.setEnabled(false);
                LabelEnergiaP.setEnabled(false);
                
                LabelEnergiaB.setText("Energia");
                
                LabelCapacidad.setText("Capacidad");
                break;
            default:
                JOptionPane.showMessageDialog(this, "NO Paso Nada?");
                break;     
        }
        
        limpiar();
    }//GEN-LAST:event_TipoTarifaItemStateChanged

    private void CopiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CopiarMouseClicked
        if(bandera ==  true){
            /*Deshabilitar Botones*/
            Modificar.setEnabled(false);
            Eliminar.setEnabled(false);
            Copiar.setEnabled(false);
            /*Activamos agregar*/
            Agregar.setEnabled(true);

            bandera = false;    
        }
        else{JOptionPane.showMessageDialog(this,"No se selecciono un elemento");}
        
    }//GEN-LAST:event_CopiarMouseClicked

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
            java.util.logging.Logger.getLogger(CostosTarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CostosTarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CostosTarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CostosTarifas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CostosTarifas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Agregar;
    private javax.swing.JTextField Capacidad;
    private javax.swing.JTextField Cenace;
    private javax.swing.JComboBox<String> ComboAnio;
    private javax.swing.JLabel Copiar;
    private javax.swing.JTextField Distribucion;
    private javax.swing.JLabel Eliminar;
    private javax.swing.JTextField EnergiaB;
    private javax.swing.JTextField EnergiaI;
    private javax.swing.JTextField EnergiaP;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel LabelCapacidad;
    private javax.swing.JLabel LabelEnergiaB;
    private javax.swing.JLabel LabelEnergiaI;
    private javax.swing.JLabel LabelEnergiaP;
    private javax.swing.JLabel Limpiar;
    private javax.swing.JLabel Modificar;
    private javax.swing.JLabel ResTarifa;
    private javax.swing.JTextField SCNMEM;
    private javax.swing.JTextField Suministro;
    private javax.swing.JTable Tabla;
    private javax.swing.JComboBox<String> TipoTarifa;
    private javax.swing.JTextField Transmicion;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

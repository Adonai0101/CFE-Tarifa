
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class RABT extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = con.conectar();

    Funciones f = new Funciones();
    Calc c = new Calc();

    String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; //Arreglo importante para la Consulta

    double fc = 0.55;

    String descuento = "";
   

    public RABT() {
        initComponents();
        // estas dos lineas son para cerra de forma mas adecuada nuestro frame 
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/cfe.png")).getImage());
    }

    public void obtenerFactorDePotencia() {
        double consumo = Double.parseDouble(Consumo.getText());
        double reactivos = Double.parseDouble(Reactivos.getText());
        double res = c.factorDePotencia(consumo, reactivos);
        resFactorPotencia.setText(c.formatoNumero(res));
    }

    public void obtenerDiasDePeriodo() {
        int diaInicio = FechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mesInicio = FechaInicio.getCalendar().get(Calendar.MONTH);
        int anioInicio = FechaInicio.getCalendar().get(Calendar.YEAR);

        int diaFin = FechaFin.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mesFin = FechaFin.getCalendar().get(Calendar.MONTH);
        int anioFin = FechaFin.getCalendar().get(Calendar.YEAR);

        int dias = 0;
        Funciones f = new Funciones();
        try {
            dias = f.diasTranscurridos(anioInicio, mesInicio, diaInicio, anioFin, mesFin, diaFin);
            
        } catch (Exception w) {}

        ResDiasPeriodo.setText(dias + "");
    }

    public void obtenerFactorDeCarga() {
        double consumo = Double.parseDouble(Consumo.getText());
        double DemanMax = Double.parseDouble(DemandaMax.getText());
        int DiasPeriodo = Integer.parseInt(ResDiasPeriodo.getText());

        double res = c.factorDeCarga(consumo, DemanMax, DiasPeriodo);
        resFactorCarga.setText(c.formatoNumero(res));
    }

    public void obtenerPromedioDiario() {
        double consumo = Double.parseDouble(Consumo.getText());
        double diasPeriodo = Double.parseDouble(ResDiasPeriodo.getText());
        double res = c.promedioDiario(consumo, diasPeriodo);
        resPromedioDiario.setText(c.formatoNumero(res));
    }

    /*obtenemos el total de la tabla*/
    public void obtenerTotalTransmision() {
        double consumo = Double.parseDouble(Consumo.getText());
        double transmision = Double.parseDouble(c.quitarFormatp(resTransmicion.getText()));
        double res = consumo * transmision;
        TotalTransmision.setText(c.formatoNumero(res));

    }

    public void obtenerTotalDist() {
        double consumo = Double.parseDouble(Consumo.getText());
        double dis = Double.parseDouble(c.quitarFormatp(resDistribucion.getText()));
        double res = consumo * dis;
        TotalDist.setText(c.formatoNumero(res));
    }

    public void obtenerTotalCenace() {
        double consumo = Double.parseDouble(Consumo.getText());
        double cenace = Double.parseDouble(c.quitarFormatp(resCenace.getText()));
        double res = consumo * cenace;
        TotalCenace.setText(c.formatoNumero(res));
    }

    public void obtenerTotalSuministro() {
        double Suministro = Double.parseDouble(c.quitarFormatp(resSuministro.getText()));
        int diasP = Integer.parseInt(ResDiasPeriodo.getText());
        double res = diasP / 30;
        res = Math.round(res);
        res = res * Suministro;

        TotalSum.setText(c.formatoNumero(res));
    }

    public void obtenerTotalSC() {
        double consumo = Double.parseDouble(Consumo.getText());
        double sc = Double.parseDouble(c.quitarFormatp(resSC.getText()));
        double res = consumo * sc;

        TotalSC.setText(c.formatoNumero(res));
    }

    public void obtenerTotalEnergia() {
        double consumo = Double.parseDouble(Consumo.getText());
        double energia = Double.parseDouble(c.quitarFormatp(resEnergia.getText()));
        double res = consumo * energia;

        TotalEnergia.setText(c.formatoNumero(res));

    }

    public void obtenerTotalCapacidad() {
        double consumo = Double.parseDouble(Consumo.getText());
        double capac = Double.parseDouble(c.quitarFormatp(resCapacidad.getText()));
        double res = capac * consumo;

        TotalCapacidad.setText(c.formatoNumero(res));
    }

    public void obtenerFacturacionBasisca() {
        double Transmicion = Double.parseDouble(c.quitarFormatp(TotalTransmision.getText()));
        double Dist = Double.parseDouble(c.quitarFormatp(TotalDist.getText()));
        double Cenace = Double.parseDouble(c.quitarFormatp(TotalCenace.getText()));
        double Suministro = Double.parseDouble(c.quitarFormatp(TotalSum.getText()));
        double Energia = Double.parseDouble(c.quitarFormatp(TotalEnergia.getText()));
        double SC = Double.parseDouble(c.quitarFormatp(TotalSC.getText()));
        double capacidad = Double.parseDouble(c.quitarFormatp(TotalCapacidad.getText()));
        double res = Transmicion + Dist + Cenace + Suministro + Energia + SC + capacidad;

        TotalFacturacionBasico.setText(c.formatoNumero(res));
    }

    public void obtenerCargoMedicionBT() {
        double numero = Double.parseDouble(c.quitarFormatp(TotalFacturacionBasico.getText()));
        double res = 0.02 * numero;
        resBT.setText(c.formatoNumero(res));
    }

    public void obtenerFacturacionNormal() {
        double importe = Double.parseDouble(c.quitarFormatp(TotalFacturacionBasico.getText()));
        double bt = Double.parseDouble(c.quitarFormatp(resBT.getText()));
        double res = importe + bt;
        resFacturacionNormal.setText(c.formatoNumero(res));
    }

    public void obtenerCostos() {

        int mesFin = FechaFin.getCalendar().get(Calendar.MONTH);
        int anioFin = FechaFin.getCalendar().get(Calendar.YEAR);
        int sd = FechaFin.getCalendar().get(Calendar.DAY_OF_MONTH);
        String sql = "SELECT * FROM RABT WHERE ANIO = '" + anioFin + "' AND mes = '" + mes[mesFin] + "'";
        Statement st;
        //System.out.println(sql);
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            resTransmicion.setText(rs.getString(4));
            resDistribucion.setText(rs.getString(5));
            resCenace.setText(rs.getString(6));
            resSuministro.setText(rs.getString(7));
            resSC.setText(rs.getString(8));
            resEnergia.setText(rs.getString(9));
            resCapacidad.setText(rs.getString(10));

        } catch (Exception e) {
            System.out.println(e + " error?");
        }
    }

    public void obtenerFactordePotencia2() {
        double reactivos = Double.parseDouble(Reactivos.getText());
        if (reactivos == 0) {
            resFactorPotencia2.setText("0");
        } else {
            resFactorPotencia2.setText(resFactorPotencia.getText());
        }

    }

    public void obtenerFactorPotenciaBonificacion() {

        double FP = Double.parseDouble(c.quitarFormatp(resFactorPotencia2.getText()));
        double facturacion = Double.parseDouble(c.quitarFormatp(resFacturacionNormal.getText()));
        double numero = 0;
        double res = 0; // guarda el valor del descuento
        res = 1 - (90 / FP);
        res = res * 100;
        res = res / 4;

        PorcentajeBonificacion.setText(c.formatoNumero(res));

        double x = Double.parseDouble(c.quitarFormatp(PorcentajeBonificacion.getText()));
        numero = x * facturacion;
        numero = numero / 100;

        Bonificacion.setText(c.formatoNumero(numero));

    }

    public void obtenerFactorPotenciaCargo() {
        double FP = Double.parseDouble(c.quitarFormatp(resFactorPotencia2.getText()));
        double facturacion = Double.parseDouble(c.quitarFormatp(resFacturacionNormal.getText()));

        double numero = 0;
        double res = 0; // guarda el valor del descuento
        res = 1 - (90 / FP);
        res = res * 100;
        res = res * (3 / 5);

        PorcentajeBonificacion.setText(c.formatoNumero(res));

        double x = Double.parseDouble(c.quitarFormatp(PorcentajeBonificacion.getText()));
        numero = x * facturacion;
        numero = numero / 100;

        Bonificacion.setText(c.formatoNumero(numero));
    }

    public void obtenerFacturacionNeta() {
        if (descuento.equals("si")) {
            double facturacion = Double.parseDouble(c.quitarFormatp(resFacturacionNormal.getText()));
            double descuento = Double.parseDouble(c.quitarFormatp(Bonificacion.getText()));
            double res = facturacion - descuento;

            FacturacionNeta.setText(c.formatoNumero(res));

        } else {
            double facturacion = Double.parseDouble(c.quitarFormatp(resFacturacionNormal.getText()));
            double cargo = Double.parseDouble(c.quitarFormatp(Cargo.getText()));
            double res = facturacion + cargo;

            FacturacionNeta.setText(c.formatoNumero(res));

        }
    }

    public void obtenerIVA() {
        double facturacion = Double.parseDouble(c.quitarFormatp(FacturacionNeta.getText()));

        double iva = Double.parseDouble(IVA.getSelectedItem().toString());
        iva = iva / 100;
        double res = iva * facturacion;

        CostoIVA.setText(c.formatoNumero(res));
    }

    public void Total() {
        double iva = Double.parseDouble(c.quitarFormatp(CostoIVA.getText()));
        double facturacion = Double.parseDouble(c.quitarFormatp(FacturacionNeta.getText()));
        double res = iva + facturacion;

        resTotal.setText(c.formatoNumero(res));
    }

    // Metodo que Valida si hay Datos En la Fecha Seleccionada 
    public boolean hayDatos() {
        int Numero = 0;
        boolean ban;
        int mesFin = FechaFin.getCalendar().get(Calendar.MONTH);
        int anioFin = FechaFin.getCalendar().get(Calendar.YEAR);
        String sql = "SELECT count(*) FROM RABT WHERE ANIO = '" + anioFin + "' AND mes = '" + mes[mesFin] + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Numero = Integer.parseInt(rs.getString(1));
        } catch (Exception e) {
            System.out.println(e + " error?");
        }

        if (Numero > 0) {
            ban = true;
        } else {
            ban = false;
        }
        return ban;
    }

    public boolean ValidarCampos() {
        int error = 0;
        boolean ban;
        if (Consumo.getText().equals("")) {
            error++;
        }
        if (DemandaMax.getText().equals("")) {
            error++;
        }
        if (Reactivos.getText().equals("")) {
            error++;
        }
        if (DemMaxAnual.getText().equals("")) {
            error++;
        }

        if (FechaInicio.getDate() == null) {
            error++;
        }
        if (FechaFin.getDate() == null) {
            error++;
        }

        if (error == 0) {
            ban = true;
        } else {
            ban = false;
        }

        return ban;
    }

    public boolean validarFechas() {
        boolean ban;
        int diaInicio = FechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mesInicio = FechaInicio.getCalendar().get(Calendar.MONTH);
        int anioInicio = FechaInicio.getCalendar().get(Calendar.YEAR);

        int diaFin = FechaFin.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mesFin = FechaFin.getCalendar().get(Calendar.MONTH);
        int anioFin = FechaFin.getCalendar().get(Calendar.YEAR);

        int dias = 0;
        Funciones f = new Funciones();
        try {
            dias = f.diasTranscurridos(anioInicio, mesInicio, diaInicio, anioFin, mesFin, diaFin);   
        } catch (Exception w) {}
        
        if(dias > 0){ban =  true;}
        else{ban = false;}
        
        return ban;
    }

    public void limpiar() {
        String res = "-";
        resFactorPotencia.setText(res);
        ResDiasPeriodo.setText(res);
        resFactorCarga.setText(res);
        resPromedioDiario.setText(res);
        TotalTransmision.setText(res);
        TotalDist.setText(res);
        TotalCenace.setText(res);
        TotalSum.setText(res);
        TotalSC.setText(res);
        TotalEnergia.setText(res);
        TotalFacturacionBasico.setText(res);
        TotalCapacidad.setText(res);
        resBT.setText(res);
        resFacturacionNormal.setText(res);
        resTransmicion.setText(res);
        resDistribucion.setText(res);
        resCenace.setText(res);
        resSuministro.setText(res);
        resSC.setText(res);
        resEnergia.setText(res);
        resCapacidad.setText(res);
        resFactorPotencia2.setText(res);
        resTotal.setText(res);
        CostoIVA.setText(res);
        FacturacionNeta.setText(res);
        Bonificacion.setText(res);
        PorcentajeBonificacion.setText(res);
        PorcentajeCargo.setText(res);
        Cargo.setText(res);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelFormulario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        FechaInicio = new com.toedter.calendar.JDateChooser();
        FechaFin = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        Consumo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        DemandaMax = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Reactivos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        MedicionBT = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        resFactorPotencia = new javax.swing.JLabel();
        ResDiasPeriodo = new javax.swing.JLabel();
        resFactorCarga = new javax.swing.JLabel();
        resPromedioDiario = new javax.swing.JLabel();
        resCargoDist = new javax.swing.JLabel();
        resCargoCap = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        resFC = new javax.swing.JLabel();
        Calcular = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        DemMaxAnual = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        TotalTransmision = new javax.swing.JLabel();
        TotalDist = new javax.swing.JLabel();
        resTransmicion = new javax.swing.JLabel();
        resDistribucion = new javax.swing.JLabel();
        TotalCenace = new javax.swing.JLabel();
        resCenace = new javax.swing.JLabel();
        TotalSC = new javax.swing.JLabel();
        resSC = new javax.swing.JLabel();
        TotalEnergia = new javax.swing.JLabel();
        resEnergia = new javax.swing.JLabel();
        TotalCapacidad = new javax.swing.JLabel();
        resCapacidad = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        TotalSum = new javax.swing.JLabel();
        resSuministro = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        TotalFacturacionBasico = new javax.swing.JLabel();
        resBT = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        resFacturacionNormal = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        resFactorPotencia2 = new javax.swing.JLabel();
        PorcentajeCargo = new javax.swing.JLabel();
        PorcentajeBonificacion = new javax.swing.JLabel();
        Cargo = new javax.swing.JLabel();
        Bonificacion = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        FacturacionNeta = new javax.swing.JLabel();
        CostoIVA = new javax.swing.JLabel();
        resTotal = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        IVA = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.white);

        PanelFormulario.setBackground(new java.awt.Color(255, 255, 255));
        PanelFormulario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Periodo:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Consumo:");

        Consumo.setText("1000");
        Consumo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ConsumoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Kilowatthoras");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Demanda Max");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mensual");

        DemandaMax.setText("1");
        DemandaMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DemandaMaxKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Kilowatts");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Reactivos:");

        Reactivos.setText("0");
        Reactivos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ReactivosKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Kvarh");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Servicio de Medicion en BT");

        MedicionBT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Factor de Potencia");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Dias de Periodo");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Factor de Carga");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Promedio Diario");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Kw Para Cargo de Distribución");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Kw Para Cargo de Capacidad");

        resFactorPotencia.setText("-");

        ResDiasPeriodo.setText("-");

        resFactorCarga.setText("-");

        resPromedioDiario.setText("-");

        resCargoDist.setText("-");

        resCargoCap.setText("-");

        jLabel18.setText("FC");

        resFC.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resFactorPotencia))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resCargoCap))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ResDiasPeriodo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resFactorCarga))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resPromedioDiario))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resCargoDist))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resFC)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(resFactorPotencia))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(ResDiasPeriodo))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(resFactorCarga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(resPromedioDiario))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(resCargoDist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(resCargoCap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(resFC))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        Calcular.setText("Calcular");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mensual", "Bimestral" }));

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel21.setText("Tipo de Facturación");

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel22.setText("Demanda Max");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Anual");

        DemMaxAnual.setText("0");
        DemMaxAnual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DemMaxAnualKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel33.setText("Kilowatts");

        javax.swing.GroupLayout PanelFormularioLayout = new javax.swing.GroupLayout(PanelFormulario);
        PanelFormulario.setLayout(PanelFormularioLayout);
        PanelFormularioLayout.setHorizontalGroup(
            PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelFormularioLayout.createSequentialGroup()
                        .addComponent(Calcular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormularioLayout.createSequentialGroup()
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelFormularioLayout.createSequentialGroup()
                                .addComponent(Consumo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormularioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DemandaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelFormularioLayout.createSequentialGroup()
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(52, 52, 52)
                                .addComponent(MedicionBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFormularioLayout.createSequentialGroup()
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(103, 103, 103)
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Reactivos, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(DemMaxAnual))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        PanelFormularioLayout.setVerticalGroup(
            PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Consumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(PanelFormularioLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DemandaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(18, 18, 18)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Reactivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DemMaxAnual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(MedicionBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Calcular)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cálculo de Tarifa: RABT");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Comisión Federal de Electricidad");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Desglose de Facturacion Trarifa RABT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setText("Concepto");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Costo");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setText("Total");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("TRANSMICION -1");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("DISTRIBUCION");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("CENACE-1");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setText("SCNMEM-1");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("ENERGIA-1");

        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setText("CAPACIDAD-D");

        TotalTransmision.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalTransmision.setText("-");

        TotalDist.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalDist.setText("-");

        resTransmicion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resTransmicion.setText("-");

        resDistribucion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resDistribucion.setText("-");

        TotalCenace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalCenace.setText("-");

        resCenace.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resCenace.setText("-");

        TotalSC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalSC.setText("-");

        resSC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resSC.setText("-");

        TotalEnergia.setText("-");

        resEnergia.setText("-");

        TotalCapacidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalCapacidad.setText("-");

        resCapacidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resCapacidad.setText("-");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Suministro");

        TotalSum.setText("-");

        resSuministro.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resSuministro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TotalSum))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalCapacidad))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resEnergia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalEnergia))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalSC))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resCenace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalCenace))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resDistribucion, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TotalDist))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(resTransmicion, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TotalTransmision)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TotalTransmision)
                    .addComponent(resTransmicion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(TotalDist)
                    .addComponent(resDistribucion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(TotalCenace)
                    .addComponent(resCenace))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(TotalSum)
                    .addComponent(resSuministro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(TotalSC)
                    .addComponent(resSC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TotalEnergia)
                    .addComponent(resEnergia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(TotalCapacidad)
                    .addComponent(resCapacidad))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel45.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel45.setText("Importe de Facturacion Básico");

        jLabel46.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel46.setText("Cargo por Medicion BT 2%");

        TotalFacturacionBasico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TotalFacturacionBasico.setText("-");

        resBT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resBT.setText("-");

        jLabel49.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel49.setText("Facturacion Normal");

        resFacturacionNormal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resFacturacionNormal.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalFacturacionBasico))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resBT))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resFacturacionNormal)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(TotalFacturacionBasico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(resBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(resFacturacionNormal))
                .addContainerGap())
        );

        jLabel51.setText("Factor de Potencia");

        jLabel52.setText("Factor de potencia Cargo %");

        jLabel53.setText("Factor de potencia Bonificación %");

        resFactorPotencia2.setText("-");

        PorcentajeCargo.setText("-");

        PorcentajeBonificacion.setText("-");

        Cargo.setText("-");

        Bonificacion.setText("-");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PorcentajeBonificacion))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PorcentajeCargo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(210, 210, 210)
                        .addComponent(resFactorPotencia2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cargo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Bonificacion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(resFactorPotencia2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(PorcentajeCargo)
                    .addComponent(Cargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(PorcentajeBonificacion)
                    .addComponent(Bonificacion))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel59.setText("Facturación Neta");

        jLabel60.setText("IVA");

        jLabel61.setText("%");

        FacturacionNeta.setText("-");

        CostoIVA.setText("-");

        resTotal.setText("-");

        jLabel20.setText("TOTAL");

        IVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FacturacionNeta))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CostoIVA))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resTotal)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(FacturacionNeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61)
                    .addComponent(CostoIVA)
                    .addComponent(IVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resTotal)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PanelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConsumoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConsumoKeyTyped
        if (!f.Numero(evt.getKeyChar() + "")) {
            f.sonido();
            evt.consume();

        }
    }//GEN-LAST:event_ConsumoKeyTyped

    private void DemandaMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DemandaMaxKeyTyped
        if (!f.Numero(evt.getKeyChar() + "")) {
            f.sonido();
            evt.consume();

        }
    }//GEN-LAST:event_DemandaMaxKeyTyped

    private void ReactivosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReactivosKeyTyped
        if (!f.Numero(evt.getKeyChar() + "")) {
            f.sonido();
            evt.consume();

        }
    }//GEN-LAST:event_ReactivosKeyTyped

    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed

        if (ValidarCampos()) {
            if (validarFechas()) {
                if (hayDatos()) {
                    obtenerFactorDePotencia();
                    obtenerDiasDePeriodo();
                    obtenerFactorDeCarga();
                    obtenerPromedioDiario();

                    obtenerCostos();

                    obtenerTotalTransmision();
                    obtenerTotalDist();
                    obtenerTotalCenace();
                    obtenerTotalSuministro();
                    obtenerTotalSC();
                    obtenerTotalEnergia();
                    obtenerTotalCapacidad();

                    obtenerFacturacionBasisca();

                    //Vemos si se aplica el 2%
                    if (MedicionBT.getSelectedItem().equals("SI")) {
                        obtenerCargoMedicionBT();
                    } else {
                        resBT.setText("0");
                    }

                    obtenerFacturacionNormal();

                    obtenerFactordePotencia2();

                    // ahora veremos si se hace una bonificacion o si se hace un cargo
                    String temp = resFactorPotencia2.getText();
                    double numero = Double.parseDouble(temp);
                    if (numero == 0) {
                        descuento = "no";
                        Cargo.setText("0");
                        Bonificacion.setText("0");
                        PorcentajeBonificacion.setText("0");
                        PorcentajeCargo.setText("0");
                    } else if (numero > 90) {
                        //Bonificacion
                        obtenerFactorPotenciaBonificacion();
                        PorcentajeCargo.setText("0");
                        Cargo.setText("0");
                        descuento = "si";
                    } else {
                        //Cargo
                        obtenerFactorPotenciaCargo();
                        PorcentajeBonificacion.setText("0");
                        Bonificacion.setText("0");
                        descuento = "no";
                    }

                    obtenerFacturacionNeta();
                    obtenerIVA();
                    Total();

                } else {
                    String temp = "No hay Datos Registrados para la Fecha que Usted Quiere Calcular \nIntente con una fecha diferente o Registre el costo de la Tarifa";
                    JOptionPane.showMessageDialog(null, temp, "Error de validación", JOptionPane.WARNING_MESSAGE);
                    limpiar();
                }
            } else {
                String temp = "Error al en las Fechas, Intente de nuevo";
                JOptionPane.showMessageDialog(null, temp, "Error de validación", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            String temp = "Todos los campos son obligatorios para hacer el cálculo";
            JOptionPane.showMessageDialog(null, temp, "Error de validación", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_CalcularActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void DemMaxAnualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DemMaxAnualKeyTyped
        if (!f.Numero(evt.getKeyChar() + "")) {
            f.sonido();
            evt.consume();

        }
    }//GEN-LAST:event_DemMaxAnualKeyTyped

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
            java.util.logging.Logger.getLogger(RABT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RABT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RABT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RABT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RABT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bonificacion;
    private javax.swing.JButton Calcular;
    private javax.swing.JLabel Cargo;
    private javax.swing.JTextField Consumo;
    private javax.swing.JLabel CostoIVA;
    private javax.swing.JTextField DemMaxAnual;
    private javax.swing.JTextField DemandaMax;
    private javax.swing.JLabel FacturacionNeta;
    private com.toedter.calendar.JDateChooser FechaFin;
    private com.toedter.calendar.JDateChooser FechaInicio;
    private javax.swing.JComboBox<String> IVA;
    private javax.swing.JComboBox<String> MedicionBT;
    private javax.swing.JPanel PanelFormulario;
    private javax.swing.JLabel PorcentajeBonificacion;
    private javax.swing.JLabel PorcentajeCargo;
    private javax.swing.JTextField Reactivos;
    private javax.swing.JLabel ResDiasPeriodo;
    private javax.swing.JLabel TotalCapacidad;
    private javax.swing.JLabel TotalCenace;
    private javax.swing.JLabel TotalDist;
    private javax.swing.JLabel TotalEnergia;
    private javax.swing.JLabel TotalFacturacionBasico;
    private javax.swing.JLabel TotalSC;
    private javax.swing.JLabel TotalSum;
    private javax.swing.JLabel TotalTransmision;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel resBT;
    private javax.swing.JLabel resCapacidad;
    private javax.swing.JLabel resCargoCap;
    private javax.swing.JLabel resCargoDist;
    private javax.swing.JLabel resCenace;
    private javax.swing.JLabel resDistribucion;
    private javax.swing.JLabel resEnergia;
    private javax.swing.JLabel resFC;
    private javax.swing.JLabel resFactorCarga;
    private javax.swing.JLabel resFactorPotencia;
    private javax.swing.JLabel resFactorPotencia2;
    private javax.swing.JLabel resFacturacionNormal;
    private javax.swing.JLabel resPromedioDiario;
    private javax.swing.JLabel resSC;
    private javax.swing.JLabel resSuministro;
    private javax.swing.JLabel resTotal;
    private javax.swing.JLabel resTransmicion;
    // End of variables declaration//GEN-END:variables
}

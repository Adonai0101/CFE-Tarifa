import java.io.*;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//Librerias solo funcionan en el java 8
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;


public class Funciones {
    
    public int diasTranscurridos(int anioI,int mesI,int diaI,int anioF,int mesF,int diaF) throws ParseException{
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Ejemplo
        //	Date fechaInicial=dateFormat.parse("2016-02-14");
        //      Date fechaFinal=dateFormat.parse("2016-03-22");

        Date fechaInicial=dateFormat.parse(anioI + "-" + mesI + "-" + diaI);
        Date fechaFinal=dateFormat.parse(anioF + "-" + mesF + "-" + diaF);
 
        int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
 
        //System.out.println("Hay "+dias+" dias de diferencia");
         return dias;
    }
    
    public boolean Numero(String cad){
        Pattern pat = Pattern.compile("[0-9.]*");
        Matcher mat = pat.matcher(cad);
            if (mat.matches()){
                  return true;      
             } 
            else {return false;}
    }
    
    
    public void sonido(){
    System.out.println("Sonido ya no funciona");
//        InputStream in;
//        try{
//            in = new FileInputStream(new File("SoundError.wav"));
//            AudioStream audio =  new AudioStream(in);
//            AudioPlayer.player.start(audio);
//        }catch(Exception e){System.out.println("Error en el audio: " + e);}
//        
    }

    public void delay(int x){
        try{
            Thread.sleep(x);
        }catch(Exception e){}
    }
    
    
    public int tipoConsulta(String Tipo){
         int x;
         if(Tipo.equals("PDBT")){
             x = 0;
         }
         else if(Tipo.equals("DIST") || Tipo.equals("GDBTH")){
             x = 1;
         }
         else{
             x = 2;
         }
         return x;
     }
    
    public void generarExel(String [][] Entrada, String Ruta) throws IOException, WriteException{
        /*Creamos el Libro*/
        WorkbookSettings  conf = new WorkbookSettings();
        conf.setEncoding("ISO-8859-1");
        
        WritableWorkbook woorBook = Workbook.createWorkbook(new File(Ruta),conf);
        
        /*Creamos las hojas del Exel*/
        WritableSheet sheet = woorBook.createSheet("Costos", 0);
        
        //Estilos de letra para los regisyros
        WritableFont h = new WritableFont(WritableFont.COURIER,16,WritableFont.NO_BOLD);
        WritableCellFormat hFormat =  new WritableCellFormat(h);
        
        for(int i = 0; i < Entrada.length; i++){
            for(int j = 0; j < Entrada[i].length; j++){
            
                sheet.addCell(new jxl.write.Label(j, i, Entrada[i][j],hFormat));
            }
        }
        woorBook.write();
        woorBook.close();
        
    }
    
    
    public static void EscribirEXCEL(String[] header, String[][] document, String Ruta) {
        String nombreArchivo = Ruta;
        
        String hoja = "Costos";
        
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        // Poner en negrita la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        //font.setBold(true);
        style.setFont(font);
        
        // Generar los datos para el documento
        for(int i = 0 ; i <= document.length ; i++) {
            XSSFRow row = hoja1.createRow(i); // Se crea la fila
            for(int j = 0 ; j < header.length ; j++) {
                if(i == 0) { // Para la cabecera
                    XSSFCell cell = row.createCell(j); // Se crean las celdas pra la cabecera
                    cell.setCellValue(header[j]); // Se a単ade el contenido
                } else {
                    XSSFCell cell = row.createCell(j); // Se crean las celdas para el contenido
                    cell.setCellValue(document[i - 1][j]); // Se a単ade el contenido
                }
            }
        }
        
        // Crear el archivo
        try (OutputStream fileOut = new FileOutputStream(nombreArchivo)){
            System.out.println("SE CREO EL EXCEL");
            libro.write(fileOut);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void imprimirArreglo(String [][] Entrada){
          for(int i = 0; i < Entrada.length; i++){
              System.out.println();
            for(int j = 0; j < Entrada[i].length; j++){
            
                System.out.print(Entrada[i][j] + " ");
            }
        }
    }
    public String tipoArchivo(String cad){
        int cont = 0;
        while (cont < cad.length()){
            if(cad.charAt(cont) == '.'){
                break;
            }
            cont++;
        }
        cad =  cad.substring(cont, cad.length());
        return cad;
    }
}
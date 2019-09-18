
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class LeerExcel {
    LinkedList lista =  new LinkedList();
    String temp = ""; 
    public static String arg[]; 
    
    public LeerExcel(File fileName){
        List cellData =  new ArrayList();
        try{
            
            FileInputStream fileInput =  new FileInputStream(fileName);
            XSSFWorkbook workbook =  new XSSFWorkbook (fileInput); // Creamos El Libro
            XSSFSheet hssfsheet = workbook .getSheetAt(0);
            Iterator rowIterator = hssfsheet.rowIterator();
            
            while(rowIterator.hasNext()){
                
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator =  hssfRow.cellIterator();
                
                List cellTemp =  new ArrayList();
                
                while(iterator.hasNext()){
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                
                cellData.add(cellTemp);
            }
            
        }catch(Exception e){System.out.println("Error:" + e);}
    
        obtener(cellData);
    }
    
    public void obtener(List lista){
        
        arg = new String [lista.size()];
        
        for(int i = 0; i < lista.size(); i++){ 
            
            List cellTemp = (List) lista.get(i);
            for(int j = 0; j < cellTemp.size(); j++){
                XSSFCell hssfCell = (XSSFCell) cellTemp.get(j) ;
                String valor = hssfCell.toString();
                //System.out.print(valor + " ");
                temp = temp + valor + " ";
                
                //System.out.println("aqui podremos agarrar el valor ??");
            }
            
            System.out.print(temp);
            arg[i] = temp;
            temp = "";
            System.out.println("");
        }
    }
}
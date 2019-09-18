
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class Calc {
    
    public String formatoNumero(double numero){
        String temp = "";
        
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
        temp = formateador.format (numero);
        
        return temp;
    }
    public String quitarFormatp(String cad){
        cad = cad.replace(",","");
        
        return cad;
    } 
    
    public double factorDePotencia(double Consumo, double Reactivos){
        double res = 0;
        res = Reactivos/Consumo;
        res = Math.tanh(res);
        res = Math.cos(res);
        res = res * 100;
        
        return res;
    }
    
    public double factorDeCarga(double consumo,double DemanMax,int DiasPeriodo){
        double res = 0;
        
        res = DiasPeriodo * DemanMax * 24;
        res =  consumo / res;
        res = res * 100;
        
        return res;
    }

    public double promedioDiario(double consumo,double diasPeriodo){
        double res = consumo/diasPeriodo;
        return res;
    }
    
    public double CargoDistribucion(double consumo,int diasPeriodo, double fc){
        double res = 0;
        res = 24 * fc * diasPeriodo;
        res = consumo / res;
        res = Math.round(res); // le quite un ' + 1 '
        return res;
    }
    
}// fin clase

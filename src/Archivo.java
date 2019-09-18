import java.io.*;
public class Archivo {
    public void crearArchivo(){
        String texto = "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
"    <title>Reporte</title>\n" +
"    <style>\n" +
"        body{\n" +
"            font-family: Arial, Helvetica, sans-serif;\n" +
"            font-size: 12px;\n" +
"        }\n" +
"       h1,h2{text-align: center;}\n" +
"       .cont{\n" +
"           display: grid;\n" +
"           grid-template-columns: 1fr 2fr;\n" +
"           grid-gap: 10px;\n" +
"       }\n" +
"       .cont .partes{\n" +
"           border: solid;\n" +
"           padding: 10px;\n" +
"           }\n" +
"           .cont div label{\n" +
"               display: block;\n" +
"               margin-bottom: 10px;\n" +
"           }\n" +
"        .partes {\n" +
"            display: flex;\n" +
"            justify-content: space-between;    \n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <h1>Comicion Federal de Electricidad </h1>\n" +
"    <h2>Desglose de facturacion: '#Nombre tarifa'</h2>\n" +
"    <div class=\"cont\">\n" +
"        <div class=\"partes\">\n" +
"            <div class=\"Concepto\">\n" +
"                    <label>Dias de Periodo: </label>\n" +
"                    <label>Consumo: </label>\n" +
"                    <label>Demanda</label>\n" +
"                    <label>Reactivos</label>\n" +
"\n" +
"                    <label>Factor de Potencia</label>\n" +
"            </div>\n" +
"            <div class=\"resultado\">\n" +
"                    <label>'var aqui' Dias</label>\n" +
"                    <label>'' KilowattHoras</label>\n" +
"                    <label>'' Kilowatts</label>\n" +
"                    <label>'' Kvarh</label>\n" +
"\n" +
"                    <label>#poner aqui</label>\n" +
"            </div>  \n" +
"        </div>\n" +
"\n" +
"        <div class=\"partes\">\n" +
"            <div class=\"Concepto\">\n" +
"                <label>TRANSMISION</label>\n" +
"                <label>DISTRIBUCION</label>\n" +
"                <label>CENACE</label>\n" +
"                <label>SUMINISTRO</label>\n" +
"                <label>SCNMEM</label>\n" +
"                <label>ENERGIA</label>\n" +
"                <label>CAPACIDAD</label>\n" +
"\n" +
"                <label>Importe de facturacion Basico</label>\n" +
"                <label>Cargo por Medicion BT</label>\n" +
"                <label>Facturacion Normal</label>\n" +
"\n" +
"                <label>Cargo por Factor de Potencia</label>\n" +
"                <label>Bonificacion por Factor de Potencia</label>\n" +
"\n" +
"                <label>Facturacion Neta</label>\n" +
"                <label>IVA #ponemos de cuanto es el IVA </label>\n" +
"            </div>\n" +
"            <div class=\"total\">\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"                    <label>$ #PONER COSTO AQUI</label>\n" +
"\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"                    <label>#poner Resultado</label>\n" +
"\n" +
"                    <label>Total: #poner Resultado</label>\n" +
"            </div>\n" +
"        </div>\n" +
"    </div>\n" +
"</body>\n" +
"</html> ";
        try{
            FileWriter arch =  new FileWriter("text.html");
            arch.write(texto);
            arch.close();
        }catch(Exception e){}
    }
}

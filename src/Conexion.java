import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Conexion {
    //String url = "C:\\Users\\jack_\\cfe.db";//La ruta de la base de datos 
    
    String url = "cfe.db";//La ruta de la base de datos
    Connection connect;
    
    public Connection conectar(){
        try{
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            
            if(connect != null){
                System.out.println("Conectado");
            }
        }catch(SQLException e){System.out.println("Error!!" + e.getMessage() );}
        
        return connect;
    }
    
    public void cerrar(){
        try{
            connect.close();
        }catch(Exception e ){}
 
    }
    
    public void mostrar(){
        ResultSet res = null;
        try{
            String sql = "SELECT * FROM TEST";
            PreparedStatement st = connect.prepareStatement(sql);
            res = st.executeQuery();
            
            while(res.next()){
                System.out.println("Algo: ");
                System.out.println(res.getString("description"));
                System.out.println("-----------------------------");
            }
        }catch(Exception e){
            System.out.println("A caray!!");
        }
    }
}//fin clase
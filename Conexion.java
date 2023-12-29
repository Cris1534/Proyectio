/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 import java.sql.Connection;
 import  java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
/**
 *
 * @author crist
 */
public class Conexion {
Connection con;
public Conexion(){
   try{
       Class.forName("com.mysql.jdbc.Driver");
       con=DriverManager.getConnection("jdbc:mysql://localhost:3366/bd_proyectio","root","");
   } catch(Exception e){
       System.err.println("Error" +e);
   }
    
}
    public static void main(String[] args) {
        Conexion cn=new Conexion();
        Statement st;
        ResultSet rs;
        try {
            st=cn.con.createStatement();
            rs=st.executeQuery("Select* from paciente");
            while (rs.next()){
                System.out.println(rs.getInt("id")+""+rs.getString("user"));
            }
        } catch (Exception e) {
            
        }
                
    }

}
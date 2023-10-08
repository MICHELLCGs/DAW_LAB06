/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Michell
 */
public class cBaseDatos2 {
        String driver  = "com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    String usuario = "root";
    String clave   = "123456";
    private Connection Conectar() {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url,usuario,clave);
            return xcon;
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
   public String validarUsuario(String xnom, String xcla) {
    try {
        Connection xcon = this.Conectar();
        String sql = "select estado from t_usuarios where nombre=? AND clave=?";
        PreparedStatement ps = xcon.prepareStatement(sql);
        ps.setString(1, xnom);
        ps.setString(2, xcla);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String estado = rs.getString("estado");
            xcon.close();
            return estado;
        } else {
            xcon.close();
            return null; // Retorna null si no se encuentra el usuario
        }
    } catch (Exception ex) {
        System.out.println(ex.toString());
    }
    return null;
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Consulta {
    public DefaultTableModel consultar(){
        Connection con = null;
//        JLabel jLabel2 = new JLabel();
        JTable jTable1 = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();// variavel é inserida la \/
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/memoria","user","leandro123");
//                int count = 0;
                String query = "select cod, pergunta, resposta from tbl_inf order by pergunta";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData rsMd = rs.getMetaData();
                int numC = rsMd.getColumnCount();
                
//                DefaultTableModel modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                for(int x=1; x<=numC;x++){
                    modelo.addColumn(rsMd.getColumnLabel(x));
                }
                while (rs.next()){
                    Object []lista = new Object [numC];
                    for(int i=0;i<numC;i++){
//                        lista [i]= rs.getString(i);
                        lista [i]= rs.getString(i+1);
                    }
//                    count++;
                    modelo.addRow(lista);
                }
//                jLabel2.setText(Integer.toString(count));
            } catch (SQLException ex) {
                Logger.getLogger(JframeConsulta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JframeConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Account;
/**
 *
 * @author vh69
 */
public class AccountDAO extends DBContext {
    public Account login(String user, String pass){
        String sql="SELECT * FROM Account\n" +
                   "WHERE UserName =?\n" +
                   "AND Password=?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,user);
            st.setString(2,pass);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void signup(String user, String pass, String name, String email, String phone) {
        String sql = "insert into Account\n"
                + "values(?,?,?,?,?,3)";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,user);
            st.setString(2,pass);
            st.setString(3,name);
            st.setString(4,email);
            st.setString(5,phone);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Account checkAccountExist(String user){
        String sql="SELECT * FROM Account\n" +
                   "WHERE UserName =?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,user);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

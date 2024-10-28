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
import java.util.ArrayList;
import java.util.List;
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
                rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void signup(String user, String pass, String name, String email, String phone) {
        String sql = "INSERT INTO Account (UserName, Password, FullName, Email, PhoneNumber, RoleID) VALUES (?, ?, ?, ?, ?, 3)";
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
    public Account checkAccountExist(String username) {
        String sql = "select UserName from Account where UserName = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("Username"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String checkEmailExist(String email) {
        String sql = "SELECT [Email]\n"
                + "  FROM [dbo].[Account] where Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return email;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Account checkPhoneExist(String phone) {
        String sql = "SELECT [PhoneNumber]\n"
                + "  FROM [dbo].[Account] where PhoneNumber = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setEmail(rs.getString("PhoneNumber"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //  lấy danh sách tất cả account
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT accountId, FullName, UserName, Email, PhoneNumber, RoleID FROM Account";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt("accountId"));
                account.setFullName(rs.getString("FullName"));
                account.setUserName(rs.getString("UserName"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setRoleID(rs.getInt("RoleID"));
                accounts.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    // cập nhật account
    public void updateAccount(Account account) {
        String sql = "UPDATE Account SET RoleID = ? WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, account.getRoleID());
            st.setInt(2, account.getAccountID());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public Account getAccountByID(int accountID) {
    Account account = null;
    String sql = "SELECT accountId, FullName, UserName, Email, PhoneNumber, RoleID FROM Account WHERE accountId = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, accountID);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            account = new Account();
            account.setAccountID(rs.getInt("accountId"));
            account.setFullName(rs.getString("FullName"));
            account.setUserName(rs.getString("UserName"));
            account.setEmail(rs.getString("Email"));
            account.setPhoneNumber(rs.getString("PhoneNumber"));
            account.setRoleID(rs.getInt("RoleID"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return account;
}
public Account getAccountById(int id) {
        String sql = "select * from Account where AccountID = ?";
        Account account = new Account();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account.setAccountID(id);
                account.setUserName(rs.getString("UserName"));
                account.setPassWord(rs.getString("Password"));
                account.setFullName(rs.getString("FullName"));
                account.setEmail(rs.getString("Email"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
            }

            return account;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public String getUserNameByEmail(String email) {
        String sql = "SELECT UserName FROM Account WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return name;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public int editProfile(Account account) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [UserName] = ?\n"
                + "      ,[FullName] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[PhoneNumber] = ?\n"
                + " WHERE AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getFullName());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhoneNumber());
            ps.setInt(5, account.getAccountID());
            int checkUpdate = ps.executeUpdate();
            return checkUpdate;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public int changePass(String newpass, int aid) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Password] = ?\n"
                + " WHERE AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newpass);
            ps.setInt(2, aid);
            int checkUpdate = ps.executeUpdate();
            return checkUpdate;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public void updatePassByUserName(String pass, String username) {
        String sql = "update Account set Password = ? where UserName= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }


}

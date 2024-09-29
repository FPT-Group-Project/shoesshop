/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListAccountDao extends DBContext {
    // get list acc để hiển thị 

    public List<Account> getListAccounts(int page, int roleID, String keyWord) {
        int itemDisplay = 5;
        int offset = (page - 1) * itemDisplay;
List<Account> listAccounts =new  ArrayList<>();
        String sql = "SELECT \n"
                + "    AccountID,\n"
                + "    UserName,\n"
                + "    Password,\n"
                + "    FullName,\n"
                + "    Email,\n"
                + "    PhoneNumber,\n"
                + "    RoleID\n"
                + "FROM \n"
                + "    [dbo].[Account]\n"
                + "WHERE RoleID = ?\n"
                + // Lọc theo RoleID
                "AND UserName LIKE ?\n"
                + // Tìm kiếm theo từ khóa trong FullName
                "ORDER BY AccountID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, roleID);
            stmt.setString(2, "%"+keyWord+"%");

            stmt.setInt(3, offset);
            stmt.setInt(4, page);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
             Account acc= new Account( rs.getInt("AccountID"),
                rs.getString("UserName"),
                rs.getString("Password"),
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getString("PhoneNumber"),
                rs.getInt("RoleID"));
            listAccounts.add(acc);
            }

        } catch (Exception e) {
        }
       return listAccounts;
    }
    // get list acc để hiển thị  (o có key)
     public List<Account> getListAccounts(int page, int roleID) {
        int itemDisplay = 5;
        int offset = (page - 1) * itemDisplay;
List<Account> listAccounts =new  ArrayList<>();
        String sql = "SELECT \n"
                + "    AccountID,\n"
                + "    UserName,\n"
                + "    Password,\n"
                + "    FullName,\n"
                + "    Email,\n"
                + "    PhoneNumber,\n"
                + "    RoleID\n"
                + "FROM \n"
                + "    [dbo].[Account]\n"
                + "WHERE RoleID = ?\n"
              
                + // Tìm kiếm theo từ khóa trong FullName
                "ORDER BY AccountID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, roleID);
        

            stmt.setInt(2, offset);
            stmt.setInt(3, page);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
             Account acc= new Account( rs.getInt("AccountID"),
                rs.getString("UserName"),
                rs.getString("Password"),
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getString("PhoneNumber"),
                rs.getInt("RoleID"));
            listAccounts.add(acc);
            }

        } catch (Exception e) {
        }
       return listAccounts;
    }

     // đếm account 
     
       public int getTotalAccountCount(String key) {
    int totalProducts = 0;

    String sql = "SELECT COUNT(*) AS total FROM [dbo].[Product] WHERE ProductName LIKE ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "%" + key + "%"); 

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalProducts = rs.getInt("total"); 
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalProducts; 
} // đếm account
       public int countAccounts(int roleID, String keyWord) {
    int totalCount = 0;
    String sql = "SELECT COUNT(*) AS total FROM [dbo].[Account] WHERE RoleID = ? AND UserName LIKE ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, roleID);
        stmt.setString(2, "%" + keyWord + "%");
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            totalCount = rs.getInt("total");
        }
    } catch (Exception e) {
        e.printStackTrace(); 
    }

    return totalCount;
}
        // đếm account
       public int countAccounts(int roleID){
       int total = 0 ;
       String sql= "SELECT COUNT(*) AS total FROM [dbo].[Account] WHERE RoleID = ?"   ;
           try {
                        PreparedStatement stmt = connection.prepareStatement(sql);
                   stmt.setInt(1, roleID);
                           ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
            total = rs.getInt("total");
        }
                  
           } catch (Exception e) {
                   e.printStackTrace(); 
           }
       return total;
       }

    public static void main(String[] args) {
        ListAccountDao accountDao = new ListAccountDao();
       List<Account> accounts =   accountDao.getListAccounts(1, 1);
        for (Account account : accounts) {
        System.out.println(account.toString());
    }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AccountAdmin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// get list acc để hiển thị 

public class ListAccountDao extends DBContext {

    public List<AccountAdmin> getListAccounts(int page, int roleID, String keyWord) {
        int itemDisplay = 5; // số lượng item 
        int offset = (page - 1) * itemDisplay; //
        List<AccountAdmin> listAccounts = new ArrayList<>();

        // SQL query chỉnh sửa
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
                + "AND (UserName LIKE ? OR Email LIKE ?)\n"
                + "ORDER BY AccountID desc \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, roleID); // Set RoleID
            stmt.setString(2, "%" + keyWord + "%");
            stmt.setString(3, "%" + keyWord + "%");
            stmt.setInt(4, offset);
            stmt.setInt(5, itemDisplay);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AccountAdmin acc = new AccountAdmin(
                        rs.getInt("AccountID"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("RoleID")
                );
                listAccounts.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAccounts;
    }

    public List<AccountAdmin> getListAccounts(int page, int roleID) {
        int itemDisplay = 5; // Số lượng item trên mỗi trang
        int offset = (page - 1) * itemDisplay; // Tính offset cho phân trang
        List<AccountAdmin> listAccounts = new ArrayList<>();

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
                + "WHERE RoleID = ?\n"//1
                + "ORDER BY AccountID desc \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY"; //23

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, roleID);
            stmt.setInt(2, offset);
            stmt.setInt(3, itemDisplay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AccountAdmin acc = new AccountAdmin(
                        rs.getInt("AccountID"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("RoleID")
                );
                listAccounts.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAccounts;
    }

    // đếm account phan trang
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

    public int countAccounts(int roleID) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM [dbo].[Account] WHERE RoleID = ?";
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
        List<AccountAdmin> accounts = accountDao.getListAccounts(2, 2);
        for (AccountAdmin account : accounts) {
            System.out.println(account.toString());
        }
    }
}

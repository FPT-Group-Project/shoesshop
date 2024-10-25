package DAL;

import Models.Feedback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // Nhập thư viện Date
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
public class FeedbackDAO extends DBContext {

    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (AccountID, ProductID, Rating, Comment, FeedbackDate, Status) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feedback.getAccountId());
            statement.setInt(2, feedback.getProductId());
            statement.setInt(3, feedback.getRating());
            statement.setString(4, feedback.getComment());
            statement.setDate(5, new Date(feedback.getFeedbackDate().getTime())); // Chuyển đổi từ java.util.Date sang java.sql.Date
            statement.setString(6, feedback.getStatus());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // true if insert was successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // false if there was an error
        }
    }
  public List<Feedback> getFeedbackByProductId(int productId) {
    List<Feedback> feedbackList = new ArrayList<>();
   String sql = "SELECT f.FeedbackID, f.AccountID, f.ProductID, f.Rating, f.Comment, f.FeedbackDate, f.Status, a.Username " +
             "FROM Feedback f " +
             "JOIN Account a ON f.AccountID = a.AccountID " +
             "WHERE f.ProductID = ? " +
             "ORDER BY f.FeedbackID DESC";
    
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int accountId = resultSet.getInt("AccountID");
            int rating = resultSet.getInt("Rating");
            String comment = resultSet.getString("Comment");
            java.util.Date feedbackDate = resultSet.getDate("FeedbackDate");
            String status = resultSet.getString("Status");
            String username = resultSet.getString("Username"); // Lấy tên người dùng
            
            Feedback feedback = new Feedback(accountId, productId, rating, comment, status, feedbackDate, username);
            feedbackList.add(feedback);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // In ra thông tin lỗi
    }
    
    return feedbackList;
}
  public int getTotalCommentsByProductId(int productId) {
    String sql = "SELECT COUNT(*) AS TotalComments FROM Feedback WHERE ProductID = ?";
    int totalComments = 0;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            totalComments = resultSet.getInt("TotalComments"); // Lấy tổng số comment
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // In ra thông tin lỗi
    }

    return totalComments; // Trả về tổng số comment
}
  public int getTotalCommentsByRating(int productId, int rating) {
    int totalComments = 0;
    String query = "SELECT COUNT(*) FROM Feedback WHERE productId = ? AND rating = ?";
    
    try (
         PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, productId);
        ps.setInt(2, rating);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalComments = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return totalComments;
}
     public static void main(String[] args) {
       
         // Kết nối đến cơ sở dữ liệu
         
         FeedbackDAO feedbackDAO = new FeedbackDAO();
         // Thay đổi productId theo sản phẩm mà bạn muốn kiểm tra
         int productId = 1;
         List<Feedback> feedbackList = feedbackDAO.getFeedbackByProductId(productId);
         // Hiển thị thông tin phản hồi
         for (Feedback feedback : feedbackList) {
             System.out.println("Account ID: " + feedback.getAccountId());
             System.out.println("Product ID: " + feedback.getProductId());
             System.out.println("Rating: " + feedback.getRating());
             System.out.println("Comment: " + feedback.getComment());
             System.out.println("Feedback Date: " + feedback.getFeedbackDate());
             System.out.println("Status: " + feedback.getStatus());
             System.out.println("-----------------------------");
         }
    }
}

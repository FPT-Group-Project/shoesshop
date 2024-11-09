/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Conversation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {
    private Connection connection;

    public ConversationDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy Conversation theo CustomerID (nếu tồn tại)
    public Conversation getConversationByCustomerId(int customerId) {
        String sql = "SELECT ConversationID, CustomerID, StartTime FROM Conversation WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Conversation conversation = new Conversation();
                conversation.setConversationId(rs.getInt("ConversationID"));
                conversation.setCustomerId(rs.getInt("CustomerID"));
                conversation.setStartTime(rs.getTimestamp("StartTime"));
                return conversation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy cuộc trò chuyện
    }

    // Tạo một cuộc trò chuyện mới cho khách hàng nếu chưa có
    public void createConversation(int customerId) {
        String sql = "INSERT INTO Conversation (CustomerID, StartTime) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Sử dụng thời gian hiện tại
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách tất cả các cuộc trò chuyện (dành cho Admin/Staff)
    public List<Conversation> getAllConversations() {
        List<Conversation> conversations = new ArrayList<>();
        String query = "SELECT * FROM Conversation";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conversation conversation = new Conversation();
                conversation.setConversationId(rs.getInt("ConversationID"));
                conversation.setCustomerId(rs.getInt("CustomerID"));
                conversation.setStartTime(rs.getTimestamp("StartTime"));
                conversations.add(conversation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conversations;
    }
    
    public Conversation getConversationByConversationId(int conversationId) {
        Conversation conversation = null;
        String sql = "SELECT * FROM Conversation WHERE ConversationID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                conversation = new Conversation();
                conversation.setConversationId(resultSet.getInt("ConversationID"));
                conversation.setCustomerId(resultSet.getInt("CustomerID"));
                conversation.setStartTime(resultSet.getTimestamp("CreatedDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversation;
    }
}

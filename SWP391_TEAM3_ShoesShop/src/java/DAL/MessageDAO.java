package DAL;

import Models.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private Connection connection;

    public MessageDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy tất cả các tin nhắn trong một cuộc trò chuyện dựa vào ConversationID
//    public List<Message> getMessagesByConversationId(int conversationId) {
//        List<Message> messages = new ArrayList<>();
//        String query = "SELECT * FROM Message WHERE ConversationID = ? ORDER BY SendTime ASC";
//        
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, conversationId);
//            ResultSet rs = stmt.executeQuery();
//            
//            while (rs.next()) {
//                Message message = new Message();
//                message.setMessageId(rs.getInt("MessageID"));
//                message.setConversationId(rs.getInt("ConversationID"));
//                message.setSenderId(rs.getInt("SenderID"));
//                message.setContent(rs.getString("Content"));
//                message.setSendTime(rs.getTimestamp("SendTime"));
//                message.setSenderRole(rs.getInt("SenderRole"));
//                messages.add(message);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return messages;
//    }

    // Thêm một tin nhắn mới vào cuộc trò chuyện
    public void addMessage(Message message) {
        String query = "INSERT INTO Message (ConversationID, SenderID, Content, SendTime, SenderRole) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, message.getConversationId());
            stmt.setInt(2, message.getSenderId());
            stmt.setString(3, message.getContent());
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Lấy thời gian hiện tại
            stmt.setInt(5, message.getSenderRole());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 public List<Message> getMessagesByCustomerId(int customerId) {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT m.MessageID, m.SenderID, m.Content, m.SendTime, m.SenderRole," +
                 "c.ConversationID, a.username AS senderName " +
                 "FROM Message m " +
                 "JOIN Conversation c ON m.ConversationID = c.ConversationID " +
                 "JOIN Account a ON m.SenderID = a.AccountID " +
                 "WHERE c.CustomerID = ? " +
                 "ORDER BY m.SendTime";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Message message = new Message();
            message.setMessageId(rs.getInt("messageId"));
            message.setConversationId(rs.getInt("conversationId"));
            message.setSenderId(rs.getInt("senderId"));
            message.setContent(rs.getString("content"));
            message.setSendTime(rs.getTimestamp("sendTime"));
            message.setSenderRole(rs.getInt("SenderRole"));
            messages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}
 
 public Message getLatestMessageByConversationId(int conversationId) {
        Message message = null;
        String sql = "SELECT TOP 1 * FROM Message WHERE conversationId = ? ORDER BY sendTime DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, conversationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                message = new Message();
                message.setMessageId(rs.getInt("messageId"));
                message.setConversationId(rs.getInt("conversationId"));
                message.setSenderId(rs.getInt("senderId"));
                message.setContent(rs.getString("content"));
                message.setSendTime(rs.getTimestamp("sendTime"));
                message.setSenderRole(rs.getInt("senderRole"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return message;
    }

        public static void main(String[] args) {
        // Kết nối cơ sở dữ liệu
        try {
            // Kết nối với database
            DBContext dbContext = new DBContext(); // Hoặc dùng DriverManager nếu bạn cần tự thiết lập
            Connection connection = dbContext.getConnection();
            
            // Khởi tạo MessageDAO
            MessageDAO messageDAO = new MessageDAO(connection);
            
            // Gọi thử phương thức với customerId giả định
            int customerId = 3; // Thay bằng ID cụ thể cần kiểm tra
            List<Message> messages = messageDAO.getMessagesByCustomerId(customerId);
            
            // In ra các tin nhắn đã lấy được
            for (Message message : messages) {
                System.out.println("Message ID: " + message.getMessageId());
                System.out.println("Conversation ID: " + message.getConversationId());
                System.out.println("Sender ID: " + message.getSenderId());
                System.out.println("Content: " + message.getContent());
                System.out.println("Send Time: " + message.getSendTime());
                System.out.println("Sender role: " + message.getSenderRole());
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        public List<Message> getMessagesByConversationId(int conversationId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message WHERE ConversationID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessageId(resultSet.getInt("MessageID"));
                message.setConversationId(resultSet.getInt("ConversationID"));
                message.setSenderId(resultSet.getInt("SenderID"));
                message.setContent(resultSet.getString("Content"));
                message.setSendTime(resultSet.getTimestamp("SendTime"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

}

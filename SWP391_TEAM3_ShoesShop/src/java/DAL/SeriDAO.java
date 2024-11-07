package DAL;

import Models.Seri;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeriDAO {
    private final DBContext dbContext;

    public SeriDAO(DBContext dbContext) {
        this.dbContext = dbContext; // Sử dụng DBContext có sẵn
    }

    // Thêm seri mới
    public boolean insertSeri(Seri seri) {
        String sql = "INSERT INTO Seri(stockId, status) VALUES (?, ?)";
        try (PreparedStatement ps = dbContext.connection.prepareStatement(sql)) {
            ps.setInt(1, seri.getStockId());
            ps.setString(2, seri.getStatus());
            return ps.executeUpdate() > 0; // Trả về true nếu chèn thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }
    
public boolean updateSeriStatusToDeleted(int stockId, int quantityToDelete) {
    String sql = "UPDATE Seri SET status = 'deleted' " +
                 "WHERE seri IN (SELECT TOP (?) seri " +
                 "               FROM Seri " +
                 "               WHERE stockId = ? AND status = 'available')";

    try (PreparedStatement ps = dbContext.connection.prepareStatement(sql)) {
        ps.setInt(1, quantityToDelete);
        ps.setInt(2, stockId);
        
        return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Trả về false nếu có lỗi
    }
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;


import Models.Order2;
import Models.OrderDetail;
import Models.OrderDetail2;
import Models.Product;
import Models.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class OrderDAO2 extends DBContext{
     public List<Order2> getOrdersByAccountId(int accountId) {
        List<Order2> orderList = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Order] WHERE AccountID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order2 order = new Order2();
                order.setOrderID(resultSet.getInt("OrderID"));
                order.setAccountID(resultSet.getInt("AccountID"));
                order.setAddress(resultSet.getString("Address"));
                order.setHistoryCouponsID(resultSet.getInt("HistoryCouponsID"));
                order.setTotalPrice(resultSet.getBigDecimal("TotalPrice"));
                order.setOrderDate(resultSet.getDate("OrderDate"));
                order.setArrivalDate(resultSet.getDate("ArrivalDate"));
                order.setStatusID(resultSet.getInt("StatusID"));
                order.setPaymentStatus(resultSet.getString("PaymentStatus"));
                
                        //    private List<OrderDetail> getOrderDetailsByOrderID(int orderID) {

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions
        }

        return orderList;
    }
      
    public void confirmOrder(int orderId) {
    int newStatusId = 3; // Trạng thái mới, 3 nghĩa là đã nhận được hàng
    String query = "UPDATE [dbo].[Order] SET StatusID = ? WHERE OrderID = ?";
    
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, newStatusId); // Cập nhật với StatusID là 3
        preparedStatement.setInt(2, orderId);
        
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Đơn hàng với OrderID " + orderId + " đã được xác nhận thành công.");
        } else {
            System.out.println("Không tìm thấy đơn hàng với OrderID " + orderId + ".");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
   
 public List<OrderDetail2> getOrderDetailsByOrderId(int orderId) {
    List<OrderDetail2> orderDetails = new ArrayList<>();
   String sql = "SELECT o.OrderID, od.OrderDetailID, p.ProductID, p.ProductName, p.AvatarP, ps.SizeID, s.Size, " +
              "pc.ColorID, pc.Color, od.Quantity, od.UnitPrice, " +
              "(od.Quantity * od.UnitPrice) AS TotalPrice, " +
              "o.StatusID, o.OrderDate, o.ArrivalDate, ps.StockId " + // Thêm StockId vào truy vấn
              "FROM [dbo].[Order] o " +
              "JOIN [dbo].[OrderDetail] od ON o.OrderID = od.OrderID " +
              "JOIN [dbo].[Product] p ON od.ProductID = p.ProductID " +
              "JOIN [dbo].[ProductStock] ps ON od.StockID = ps.StockId " +
              "JOIN [dbo].[Product_Color] pc ON ps.ColorID = pc.ColorID " +
              "JOIN [dbo].[Product_Size] s ON ps.SizeID = s.SizeID " +
              "WHERE o.OrderID = ?";  // Sử dụng tham số cho OrderID


    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, orderId); // Gán giá trị cho tham số OrderID

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                OrderDetail2 orderDetail = new OrderDetail2();
                
                // Gán giá trị từ ResultSet vào orderDetail
                orderDetail.setOrderDetailID(resultSet.getInt("OrderDetailID"));
Order2 order = new Order2();
order.setOrderID(resultSet.getInt("OrderID"));
order.setOrderDate(resultSet.getDate("OrderDate"));
order.setTotalPrice(resultSet.getBigDecimal("TotalPrice"));
order.setStatusID(resultSet.getInt("StatusID"));

                // Gán OrderID trực tiếp (nếu không cần thông tin chi tiết của Order)
                orderDetail.setOrderID(order);

                // Tạo và gán Product
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                
                orderDetail.setProductID(product);

                // Tạo và gán Stock
                Stock stock = new Stock();
                stock.setStockId(resultSet.getInt("StockId")); // Lấy StockId từ ResultSet
                stock.setQuantity(resultSet.getInt("Quantity"));
                
                orderDetail.setStockID(stock);

                // Gán màu sắc và kích thước
                orderDetail.setColor(resultSet.getString("Color")); // Gán màu sắc
                orderDetail.setSize(resultSet.getString("Size"));   // Gán kích thước

                orderDetail.setQuantity(resultSet.getInt("Quantity"));
                orderDetail.setUnitPrice(resultSet.getDouble("UnitPrice"));
               orderDetail.setImageUrl(resultSet.getString("AvatarP")); // Lấy đường dẫn đến hình ảnh từ ResultSet


                // Thêm orderDetail vào danh sách
                orderDetails.add(orderDetail);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý lỗi
    }

    return orderDetails;
}
 

}

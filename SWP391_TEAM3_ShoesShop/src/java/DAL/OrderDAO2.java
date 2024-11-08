/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Account;
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
public class OrderDAO2 extends DBContext {

    public List<Order2> getOrdersByAccountId(int accountId) {
        List<Order2> orderList = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Order] WHERE AccountID = ? ORDER BY OrderDate DESC";

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
        String sql = "SELECT o.OrderID, od.OrderDetailID, p.ProductID, p.ProductName, p.AvatarP, ps.SizeID, s.Size, "
                + "pc.ColorID, pc.Color, od.Quantity, od.UnitPrice, "
                + "(od.Quantity * od.UnitPrice) AS TotalPrice, "
                + "o.StatusID, o.OrderDate, o.Address, o.ArrivalDate, ps.StockId, "
                + "a.AccountID, a.FullName, a.Email, a.PhoneNumber " // Thêm thông tin người mua
                + "FROM [dbo].[Order] o "
                + "JOIN [dbo].[OrderDetail] od ON o.OrderID = od.OrderID "
                + "JOIN [dbo].[Product] p ON od.ProductID = p.ProductID "
                + "JOIN [dbo].[ProductStock] ps ON od.StockID = ps.StockId "
                + "JOIN [dbo].[Product_Color] pc ON ps.ColorID = pc.ColorID "
                + "JOIN [dbo].[Product_Size] s ON ps.SizeID = s.SizeID "
                + "JOIN [dbo].[Account] a ON o.AccountID = a.AccountID " // JOIN vào bảng Account
                + "WHERE o.OrderID = ?";  // Sử dụng tham số cho OrderID

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
                    order.setAddress(resultSet.getString("address"));
                   

                    // Gán OrderID trực tiếp (nếu không cần thông tin chi tiết của Order)
                    orderDetail.setOrderID(order);

                    // Tạo và gán Product
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("ProductID"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setAvatarP(resultSet.getString("AvatarP"));

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
                    Account account = new Account();
                    account.setEmail(resultSet.getString("email"));
                    account.setFullName(resultSet.getString("fullName"));
                    account.setPhoneNumber(resultSet.getString("phoneNumber"));
                    orderDetail.setAccount(account); // Bạn cần thêm phương thức setAccount trong OrderDetail2
                    // Thêm orderDetail vào danh sách
                    orderDetails.add(orderDetail);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }

        return orderDetails;
    }

    public String getOrderStatus(int orderId) {
        String status = null;
        String query = "SELECT StatusID FROM [dbo].[Order] WHERE OrderID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int statusId = resultSet.getInt("StatusID");

                    // Chuyển đổi StatusID thành trạng thái dưới dạng chuỗi
                    switch (statusId) {
                        case 1:
                            status = "pending";      // Đang chờ xử lý
                            break;
                        case 2:
                            status = "approved";     // Đã phê duyệt
                            break;
                        case 3:
                            status = "delivering";   // Đang giao hàng
                            break;
                        case 4:
                            status = "delivered";    // Đã giao hàng
                            break;
                        case 5:
                            status = "rejected";     // Bị từ chối
                            break;
                        case 6:
                            status = "canceled";     // Đã hủy
                            break;

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }

        return status;
    }

    public void cancelOrder(int orderId) {
        String query = "UPDATE [dbo].[Order] SET StatusID = 6 WHERE OrderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }
    }
    public void reorderToCart(int orderId, int accountId) {
    // SQL truy vấn để lấy chi tiết sản phẩm từ đơn hàng cũ
    String selectOrderDetails = "SELECT ProductID, StockID, Quantity FROM [dbo].[OrderDetail] WHERE OrderID = ?";
    // SQL truy vấn để thêm sản phẩm vào giỏ hàng của người dùng hiện tại
    String insertToCart = "INSERT INTO [dbo].[Cart] (AccountID, ProductID, StockID, Quantity) VALUES (?, ?, ?, ?)";

    try (PreparedStatement selectStmt = connection.prepareStatement(selectOrderDetails);
         PreparedStatement insertStmt = connection.prepareStatement(insertToCart)) {
        
        // Gán OrderID cho truy vấn lấy chi tiết đơn hàng
        selectStmt.setInt(1, orderId);
        ResultSet resultSet = selectStmt.executeQuery();

        // Lặp qua các sản phẩm trong đơn hàng cũ và thêm vào giỏ hàng
        while (resultSet.next()) {
            int productId = resultSet.getInt("ProductID");
            int stockId = resultSet.getInt("StockID");
            int quantity = resultSet.getInt("Quantity");

            // Gán thông tin vào truy vấn thêm sản phẩm vào giỏ hàng
            insertStmt.setInt(1, accountId);
            insertStmt.setInt(2, productId);
            insertStmt.setInt(3, stockId);
            insertStmt.setInt(4, quantity);
            
            // Thực thi truy vấn
            insertStmt.executeUpdate();
        }

        System.out.println("Các sản phẩm từ OrderID " + orderId + " đã được thêm vào giỏ hàng của AccountID " + accountId);

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Không thể thêm sản phẩm vào giỏ hàng.");
    }
}
}

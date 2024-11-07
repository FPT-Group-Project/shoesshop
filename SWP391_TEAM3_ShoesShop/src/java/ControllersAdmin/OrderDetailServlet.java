package ControllersAdmin;

import DAL.AccountDAO;
import DAL.OrderDetailDAO;
import Models.OrderDetail;
import DAL.DBContext;
import DAL.OrderDAO;
import DAL.ProductDAO;
import DAL.ProductStockDAO;
import Models.Account;
import Models.Order;
import Models.Product;
import Models.ProductStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/orderDetail"})
public class OrderDetailServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String orderIDParam = request.getParameter("orderID");
    String accountIDParam = request.getParameter("accountID");

    int orderID = 0;
    int accountID = 0;
    try {
        orderID = Integer.parseInt(orderIDParam);
        accountID = Integer.parseInt(accountIDParam);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid orderID or accountID");
        return;
    }

    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderID);

    // Lấy thông tin đơn hàng
    OrderDAO orderDAO = new OrderDAO();
    Order order = orderDAO.getOrderById(orderID);

    AccountDAO accountDAO = new AccountDAO();
    Account account = accountDAO.getAccountByID(accountID);

    Map<Integer, Map<String, Object>> productInfoMap = new HashMap<>();
    ProductDAO productDAO = new ProductDAO();
    ProductStockDAO productStockDAO = new ProductStockDAO(new DBContext());

    for (OrderDetail detail : orderDetails) {
        Product product = productDAO.getProductById(detail.getProductID());
        Map<String, Object> productInfo = new HashMap<>();
        
        // Thêm ảnh đại diện và giá từ Product
        productInfo.put("avatarP", product.getAvatarP());
        productInfo.put("price", product.getPrice());
        
        // Lấy color và size từ ProductStock bằng stockID
        ProductStock productStock = productStockDAO.getProductStockById(detail.getStockID());
        productInfo.put("color", productStock.getColorID());
        productInfo.put("size", productStock.getSizeID());

        productInfoMap.put(detail.getStockID(), productInfo); // Lưu theo stockID
    }

    request.setAttribute("orderDetails", orderDetails);
    request.setAttribute("account", account);
    request.setAttribute("order", order); // Thêm thông tin đơn hàng
    request.setAttribute("productInfoMap", productInfoMap);

    request.getRequestDispatcher("Views/Admin/orderDetail.jsp").forward(request, response);
}
}

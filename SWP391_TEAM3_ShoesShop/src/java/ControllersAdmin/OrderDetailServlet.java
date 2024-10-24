package ControllersAdmin;

import DAL.AccountDAO;
import DAL.OrderDetailDAO;
import Models.OrderDetail;
import DAL.DBContext;
import DAL.ProductDAO;
import Models.Account;
import Models.Product;
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

    // Chuyển đổi orderID và accountID từ String sang int
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

    // Lấy thông tin chi tiết đơn hàng
    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderID);
    
    // Lấy thông tin khách hàng
    AccountDAO accountDAO = new AccountDAO();
    Account account = accountDAO.getAccountByID(accountID);
    
    // Map lưu thông tin sản phẩm (productID -> Map với avatarP và price)
    Map<Integer, Map<String, Object>> productInfoMap = new HashMap<>();

    ProductDAO productDAO = new ProductDAO();
    for (OrderDetail detail : orderDetails) {
        Product product = productDAO.getProductById(detail.getProductID());
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("avatarP", product.getAvatarP());
        productInfo.put("price", product.getPrice());
        productInfoMap.put(product.getProductId(), productInfo);
    }

    request.setAttribute("orderDetails", orderDetails);
    request.setAttribute("account", account);
    request.setAttribute("productInfoMap", productInfoMap);

    request.getRequestDispatcher("Views/Admin/orderDetail.jsp").forward(request, response);
}

}

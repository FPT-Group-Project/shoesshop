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

    // Ánh xạ ColorID sang tên màu và SizeID sang kích cỡ
    Map<Integer, String> colorMap = new HashMap<>();
    colorMap.put(1, "Red");
    colorMap.put(2, "Blue");
    colorMap.put(3, "White");
    colorMap.put(4, "Purple");
    colorMap.put(5, "Black");
    colorMap.put(6, "Green");
    colorMap.put(7, "Yellow");
    colorMap.put(8, "Pink");
    colorMap.put(9, "Gray");
    colorMap.put(10, "Red");
    colorMap.put(11, "Blue");
    colorMap.put(12, "White");
    colorMap.put(13, "Black");
    colorMap.put(14, "Black");
    colorMap.put(15, "Green");
    colorMap.put(16, "Yellow");
    colorMap.put(17, "Pink");
    colorMap.put(18, "Gray");

    Map<Integer, String> sizeMap = new HashMap<>();
    sizeMap.put(1, "36");
    sizeMap.put(2, "37");
    sizeMap.put(3, "38");
    sizeMap.put(4, "39");
    sizeMap.put(5, "40");
    sizeMap.put(6, "41");
    sizeMap.put(7, "42");
    sizeMap.put(8, "36");
    sizeMap.put(9, "37");
    sizeMap.put(10, "38");
    sizeMap.put(11, "39");
    sizeMap.put(12, "40");
    sizeMap.put(13, "41");
    sizeMap.put(14, "42");

    request.setAttribute("orderDetails", orderDetails);
    request.setAttribute("account", account);
    request.setAttribute("order", order);
    request.setAttribute("productInfoMap", productInfoMap);
    request.setAttribute("colorMap", colorMap);
    request.setAttribute("sizeMap", sizeMap);

    request.getRequestDispatcher("Views/Admin/orderDetail.jsp").forward(request, response);
}
}

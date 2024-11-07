package ControllersAdmin;

import DAL.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderListServlet", urlPatterns = {"/orderList"})
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO(); // Tạo đối tượng OrderDAO
        List<Map<String, Object>> orders = orderDAO.getAllOrdersWithCustomerNames(); // Gọi phương thức DAO đã chỉnh sửa
        
        request.setAttribute("orders", orders); // Gán danh sách đơn hàng vào request attribute
        request.getRequestDispatcher("/Views/Admin/orderList.jsp").forward(request, response); // Chuyển tiếp đến JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String action = request.getParameter("action");
        OrderDAO orderDAO = new OrderDAO();

        switch (action) {
            case "approve":
                orderDAO.updateOrderStatus(orderID, 2); // Approved
                break;
            case "reject":
                orderDAO.updateOrderStatus(orderID, 5); // Rejected
                break;
            case "delivering":
                orderDAO.updateOrderStatus(orderID, 3); // Delivering
                break;
            case "delivered":
                orderDAO.updateOrderStatus(orderID, 4); // Delivered
                break;
            default:
                break;
        }
        response.sendRedirect("orderList"); // Redirect về danh sách đơn hàng
    }
}

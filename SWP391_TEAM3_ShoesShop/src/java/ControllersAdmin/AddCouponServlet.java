    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
     */

    package ControllersAdmin;

    import DAL.UsedCouponDAO;
    import Models.UsedCoupon;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    /**
     *
     * @author biggp
     */
    @WebServlet(name="AddCouponServlet", urlPatterns={"/addCoupon"})
    public class AddCouponServlet extends HttpServlet {
        @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Trả về trang form để thêm coupon
    request.getRequestDispatcher("Views/Admin/addCoupon.jsp").forward(request, response);
}
       @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String codeName = request.getParameter("codeName");
        double discount = Double.parseDouble(request.getParameter("discount"));
        String couponType = request.getParameter("couponType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Tạo đối tượng UsedCoupon
        UsedCoupon coupon = new UsedCoupon(0, codeName, discount, couponType, quantity);

        // Gọi DAO để kiểm tra mã giảm giá đã tồn tại hay chưa
        UsedCouponDAO couponDAO = new UsedCouponDAO();
        String message = "";  // Thông báo sẽ hiển thị trên trang JSP

        try {
            // Kiểm tra mã giảm giá đã tồn tại
            if (couponDAO.isCouponCodeExists(codeName)) {
                message = "Coupon code existed!";
            } else {
                // Thêm coupon mới vào cơ sở dữ liệu
                couponDAO.addCoupon(coupon);
                message = "Coupon added successful!";
            }
        } catch (Exception e) {
            message = "Error while adding: " + e.getMessage();
        }

        // Truyền thông báo vào request
        request.setAttribute("message", message);

        // Chuyển tiếp về trang addCoupon.jsp để hiển thị thông báo
        request.getRequestDispatcher("Views/Admin/addCoupon.jsp").forward(request, response);
    }
}
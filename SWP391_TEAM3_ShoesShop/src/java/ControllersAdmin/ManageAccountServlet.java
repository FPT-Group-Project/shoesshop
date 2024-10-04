package ControllersAdmin;

import DAL.AccountDAO;
import Models.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageAccountServlet", urlPatterns = {"/manageAccount"})
public class ManageAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();

        // Lấy danh sách tất cả tài khoản từ database
        List<Account> accountList = accountDAO.getAllAccounts(); // Đảm bảo bạn đã tạo phương thức này

        // Đặt danh sách tài khoản vào request
        request.setAttribute("accountList", accountList);

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("/Views/Admin/manageAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        
        // lấy danh sách tất cả account từ database
        List<Account> accountList = accountDAO.getAllAccounts();
        
        // thiết lập danh sách account vào request
        request.setAttribute("accountList", accountList);

        // Lặp qua từng tài khoản và cập nhật role
        for (Account account : accountList) {
            String roleAdminParam = request.getParameter("roleAdmin_" + account.getAccountID());
            String roleStaffParam = request.getParameter("roleStaff_" + account.getAccountID());

            // xác định RoleID 
            if (roleAdminParam != null) {
                account.setRoleID(1); // Chọn Admin
            } else if (roleStaffParam != null) {
                account.setRoleID(2); // Chọn Staff
            } else {
                account.setRoleID(3); // Không chọn Admin hay Staff, mặc định là Customer
            }

            // cập nhật lại tài khoản trong database
            accountDAO.updateAccount(account);
        }

        // đặt thông báo thành công
        request.setAttribute("successMessage", "Accounts updated successfully!");
        
        // chuyển hướng về trang manageAccount.jsp
        request.getRequestDispatcher("/Views/Admin/manageAccount.jsp").forward(request, response);
    }
}

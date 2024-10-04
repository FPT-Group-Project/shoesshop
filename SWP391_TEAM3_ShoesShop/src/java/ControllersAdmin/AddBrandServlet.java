package ControllersAdmin;

import DAL.BrandDAO;
import Models.Brand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBrandServlet", urlPatterns = {"/addBrand"})
public class AddBrandServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // chuyển hướng sang addBrand.jsp
        request.getRequestDispatcher("/Views/Admin/addBrand.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // lấy giá trị từ form
        String brandName = request.getParameter("brandName");

        // tạo đối tượng Brand (không cần brandID)
        Brand brand = new Brand();
        brand.setBrandName(brandName);

        // gọi DAO để kiểm tra thương hiệu đã tồn tại
        BrandDAO brandDAO = new BrandDAO();
        if (brandDAO.brandExists(brandName)) {
            // Nếu brand đã tồn tại, thiết lập thông báo lỗi
            request.setAttribute("errorMessage", "Brand đã tồn tại");
            request.getRequestDispatcher("/Views/Admin/addBrand.jsp").forward(request, response);
            return; // Kết thúc phương thức
        }

        // nếu không tồn tại, thêm brand vào db
        brandDAO.insertBrand(brand);

        // lưu thông báo thành công vào request
        request.setAttribute("successMessage", "Brand added successfully");

        // chuyển tiếp về trang jsp
        request.getRequestDispatcher("/Views/Admin/addBrand.jsp").forward(request, response);
    }
}

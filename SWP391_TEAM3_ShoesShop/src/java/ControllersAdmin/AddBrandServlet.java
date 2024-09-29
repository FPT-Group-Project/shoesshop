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
        int brandID = Integer.parseInt(request.getParameter("brandID"));
        String brandName = request.getParameter("brandName");

        // tạo đối tượng Brand
        Brand brand = new Brand(brandID, brandName);

        // gọi DAO để thêm brand vào db
        BrandDAO brandDAO = new BrandDAO();
        brandDAO.insertBrand(brand);

        // lưu thông báo thành công vào request
        request.setAttribute("successMessage", "Brand added succesfully");

        // chuyển tiếp về trang jsp
        request.getRequestDispatcher("/Views/Admin/addBrand.jsp").forward(request, response);
    }
}

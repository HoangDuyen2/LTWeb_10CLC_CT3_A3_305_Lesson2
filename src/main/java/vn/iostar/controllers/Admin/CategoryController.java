package vn.iostar.controllers.Admin;

import vn.iostar.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryService;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(urlPatterns = {"/admin/categories","/admin/category/add","/admin/category/insert",
        "/admin/category/upload","/admin/category/edit","/admin/category/delete"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICategoryService categoryServices = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if(url.contains("/admin/categories")){
            List<Category> list = categoryServices.findAll();
            req.setAttribute("listcategory", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/category/edit")){
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryServices.findById(id);
            req.setAttribute("category", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        }
        else{
            int id = Integer.parseInt(req.getParameter("id"));  // Lấy ID từ request
            Category category = categoryServices.findById(id);  // Lấy đối tượng Category dựa trên ID
            // Sau khi xử lý xóa ảnh, xóa mục khỏi cơ sở dữ liệu
            try {
                categoryServices.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Redirect sau khi xóa thành công
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if(url.contains("/admin/category/insert")){
            String name = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String image = req.getParameter("images");
//            Dua du lieu vao model
            Category categoryModel = new Category();
            categoryModel.setCategoryname(name);
            categoryModel.setStatus(status);

            String fname = "";
            String uploadPath = Constants.DIR;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
//          Nếu thư mục không tồn tại, phương thức mkdir() sẽ tạo ra thư mục
//          tại đường dẫn được chỉ định
                uploadDir.mkdir();
            }

            try {
//              Lấy đối tượng Part từ request. Phương thức getPart("images1")
//              lấy file mà người dùng đã tải lên từ form có input với tên là
//              "images1"
                Part part = req.getPart("images1");
                if(part.getSize() > 0){
//                  part.getSubmittedFileName(): trả về tên file gốc mà người dùng tải lên
//                  Paths.get(...).getFileName().toString(): Chỉ lấy tên file từ đường dẫn
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String fileExtension = fileName.substring(index+1);
                    fname = System.currentTimeMillis()+ "." + fileExtension;
                    part.write(uploadPath+"/" + fname);
                    categoryModel.setImages(fname);
                }
                else if(image != null){
                    categoryModel.setImages(image);
                }
                else {
                    categoryModel.setImages("0c93b81da322586c08b5d61227b1f98b.jpg");
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            categoryServices.insert(categoryModel);
//            Chuyen trang
            resp.sendRedirect(req.getContextPath()+"/admin/categories");
        }
        if(url.contains("/admin/category/edit")){
            int id = Integer.parseInt(req.getParameter("categoryid"));
            String name = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
//            Dua du lieu vao model
            Category categoryModel = categoryServices.findById(id);
            String fileoId = categoryModel.getImages();
            categoryModel.setCategoryname(name);
            categoryModel.setStatus(status);

            String fname = "";
            String uploadPath = Constants.DIR;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            try {
                Part part = req.getPart("images1");
                if(part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String fileExtension = fileName.substring(index+1);
                    fname = System.currentTimeMillis()+ "." + fileExtension;
//                    Xoa file cu trong thu muc(tu lam)
                    part.write(uploadPath+"/" + fname);
                    categoryModel.setImages(fname);
                }
                else {
                    categoryModel.setImages(fileoId);
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            categoryServices.update(categoryModel);
//            Chuyen trang
            resp.sendRedirect(req.getContextPath()+"/admin/categories");
        }
    }
}

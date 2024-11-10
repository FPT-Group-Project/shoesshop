/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Tuan anh
 */
public class NewsDao extends DBContext{
  
    
    public List<News> getNewsList(int page) {
    List<News> newsList = new ArrayList<>();
    int itemsPerPage = 6; 
    int offset = (page - 1) * itemsPerPage; 

    String sql = "SELECT [NewsID], [Content], [Image], [Title], [UploadDate], [Author], [VideoLink], [ProductLink] " +
                 "FROM [dbo].[News] " +
                 "ORDER BY [NewsID] DESC " +
                 "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, offset);    
        stmt.setInt(2, itemsPerPage); 

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            News news = new News(
                rs.getInt("NewsID"),           
                rs.getString("Content"),       
                rs.getString("Image"),          
                rs.getString("Title"),         
                rs.getDate("UploadDate"),    
                rs.getString("Author"),       
                rs.getString("VideoLink"),    
                rs.getString("ProductLink")  
            );

            newsList.add(news);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return newsList;
}
    public News getNews(int NewsID) {
    News news = null;
    // Sử dụng REPLACE trong truy vấn SQL để thay thế ký tự newline bằng <br />
    String sql = "SELECT \n" +
"    [NewsID], \n" +
"    REPLACE([Content], '\\n', '<br />') AS [Content], \n" +
"    [Image], \n" +
"    [Title], \n" +
"    [UploadDate], \n" +
"    [Author], \n" +
"    [VideoLink], \n" +
"    [ProductLink] \n" +
"FROM \n" +
"    [dbo].[News] \n" +
"WHERE \n" +
"    [NewsID] = ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, NewsID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String content = rs.getString("Content");

            news = new News(
                rs.getInt("NewsID"),
                content,  // Sử dụng nội dung đã được thay thế
                rs.getString("Image"),
                rs.getString("Title"),
                rs.getDate("UploadDate"),
                rs.getString("Author"),
                rs.getString("VideoLink"),
                rs.getString("ProductLink")
            );
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return news;
}



    public int getTotalNewsCount() {
    int count = 0;

    String sql = "SELECT COUNT(*) AS TotalCount FROM [dbo].[News]";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            count = rs.getInt("TotalCount");
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
}

    public List<News> getNewsListTop5() {
    List<News> newsList = new ArrayList<>();
    String sql = "SELECT [NewsID], [Content], [Image], [Title], [UploadDate], [Author], [VideoLink], [ProductLink] " +
                 "FROM [dbo].[News] " +
                 "ORDER BY [UploadDate] DESC " + // Sắp xếp theo ngày tải lên mới nhất
                 "OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY"; // Lấy 5 bài viết đầu tiên

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            News news = new News(
                rs.getInt("NewsID"),            // ID bài viết
                rs.getString("Content"),        // Nội dung bài viết
                rs.getString("Image"),          // Hình ảnh bài viết
                rs.getString("Title"),          // Tiêu đề bài viết
                rs.getDate("UploadDate"),       // Ngày tải lên bài viết
                rs.getString("Author"),         // Tác giả bài viết
                rs.getString("VideoLink"),      // Liên kết video
                rs.getString("ProductLink")     // Liên kết sản phẩm
            );

            newsList.add(news);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return newsList;
}

public List<News> getNewsList(int page, String key) {
    List<News> newsList = new ArrayList<>();
    int itemsPerPage = 6; 
    int offset = (page - 1) * itemsPerPage; 

    String sql = "SELECT [NewsID], [Content], [Image], [Title], [UploadDate], [Author], [VideoLink], [ProductLink] " +
                 "FROM [dbo].[News] " +
                 "WHERE [Title] LIKE ? OR [Author] LIKE ? " +
                 "ORDER BY [NewsID] DESC " +
                 "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        String searchKey = "%" + key + "%"; 

        stmt.setString(1, searchKey);       
        stmt.setString(2, searchKey);      
        stmt.setInt(3, offset);            
        stmt.setInt(4, itemsPerPage);      

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            News news = new News(
                rs.getInt("NewsID"),            
                rs.getString("Content"),        
                rs.getString("Image"),          
                rs.getString("Title"),          
                rs.getDate("UploadDate"),      
                rs.getString("Author"),        
                rs.getString("VideoLink"),     
                rs.getString("ProductLink")     
            );

            newsList.add(news);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return newsList;
}
public int countNewsByKey(String key) {
    int count = 0;

    String sql = "SELECT COUNT(*) AS Total " +
                 "FROM [dbo].[News] " +
                 "WHERE [Title] LIKE ? OR [Author] LIKE ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        String searchKey = "%" + key + "%"; 

        stmt.setString(1, searchKey);     
        stmt.setString(2, searchKey);      

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            count = rs.getInt("Total"); 
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
}
public boolean getTitleNews(String title) {
    String sql = "SELECT COUNT(*) FROM [dbo].[News] WHERE [Title] = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt(1);
            rs.close();
            stmt.close();
            return count == 0; 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
}
public boolean editNews(News news) {
    boolean isUpdated = false;
    String sql = "UPDATE [dbo].[News] SET " +
                 "[Content] = ?, " +
                 "[Image] = ?, " +
                 "[Title] = ?, " +
                 "[UploadDate] = ?, " +
                 "[Author] = ?, " +
                 "[VideoLink] = ?, " +
                 "[ProductLink] = ? " +
                 "WHERE [NewsID] = ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, news.getContent()); 
        stmt.setString(2, news.getImage());
        stmt.setString(3, news.getTitle());
        stmt.setDate(4, new java.sql.Date(news.getUploadDate().getTime()));
        stmt.setString(5, news.getAuthor());
        stmt.setString(6, news.getVideoLink());
        stmt.setString(7, news.getProductLink());
        stmt.setInt(8, news.getNewsID());

        int rowsAffected = stmt.executeUpdate();
        isUpdated = (rowsAffected > 0);

        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isUpdated;
}
public boolean addNews(String content, String image, String title, String author, String videoLink, String productLink) {
    boolean isAdded = false;
    String sql = "INSERT INTO [dbo].[News] " +
                 "([Content], [Image], [Title], [UploadDate], [Author], [VideoLink], [ProductLink]) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, content);   
        stmt.setString(2, image);    
        stmt.setString(3, title);   
        stmt.setDate(4, new java.sql.Date(new java.util.Date().getTime())); 
        stmt.setString(5, author);     
        stmt.setString(6, videoLink);   
        stmt.setString(7, productLink); 

        // Thực thi câu lệnh
        int rowsAffected = stmt.executeUpdate();
        isAdded = (rowsAffected > 0); 

        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isAdded;
}
public boolean deleteNews(int newsID) {
    boolean isDeleted = false;
    String sql = "DELETE FROM [dbo].[News] WHERE [NewsID] = ?";

    try {
        // Giả sử bạn đã có kết nối với cơ sở dữ liệu
        PreparedStatement stmt = connection.prepareStatement(sql);

        // Đặt giá trị cho tham số ID
        stmt.setInt(1, newsID);

        // Thực thi câu lệnh DELETE
        int rowsAffected = stmt.executeUpdate();
        isDeleted = (rowsAffected > 0); 

        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isDeleted;
}




    public static void main(String[] args) {
        NewsDao dao = new NewsDao();
        int List = dao.countNewsByKey("Summer ");
        News a = dao.getNews(1);
        System.out.println(List);
       boolean getTitleNews = dao.getTitleNews("Summer ");
        System.out.println(getTitleNews);
    }
}

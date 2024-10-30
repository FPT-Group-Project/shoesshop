/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;



/**
 *
 * @author thanh
 */

public class Feedback {
    private int accountId;
    private int productId;
    private int rating;
    private String comment;
    
    private Date feedbackDate; // Thêm thuộc tính này
    private String  username;
    private String fullname;

    public Feedback(int accountId, int productId, int rating, String comment, Date feedbackDate) {
        this.accountId = accountId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        
        this.feedbackDate = feedbackDate; // Gán giá trị cho thuộc tính\
        
    }

    public Feedback(int accountId, int productId, int rating, String comment, Date feedbackDate, String username) {
        this.accountId = accountId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
      
        this.feedbackDate = feedbackDate;
        this.username = username;
    }

    public Feedback(int accountId, int productId, int rating, String comment, Date feedbackDate, String username, String fullname) {
        this.accountId = accountId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
        this.username = username;
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

        public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

  

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
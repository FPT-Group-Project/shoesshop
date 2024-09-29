/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author biggp
 */
public class Account {
    private int accountId;
    private String fullName;
    private String username;
    private String passWord;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String roleId;
    private String imgAccount;
    private String status;
    
    // bien bi trung nhau, doi sua lai sau
    private int accountID;
    private String userName;
    private String password;
    private int roleID;
    
    public Account(int accountID, String userName, String password, String fullName, String email, String phoneNumber, int roleID) {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
    }

    public Account() {
    }

    public Account(int accountId, String fullName, String username, String passWord, String gender, String email, String phoneNumber, String address, String roleId, String imgAccount, String status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.username = username;
        this.passWord = passWord;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleId = roleId;
        this.imgAccount = imgAccount;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getImgAccount() {
        return imgAccount;
    }

    public void setImgAccount(String imgAccount) {
        this.imgAccount = imgAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}

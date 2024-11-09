/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author vh69
 */
public class LoginResponse {
    private Account account;
    private String message;

    public LoginResponse(Account account, String message) {
        this.account = account;
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public String getMessage() {
        return message;
    }
}

package ua.ip.noteApp.account;

import lombok.Data;

@Data
public class ChangePasswordForm {
    String oldPassword;
    String password;
    String passwordConfirm;
}

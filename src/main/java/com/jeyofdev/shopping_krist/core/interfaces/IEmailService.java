package com.jeyofdev.shopping_krist.core.interfaces;

public interface IEmailService {
    void sendPasswordResetEmail(String toEmail, String resetToken);

    void sendSuccessUpdatePasswordEmail(String toEmail);

    void sendValidationEmail(String email, String verificationToken);

    void sendSuccessValidationEmail(String email);
}

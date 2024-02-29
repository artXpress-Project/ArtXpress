package com.group5.ArtExpress.Verification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
@Validated
public class EmailVerificationService {
    public boolean verifyEmailFormat(@Email String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean verifyEmailDomain(String email) {
        String[] parts = email.split("@");
        String domain = parts[1];

        try {
            InetAddress.getByName(domain);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
    public boolean verifyEmail(@Valid String email) {
        return verifyEmailFormat(email) && verifyEmailDomain(email);
    }

}

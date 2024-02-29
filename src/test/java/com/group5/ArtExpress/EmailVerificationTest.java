package com.group5.ArtExpress;

import com.group5.ArtExpress.emailService.EmailVerificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailVerificationTest {
    
    @Autowired
    private EmailVerificationService emailVerificationService;
    
    @Test
    public void testThatEmailIsValid(){
        boolean isTrue = emailVerificationService.verifyEmailFormat("ikennajames03@gmail.com");
        assertTrue(isTrue);

        boolean confirmation = emailVerificationService.verifyEmailFormat("ikennajamesgmail.com");
        assertFalse(confirmation);


    }


}

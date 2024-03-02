//package com.group5.ArtExpress.emailService;
//
//import com.group5.ArtExpress.data.models.Recipient;
//import com.group5.ArtExpress.data.models.Sender;
//import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
//import com.group5.ArtExpress.dto.responseDto.SendMailResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class BrevoMailServiceTest {
//
//    @Autowired
//    private BrevoMailService mailService;
//
//    @Test
//    public void testSendMail() {
//        SendMailRequest sendMailRequest = buildMailRequest();
//        SendMailResponse mailResponse = mailService.sendMail(sendMailRequest);
//        assertNotNull(mailResponse);
//        assertEquals(201, mailResponse.getStatusCode());
//    }
//
//    private static SendMailRequest buildMailRequest() {
//        SendMailRequest mailRequest = new SendMailRequest();
//        Sender sender = new Sender("Joel", "ikennajames03@gmail.com");
//        List<Recipient> recipients = List.of(
//                new Recipient("James", "ikennajames03@gmail.com"));
//
//        mailRequest.setSubject("Check this out");
//        mailRequest.setHtmlContent("<p>Hello young man</p>");
//        mailRequest.setSender(sender);
//        mailRequest.setRecipients(recipients);
//        return mailRequest;
//    }
//}
package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
import com.group5.ArtExpress.dto.responseDto.SendMailResponse;

public interface BrevoMailService {
    SendMailResponse sendMail(SendMailRequest sendMailRequest);
}

package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

@Data
public class EmailSendingRequest {
    private String receiverContent;
    private String subject;
    private String receiverMail;
}

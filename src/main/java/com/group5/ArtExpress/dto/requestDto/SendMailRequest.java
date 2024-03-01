package com.group5.ArtExpress.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group5.ArtExpress.data.models.Recipient;
import com.group5.ArtExpress.data.models.Sender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SendMailRequest {
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    private String subject;
    private String htmlContent;
}

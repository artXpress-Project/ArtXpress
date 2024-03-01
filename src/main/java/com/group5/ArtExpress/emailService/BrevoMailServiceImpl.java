package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.config.AppConfigurations;
import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
import com.group5.ArtExpress.dto.responseDto.SendMailResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class BrevoMailServiceImpl implements BrevoMailService{
    private final AppConfigurations appConfig;
    private final RestTemplate restTemplate;

    public BrevoMailServiceImpl(AppConfigurations appConfig, RestTemplate restTemplate) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public SendMailResponse sendMail(SendMailRequest request) {
        HttpHeaders headers = addRequestHeaders();
//        RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(request, headers, POST, URI.create(appConfig.getMailServiceUrl()));
        RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(request, headers, POST, URI.create(appConfig.getMailServiceUrl()));
        ResponseEntity<SendMailResponse> mailResponse = restTemplate.postForEntity(appConfig.getMailServiceUrl(),requestEntity, SendMailResponse.class);
        return buildSendMailResponse(mailResponse);
    }

    private static SendMailResponse buildSendMailResponse(ResponseEntity<SendMailResponse> mailResponse) {
        HttpStatusCode code = mailResponse.getStatusCode();
        var response = mailResponse.getBody();
        if (response == null)
            throw new RuntimeException("Mail sending failed");
        response.setStatusCode(code.value());
        return response;
    }

    private HttpHeaders addRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.set("api-key", appConfig.getMailApiKey());
        return headers;
    }
}

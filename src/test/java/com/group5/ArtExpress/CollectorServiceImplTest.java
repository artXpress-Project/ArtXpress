package com.group5.ArtExpress;

import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.repository.CollectorConfirmationRepo;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.service.CollectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CollectorServiceImplTest {
    @Autowired
    CollectorService collectorService;

    @Autowired
    CollectorRepo collectorRepo;

    @Autowired
    CollectorConfirmationRepo collectorConfirmationRepo;
    CollectorRequest collectorRequest;

    @BeforeEach
    public void tearDown(){
        collectorConfirmationRepo.deleteAll();
        collectorRepo.deleteAll();

    }

    @BeforeEach
    public void startWith(){
        collectorRequest = new CollectorRequest();
    }

    @Test
    public void canRegisterCollector(){
        collectorRequest.setFirstName("joel");
        collectorRequest.setLastName("chukwudi");
        collectorRequest.setPhoneNumber("08149661102");
        collectorRequest.setEmail("joelchukwuss@gmail.com");
        collectorRequest.setPassword("joel");
        collectorRequest.setAddress("Aba");
        Collector collector = collectorService.registerCollector(collectorRequest);
        assertNotNull(collector);

    }

    @Test
    public void canRegisterMultipleUsers(){
        collectorRequest.setFirstName("joel");
        collectorRequest.setLastName("chukwudi");
        collectorRequest.setPhoneNumber("08149661102");
        collectorRequest.setEmail("joelchukwussd@gmail.com");
        collectorRequest.setPassword("joel");
        collectorRequest.setAddress("Aba");
        Collector collector = collectorService.registerCollector(collectorRequest);
        assertNotNull(collector);

        collectorRequest.setFirstName("joel");
        collectorRequest.setLastName("chukwudi");
        collectorRequest.setPhoneNumber("08149661102");
        collectorRequest.setEmail("joelchukwuszzd@gmail.com");
        collectorRequest.setPassword("joel");
        collectorRequest.setAddress("Aba");
        Collector collectors = collectorService.registerCollector(collectorRequest);
        assertNotNull(collectors);



    }
}

package com.group5.ArtExpress.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface CloudService {
    String upload(MultipartFile artWork);
}

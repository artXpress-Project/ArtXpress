package com.group5.ArtExpress.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudService {
    String upload(MultipartFile artWork);
}

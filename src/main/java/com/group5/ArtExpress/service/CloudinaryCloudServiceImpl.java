package com.group5.ArtExpress.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service

public class CloudinaryCloudServiceImpl implements CloudService{
    private Cloudinary cloudinary;

//    @Override
//    public String upload(List<MultipartFile> artWork) {
//        try {
//            Uploader uploader = cloudinary.uploader();
//            Map<?, ?> response = uploader.upload(artWork.)
//        }
//    }

    @Override
    public String upload(MultipartFile file) {
        try {
            Uploader uploader = cloudinary.uploader();
            Map<?,?> response = uploader.upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return (String)response.get("secure_url");
        }catch (IOException exception){
            return null;
        }
    }
}

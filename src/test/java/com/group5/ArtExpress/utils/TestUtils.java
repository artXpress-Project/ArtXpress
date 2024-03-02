package com.group5.ArtExpress.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {

    public static MultipartFile getTestImage(String fileLocation) {
        Path path = Paths.get(fileLocation);
        try(InputStream stream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("test file", stream);
            return file;
        } catch (Exception exception) {
            return null;
        }
    }
}

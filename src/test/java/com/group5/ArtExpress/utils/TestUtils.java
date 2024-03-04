package com.group5.ArtExpress.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

//    public static MultipartFile getTestImage(List<String> fileLocations) {
//        Path path = null;
//        for (String fileLocation : fileLocations) {
//            path = Paths.get(fileLocation);
//        }
//        try {
//            assert path != null;
//            try(InputStream stream = Files.newInputStream(path)) {
//                MultipartFile file = new MockMultipartFile("test file", stream);
//                return file;
//            }
//        } catch (Exception exception) {
//            return null;
//        }
//    }

//    public static MultipartFile getTestImage(List<String> fileLocations) {
//        List<Path> paths = new ArrayList<>();
//
//        for (String fileLocation : fileLocations) {
//            paths.add(Paths.get(fileLocation));
//        }
//
//        try {
//            List<MultipartFile> files = new ArrayList<>();
//
//            for (Path path : paths) {
//                try (InputStream stream = Files.newInputStream(path)) {
//                    String fileName = path.getFileName().toString();
//                    MultipartFile file = new MockMultipartFile(fileName, fileName, "image/jpeg", stream);
//                    files.add(file);
//                }
//            }
//            return files.isEmpty() ? null : files.get(0);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//            return null;
//        }
//    }

}

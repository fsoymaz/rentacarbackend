package com.tobeto.pair8.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {
    String uploadImage(MultipartFile file) throws IOException;

}

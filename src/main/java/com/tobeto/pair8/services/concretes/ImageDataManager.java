package com.tobeto.pair8.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tobeto.pair8.core.utilities.images.ImageUtils;
import com.tobeto.pair8.entities.concretes.ImageData;
import com.tobeto.pair8.repositories.ImageDataRepository;
import com.tobeto.pair8.services.abstracts.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageDataManager implements ImageDataService {

    private final ImageDataRepository dataRepository;
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = ImageData.builder()
                .imageUrl(cloudinary.uploader()
                        .upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
                        .get("url")
                        .toString())
                .build();

        dataRepository.save(imageData);
        return imageData.getImageUrl();
    }
}

package com.example.ollethboardproject.service;

import com.example.ollethboardproject.domain.entity.Community;
import com.example.ollethboardproject.domain.entity.Image;
import com.example.ollethboardproject.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    @Value("${upload.path}")
    private String uploadPath;


    //local 절대 경로 이미지 저장 test
    public Image saveImageToCreateCommunity(MultipartFile file, Community community) throws Exception {
        //저장할 파일 경로 생성
        // 오리지날 네임 저장할지 여부
        String fileName = getUniqueFileName(file);
        // getUniqueFileName 함수를 호출할때마다 파일명을 새로 생성하기 때문에 한번만 호출해야함.
//      Path filePath = Path.of(uploadPath, getUniqueFileName(file));
        Path filePath = Path.of(uploadPath, fileName);
        //파일 저장
        // 59a990c5-3238-4367-bc04-d63c38f0eae7.png
        // 6d3c30b9-b4ae-4a8f-a21b-da80317d8967.png
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        Files.copy(file.getInputStream(), filePath);
        //데이터베이스에 이미지 정보 저장
        Image image = Image.of(fileName, filePath.toString(), community);
//        Image image = Image.of(getUniqueFileName(file), filePath.toString(), community);
        imageRepository.save(image);
        return image;
    }

    public void deleteImageByCommunity(Community community) {
        imageRepository.delete(imageRepository.findByCommunity(community));
    }

    public void saveImageToUpdateCommunity(MultipartFile file, Community community) throws Exception {
        //저장할 파일 경로 생성
        Path filePath = Path.of(uploadPath, getUniqueFileName(file));
        //파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        //데이터베이스에 이미지 정보 저장
        Image image = Image.of(getUniqueFileName(file), filePath.toString(), community);
        imageRepository.save(image);

    }

    //고유한 파일명 추출
    public String getUniqueFileName(MultipartFile file) {
        //return UUID.randomUUID() + "_" + file.getOriginalFilename();
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return UUID.randomUUID() + "." + extension;
    }
}
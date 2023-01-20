package com.ws.crud.Image;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadImage {
    public String uploadImage(MultipartFile file) throws Exception {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());

        String name = Long.toString(new Date().getTime()/1000) + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        String path_Directory = "D:/Desktop/Cloud/Projet/WebService/ws-enchere/src/main/resources/static/images";
        Files.copy(file.getInputStream(), Paths.get(path_Directory + File.separator + name),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Succeessfully Image is uploaded");
        return name;
    }
}

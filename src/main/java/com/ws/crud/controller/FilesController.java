package com.ws.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.crud.model.Enchere;
import com.ws.crud.model.FileInfo;
import com.ws.crud.model.Image;
import com.ws.crud.payload.request.AjouterEnchere;
import com.ws.crud.repository.EnchereRepository;
import com.ws.crud.repository.ImageRepository;
import com.ws.crud.response.ResponseMessage;
import com.ws.crud.service.FilesStorageService;

@Controller
@CrossOrigin("http://localhost:8081")
public class FilesController {

  @Autowired
  EnchereRepository enchereRepository;

  @Autowired
  ImageRepository imageRepository;

  @Autowired
  FilesStorageService storageService;

  // save enchere

  @PostMapping("/encheress")
  public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("enchere") String model,
      @RequestParam("files") MultipartFile[] files) throws JsonMappingException, JsonProcessingException {
    String message = "";
    ObjectMapper mapper = new ObjectMapper();
    AjouterEnchere ajouterEnchere = mapper.readValue(model, AjouterEnchere.class);
    Enchere enchere = new Enchere(ajouterEnchere.getDatedebut(), ajouterEnchere.getDateFin(),
        ajouterEnchere.getProduit(), ajouterEnchere.getPrixminimal(), ajouterEnchere.getDescription(),
        ajouterEnchere.getCategory_id(), ajouterEnchere.getUser_id(), 0);
    System.out.println(ajouterEnchere.getDatedebut());
    System.out.println(ajouterEnchere.getDateFin());
    System.out.println(ajouterEnchere.getProduit());
    System.out.println(ajouterEnchere.getPrixminimal());
    System.out.println(ajouterEnchere.getDescription());
    System.out.println(ajouterEnchere.getCategory_id());
    System.out.println(ajouterEnchere.getUser_id());
    Enchere en = enchereRepository.save(enchere);
    try {
      List<String> fileNames = new ArrayList<>();

      Arrays.asList(files).stream().forEach(file -> {
        String filenames = Long.toString(new Date().getTime() / 1000) + "."
            + FilenameUtils.getExtension(file.getOriginalFilename());
        storageService.save(file, filenames);
        Image image = new Image(filenames, (int) en.getId());
        fileNames.add(filenames);
        imageRepository.save(image);
      });

      message = "Uploaded the files successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Fail to upload files!";
      System.out.println(e);
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
package dev.khoican.fileuploadservice.controller;

import dev.khoican.fileuploadservice.model.UploadedFile;
import dev.khoican.fileuploadservice.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "file-upload")
public class FileUploadController {
    FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<List<UploadedFile>> addFiles(HttpServletRequest request, @RequestPart() MultipartFile[] files) {
        return fileUploadService.saveFiles(files);
    }
}

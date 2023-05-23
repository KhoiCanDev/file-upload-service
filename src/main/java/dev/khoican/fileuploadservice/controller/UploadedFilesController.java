package dev.khoican.fileuploadservice.controller;

import dev.khoican.fileuploadservice.model.UploadedFile;
import dev.khoican.fileuploadservice.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "uploaded-files")
public class UploadedFilesController {
    FileUploadService fileUploadService;

    @Autowired
    public UploadedFilesController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UploadedFile> getUploadedFiles(HttpServletRequest request) {
        return fileUploadService.getUploadedFiles();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<UploadedFile> getUploadedFile(HttpServletRequest request, @PathVariable("id") Integer id) {
        return fileUploadService.getUploadedFileById(id);
    }

    @RequestMapping(value = "/{id}/content", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> getUploadedFileContent(HttpServletRequest request, @PathVariable("id") Integer id) {
        Optional<FileSystemResource> contentOpt = fileUploadService.getUploadedFileContent(id);
        if (contentOpt.isPresent()) {
            return ResponseEntity.ok(contentOpt.get());
        }
        return ResponseEntity.badRequest().build();
    }
}

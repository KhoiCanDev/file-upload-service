package dev.khoican.fileuploadservice.service;

import dev.khoican.fileuploadservice.model.UploadedFile;
import dev.khoican.fileuploadservice.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
public class FileUploadService {

    private final UploadedFileRepository repository;

    @Autowired
    public FileUploadService(UploadedFileRepository repository) {
        this.repository = repository;
    }

    public List<UploadedFile> getUploadedFiles() {
        ArrayList<UploadedFile> result = new ArrayList<>();
        Iterable<UploadedFile> files = repository.findAll(Sort.by(Sort.Direction.DESC, "created"));
        files.forEach(result::add);
        return result;
    }

    public Optional<UploadedFile> getUploadedFileById(Integer id) {
        return repository.findFirstById(id);
    }

    public Optional<FileSystemResource> getUploadedFileContent(Integer id) {
        Optional<UploadedFile> uploadedFileOpt = repository.findFirstById(id);
        if (uploadedFileOpt.isPresent()) {
            File file = new File(uploadedFileOpt.get().getFilePath());
            return Optional.of(new FileSystemResource(file));
        }
        return Optional.empty();
    }

    public ResponseEntity<List<UploadedFile>> saveFiles(MultipartFile[] files) {
        List<UploadedFile> result = new ArrayList<>();

        try {
            for (MultipartFile file: files) {
                UUID uuid = UUID.randomUUID();
                String uuidStr = uuid.toString();
                String fileAbsPath = storeFile(file, uuidStr);

                UploadedFile uploadedFile = new UploadedFile();
                uploadedFile.setFileName(file.getOriginalFilename());
                uploadedFile.setFilePath(fileAbsPath);
                uploadedFile.setFileSize(file.getSize());
                uploadedFile.setCreated(new Date().getTime());
                repository.save(uploadedFile);

                result.add(uploadedFile);
            }
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private String storeFile(MultipartFile file, String fileId) throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        byte[] bytes = file.getBytes();
        File dir = new File(tmpDir + File.separator + "file-upload-service" + File.separator + fileId);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File uploadFile = new File(dir.getAbsolutePath() + File.separator +
                file.getOriginalFilename());
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
        outputStream.write(bytes);
        outputStream.close();

        return uploadFile.getAbsolutePath();
    }
}

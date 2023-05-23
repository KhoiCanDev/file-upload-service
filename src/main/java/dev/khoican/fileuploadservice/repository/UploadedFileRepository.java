package dev.khoican.fileuploadservice.repository;

import dev.khoican.fileuploadservice.model.UploadedFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Integer> {
    Optional<UploadedFile> findFirstById(Integer id);
}

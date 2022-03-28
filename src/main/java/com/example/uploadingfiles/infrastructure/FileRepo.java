package com.example.uploadingfiles.infrastructure;

import com.example.uploadingfiles.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepo extends JpaRepository<FileInfo,String> {
    List<FileInfo> findByNombre(String nombre);
}

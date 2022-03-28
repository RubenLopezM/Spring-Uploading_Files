package com.example.uploadingfiles.application;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init(String path);

    void save(MultipartFile file);

    Stream<Path> loadAll(Path path);

    Resource load(String filename);

    void deleteAll();
}

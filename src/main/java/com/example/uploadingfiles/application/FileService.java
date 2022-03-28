package com.example.uploadingfiles.application;

import com.example.uploadingfiles.domain.FileInfo;
import com.example.uploadingfiles.infrastructure.DTO.FileInfoOutputDTO;

import java.util.List;

public interface FileService {
    FileInfoOutputDTO add(FileInfo fileInfo);
    FileInfoOutputDTO findById(String id);

}

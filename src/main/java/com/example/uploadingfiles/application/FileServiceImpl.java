package com.example.uploadingfiles.application;

import com.example.uploadingfiles.domain.FileInfo;
import com.example.uploadingfiles.infrastructure.DTO.FileInfoOutputDTO;
import com.example.uploadingfiles.infrastructure.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepo fileRepo;

    @Autowired
    StorageService storageService;


    @Override
    public FileInfoOutputDTO add(FileInfo fileInfo) {
        fileRepo.save(fileInfo);
        return converttoDTO(fileInfo);
    }

    @Override
    public FileInfoOutputDTO findById(String id) {
       FileInfo file= fileRepo.findById(id).orElseThrow(()-> new RuntimeException("No se ha encotrado el archivo"));
        return converttoDTO(file);

    }



    private FileInfoOutputDTO converttoDTO (FileInfo fileInfo){
        FileInfoOutputDTO fileInfoOutputDTO= new FileInfoOutputDTO();
        fileInfoOutputDTO.setId_archivo(fileInfo.getId_archivo());
        fileInfoOutputDTO.setNombre(fileInfo.getNombre());
        fileInfoOutputDTO.setFecha_subida(fileInfo.getFecha_subida());
        fileInfoOutputDTO.setFormato(fileInfo.getFormato());
        fileInfoOutputDTO.setTamaño(fileInfo.getTamaño());
        return fileInfoOutputDTO;
    }
}

package com.example.uploadingfiles.infrastructure;

import com.example.uploadingfiles.application.FileService;
import com.example.uploadingfiles.application.StorageService;
import com.example.uploadingfiles.domain.FileInfo;
import com.example.uploadingfiles.infrastructure.DTO.FileInfoOutputDTO;
import com.example.uploadingfiles.shared.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class FileUploadController {

    @Autowired
    FileService fileService;

    @Autowired
    StorageService storageService;

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<?> uploadFile(@PathVariable String tipo, @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){

        String mensaje = "";
        storageService.save(file);
        String extension= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            if (tipo.equals(extension)){
                FileInfo fileInfo=new FileInfo(null, file.getOriginalFilename(), new Date(), extension  ,file.getSize());
                fileService.add(fileInfo);
                return new ResponseEntity<>(fileInfo,HttpStatus.OK);
            }
            else {
                mensaje = "El archivo " + file.getOriginalFilename() + " no es del tipo requerido:"+ tipo;
                return new ResponseEntity<>(mensaje,HttpStatus.UNPROCESSABLE_ENTITY);
            }
    }

    @GetMapping("files/{filename}")
    public ResponseEntity<Resource> getFilebyName(@PathVariable String filename){
        Resource resource=storageService.load(filename);
        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @GetMapping("upload/{id}")
    public ResponseEntity<Resource> getFilebyId (@PathVariable String id){
        FileInfoOutputDTO fileInfo=fileService.findById(id);
        Resource resource=storageService.load(fileInfo.getNombre());
        return new ResponseEntity<>(resource,HttpStatus.OK);

    }


}

package com.example.uploadingfiles.infrastructure.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FileInfoOutputDTO {

    private String id_archivo;
    private String nombre;
    private Date fecha_subida;
    private String formato;
    private Long tamaño;
}

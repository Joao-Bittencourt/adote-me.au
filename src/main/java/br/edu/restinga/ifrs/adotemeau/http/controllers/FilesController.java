package br.edu.restinga.ifrs.adotemeau.http.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.restinga.ifrs.adotemeau.models.enums.EntityTypeEnum;
import br.edu.restinga.ifrs.adotemeau.services.files.UploadFileService;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    UploadFileService uploadFileService;
    
    @PostMapping
    public void create(@Valid 
        @RequestParam("image") MultipartFile image, 
        @RequestParam("entityId") Long entityId, 
        @RequestParam("entityType") EntityTypeEnum entityType) 
    throws IOException {

        this.uploadFileService.execute(image.getBytes(), entityId, entityType);
    }
}

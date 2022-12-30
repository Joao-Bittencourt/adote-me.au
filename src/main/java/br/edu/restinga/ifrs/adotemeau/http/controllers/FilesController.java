package br.edu.restinga.ifrs.adotemeau.http.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur.ImgurDeleteService;
import br.edu.restinga.ifrs.adotemeau.services.files.DeleteFileService;
import br.edu.restinga.ifrs.adotemeau.services.files.UploadFileService;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    DeleteFileService deleteFileService;
    
    @PostMapping
    public ResponseEntity create(@Valid @RequestParam("image") MultipartFile image) throws IOException {
        return this.uploadFileService.execute(image.getBytes());
    }

    @DeleteMapping("/{imgurDeleteId}")
    public ResponseEntity delete(@PathVariable("imgurDeleteId") String imgurDeleteId) {
        return this.deleteFileService.execute(imgurDeleteId);
    }
}

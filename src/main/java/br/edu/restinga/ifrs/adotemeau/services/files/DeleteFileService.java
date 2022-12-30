package br.edu.restinga.ifrs.adotemeau.services.files;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import br.edu.restinga.ifrs.adotemeau.exceptions.CustomException;
import br.edu.restinga.ifrs.adotemeau.exceptions.StringResponse;
import br.edu.restinga.ifrs.adotemeau.models.File;
import br.edu.restinga.ifrs.adotemeau.repositories.FileRepository;
import br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur.ImgurDeleteService;

@Service
public class DeleteFileService {

    @Autowired
    private ImgurDeleteService imgurDeleteService;

    @Autowired
    private FileRepository fileRepository;
    
    @Transactional
    public ResponseEntity execute(String imgurDeleteId) {
        try {

            Optional<File>  file = this.fileRepository.findByImgurDeleteId(imgurDeleteId);

            if (!file.isPresent()) {
                return new ResponseEntity(new StringResponse("File not found!"), HttpStatus.BAD_REQUEST);
            }

            this.imgurDeleteService.execute(imgurDeleteId);

            this.fileRepository.deleteByImgurDeleteId(imgurDeleteId);

            return new ResponseEntity(new StringResponse("File deleted with success!"), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new StringResponse(e.getMessage()), e.getHttpStatus());
        }
    }
}

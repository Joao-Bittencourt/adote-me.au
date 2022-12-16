package br.edu.restinga.ifrs.adotemeau.services.files;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.exceptions.CustomException;
import br.edu.restinga.ifrs.adotemeau.exceptions.StringResponse;
import br.edu.restinga.ifrs.adotemeau.models.File;
import br.edu.restinga.ifrs.adotemeau.models.enums.EntityTypeEnum;
import br.edu.restinga.ifrs.adotemeau.repositories.FileRepository;
import br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur.ImgurUploadService;

@Service
public class UploadFileService {
    
    @Autowired
    private ImgurUploadService imgurUploadService;

    @Autowired
    private FileRepository fileRepository;

    public ResponseEntity execute(byte[] image, Long entityId, EntityTypeEnum entityType) {
        try {
            Map<String, String> fileImgur = this.imgurUploadService.execute(image);

            if (fileImgur.isEmpty()) {
                return new ResponseEntity(new StringResponse("Internal server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            this.createFile(fileImgur, entityId, entityType);

            return new ResponseEntity(new StringResponse("Created new File"), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new StringResponse(e.getMessage()), e.getHttpStatus());
        }
    }

    @Transactional
    private void createFile(Map<String, String> fileImgur,Long entityId, EntityTypeEnum entityType) {
        File file = new File();
        file.setEntityId(entityId);
        file.setType(entityType);
        file.setImgurImageHash(fileImgur.get("fileId"));
        file.setImgurImageDeleteHash(fileImgur.get("deleteHash"));
        file.setPath(fileImgur.get("path"));

        this.fileRepository.save(file);
    }
}

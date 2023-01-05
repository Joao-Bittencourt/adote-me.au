package br.edu.restinga.ifrs.adotemeau.services.files;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.models.File;
import br.edu.restinga.ifrs.adotemeau.repositories.FileRepository;
import br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur.ImgurUploadService;

@Service
public class UploadFileService {

    @Autowired
    private ImgurUploadService imgurUploadService;

    @Autowired
    private FileRepository fileRepository;

    public File execute(byte[] image) {
        Map<String, String> fileImgur = this.imgurUploadService.execute(image);
        return this.createFile(fileImgur);
    }

    @Transactional
    private File createFile(Map<String, String> fileImgur) {
        File file = new File();
        file.setImgurId(fileImgur.get("imgurId"));
        file.setImgurDeleteId(fileImgur.get("imgurDeleteId"));
        file.setPath(fileImgur.get("path"));

        return this.fileRepository.save(file);
    }
}

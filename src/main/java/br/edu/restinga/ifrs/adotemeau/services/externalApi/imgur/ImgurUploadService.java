package br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;

import reactor.core.publisher.Mono;

@Service
public class ImgurUploadService {

    @Autowired
    private WebClient webClient;

    @Value("${imgur.authorization}")
    private String imgurAuthorization;

    public Map<String, String> execute(byte[] image) {
        
        String file = uploadFile(image);

        return getHashMap(file);
    }

    private String uploadFile(byte[] image) {
        ByteArrayResource filePart = new ByteArrayResource(image);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", filePart);

        Mono<String> httpStatusMono = this.webClient
                .post()
                .uri("/image")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .headers(header -> header.set("Authorization", this.imgurAuthorization))
                .retrieve()
                .bodyToMono(String.class);

        return httpStatusMono.block();
    }

    private Map<String, String> getHashMap(String file) {
        String[] arrOfStr = file.split("[:,]");

        Map<String, String> map = new HashMap<String, String>();
        map.put("imgurId", arrOfStr[2].replace("\"", ""));
        map.put("imgurDeleteId", arrOfStr[52].replace("\"", ""));
        map.put("path", "https:" + arrOfStr[57].replace("\\", "").replace("}", "").replace("\"", ""));

        return map;
    }
}

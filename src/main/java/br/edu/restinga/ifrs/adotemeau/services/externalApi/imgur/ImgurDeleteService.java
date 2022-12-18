package br.edu.restinga.ifrs.adotemeau.services.externalApi.imgur;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

@Service
public class ImgurDeleteService {

    @Autowired
    private WebClient webClient;

    @Value("${imgur.authorization}")
    private String imgurAuthorization;

    public int execute(String imgurDeleteId) {

        ResponseSpec httpStatusMono = this.webClient
                .delete()
                .uri("/image/" + imgurDeleteId)
                // .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(header -> header.set("Authorization", this.imgurAuthorization))
                .retrieve();

        return httpStatusMono.toBodilessEntity().block().getStatusCode().value();
    }
}

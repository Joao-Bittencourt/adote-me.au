package br.edu.restinga.ifrs.adotemeau.http.dtos.response;

import br.edu.restinga.ifrs.adotemeau.models.File;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalAlbumResponse {
    private Long id;
    private File file;
}

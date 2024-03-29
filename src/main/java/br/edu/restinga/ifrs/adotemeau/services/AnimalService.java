package br.edu.restinga.ifrs.adotemeau.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.edu.restinga.ifrs.adotemeau.exceptions.CustomException;
import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.exceptions.StringResponse;
import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
import br.edu.restinga.ifrs.adotemeau.http.dtos.response.AnimalAlbumResponse;
import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.models.AnimalAlbum;
import br.edu.restinga.ifrs.adotemeau.models.File;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalAlbumRepository;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalRepository;
import br.edu.restinga.ifrs.adotemeau.repositories.UserRepository;
import br.edu.restinga.ifrs.adotemeau.services.authentication.AuthenticationService;
import br.edu.restinga.ifrs.adotemeau.services.files.UploadFileService;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private AnimalAlbumRepository animalAlbumRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Animal create(AnimalDTO animalDTO, String token) throws Exception {
        Animal animal = new Animal();
        BeanUtils.copyProperties(animalDTO, animal);

        User user = authenticationService.getUser(token);

        animal.setUser(user);
        animal.setTemperament(animalDTO.getTemperaments());

        return animalRepository.save(animal);
    }

    public Animal findById(Long id) {
        return this.animalRepository.findById(id).get();
    }

    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }

    public List<Animal> findByTemperament(Long id) {
        return this.animalRepository.findByTemperament_Id(id);
    }

    public List<Animal> findByFamily(Long id) {
        return this.animalRepository.findByFamily_Id(id);
    }

    public List<Animal> findByBreed(Long id) {
        return this.animalRepository.findByBreed_Id(id);
    }

    public Animal update(Long id, @Valid AnimalDTO animalDTO, String token) throws Exception {
        Animal animal = new Animal();
        BeanUtils.copyProperties(animalDTO, animal);

        Animal animalEntity = animalRepository.findById(id).get();

        User user = authenticationService.getUser(token);

        if (user.getId() != animalEntity.getId()) {
            throw new InvalidField("animalId","Este animal não pertence a você");
        }

        animal.setId(animalEntity.getId());

        return this.animalRepository.save(animal);
    }

    public String delete(Long id, String token) throws Exception {
        Animal animalEntity = animalRepository.findById(id).get();
        User user = authenticationService.getUser(token);

        if (user.getId() != animalEntity.getId()) {
            throw new InvalidField("animalId","Este animal não pertence a você");
        }

        this.animalRepository.deleteById(id);
        return "Deleted!";
    }

    public Animal isAdopted(Long id, Boolean adopted, String token) throws Exception {
        Animal animal = animalRepository.findById(id).get();

        User user = authenticationService.getUser(token);

        if (user.getId() != animal.getId()) {
            throw new InvalidField("animalId","Este animal não pertence a você");
        }

        animal.setAdopted(adopted);
        return this.animalRepository.save(animal);
    }

    public Animal setProfileImage(Long animalId, byte[] image, String token) throws Exception {

        Animal animal = this.animalRepository.findById(animalId).get();
        User user = authenticationService.getUser(token);

        if (user.getId() != animal.getId()) {
            throw new InvalidField("animalId","Este animal não pertence a você");
        }

        File file = uploadFileService.execute(image);
        animal.setProfileImage(file);
        return this.animalRepository.save(animal);
    }

    public String setAlbumImage(Long animalId, List<MultipartFile> images, String token) throws Exception {
        try {
            Animal animal = this.animalRepository.findById(animalId).get();

            User user = authenticationService.getUser(token);

            if (user.getId() != animal.getId()) {
                throw new InvalidField("animalId","Este animal não pertence a você");
            }

            for (MultipartFile image : images) {
                File file = uploadFileService.execute(image.getBytes());
                AnimalAlbum animalAlbum = new AnimalAlbum(animal, file);
                this.animalAlbumRepository.save(animalAlbum);
            }
        } catch (Exception e) {
            throw e;
        }

        return "Album cadastrado com sucesso!";
    }

    public List<AnimalAlbumResponse> getAlbum(Long animalId) {
        List<AnimalAlbum> animalAlbums = this.animalAlbumRepository.findByAnimalId(animalId);
        List<AnimalAlbumResponse> animalAlbumResponses = new ArrayList<>();

        for (AnimalAlbum animalAlbum : animalAlbums) {
            AnimalAlbumResponse animalAlbumResponse = new AnimalAlbumResponse(animalAlbum.getId(),
                    animalAlbum.getFile());
            animalAlbumResponses.add(animalAlbumResponse);
        }

        return animalAlbumResponses;
    }

    public List<Animal> findAnimalsByUser(String token) throws Exception {
        User user = authenticationService.getUser(token);
        return this.animalRepository.findByUser(user);
    }

}

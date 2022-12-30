package br.edu.restinga.ifrs.adotemeau.models;

import br.edu.restinga.ifrs.adotemeau.models.enums.EntityTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="imgur_image_hash", nullable = false)
    private String imgurImageHash;

    @Column(name="imgur_image_delete_hash", nullable = false)
    private String imgurImageDeleteHash;

    @Column(nullable = false)
    private String path;

    @Column(name="entity_id", nullable = false)
    private Long entityId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityTypeEnum type;
}

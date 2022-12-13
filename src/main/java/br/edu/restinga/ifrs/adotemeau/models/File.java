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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String imgurImageHash;

    @Column(nullable = false)
    private String imgurImageDeleteHash;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityTypeEnum type;
}

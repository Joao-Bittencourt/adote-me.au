package br.edu.restinga.ifrs.adotemeau.models;

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

    @Column(name="imgur_id", nullable = false)
    private String imgurId;

    @Column(name="imgur_delete_id", nullable = false)
    private String imgurDeleteId;

    @Column(nullable = false)
    private String path;
}

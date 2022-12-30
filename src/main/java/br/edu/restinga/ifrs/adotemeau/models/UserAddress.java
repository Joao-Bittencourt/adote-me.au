package br.edu.restinga.ifrs.adotemeau.models;

import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_addresses")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(length = 255)
    private String state;
    @Column(nullable = false, length = 255)
    private String city;
    @Column(nullable = false, length = 255)
    private String district;
    
    @OneToOne
    private User user;
 
}

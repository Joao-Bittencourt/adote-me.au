package br.edu.restinga.ifrs.adotemeau.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_addresses")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

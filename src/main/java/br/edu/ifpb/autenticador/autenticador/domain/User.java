package br.edu.ifpb.autenticador.autenticador.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private String password;

    @OneToOne(orphanRemoval = true)
    @Cascade({CascadeType.ALL})
    private Permissions permission;

    @OneToOne
    @Cascade({CascadeType.ALL})
    private Address address;

}

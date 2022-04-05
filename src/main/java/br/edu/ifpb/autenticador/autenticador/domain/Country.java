package br.edu.ifpb.autenticador.autenticador.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

}

package br.edu.ifpb.autenticador.autenticador.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Permissions {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean adminPermission;
    private Boolean listPermission;
    private Boolean updatePermission;
    private Boolean insertPermission;
    private Boolean deletePermission;
}

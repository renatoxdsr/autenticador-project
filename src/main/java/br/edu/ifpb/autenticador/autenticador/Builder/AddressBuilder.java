package br.edu.ifpb.autenticador.autenticador.Builder;

import br.edu.ifpb.autenticador.autenticador.domain.Address;
import br.edu.ifpb.autenticador.autenticador.domain.City;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressBuilder implements Builder{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String number;

    @ManyToOne
    @NotNull
    @Cascade({CascadeType.ALL})
    private City city;

    @Override
    public Builder setStreet(String street) {
        this.street = street;
        return this;
    }

    @Override
    public Builder setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    @Override
    public Builder setNumber(String number) {
        this.number = number;
        return this;
    }

    @Override
    public Builder setCity(City city) {
        this.city = city;
        return this;
    }

    public Address getResult(){
        return new Address(street, neighborhood, number, city);
    }

}

package br.edu.ifpb.autenticador.autenticador.Builder;

import br.edu.ifpb.autenticador.autenticador.domain.City;

public interface Builder {
    Builder setStreet(String street);
    Builder setNeighborhood(String neighborhood);
    Builder setNumber(String number);
    Builder setCity(City city);
}

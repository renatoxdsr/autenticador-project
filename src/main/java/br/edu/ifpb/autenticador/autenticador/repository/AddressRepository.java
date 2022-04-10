package br.edu.ifpb.autenticador.autenticador.repository;

import br.edu.ifpb.autenticador.autenticador.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}

package br.edu.ifpb.autenticador.autenticador.web;

import br.edu.ifpb.autenticador.autenticador.Builder.AddressBuilder;
import br.edu.ifpb.autenticador.autenticador.domain.*;
import br.edu.ifpb.autenticador.autenticador.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }

    // TODO - refatorar método para utilizar o padrão BUILDER para construir o endereço
    @PutMapping("/update-address")
    public ResponseEntity<Void> updateAddress(Long userId, String rua, String numero, String bairro, String cidade, String estado, String pais) {
        AddressBuilder builder = new AddressBuilder();
        builder.setStreet(rua);
        builder.setNumber(numero);
        builder.setNeighborhood(bairro);

        Country country = new Country();
        country.setName(pais);
        State state = new State();
        state.setName(estado);
        state.setCountry(country);
        City city = new City();
        city.setName(cidade);
        city.setState(state);
        builder.setCity(city);

        userService.updateUserAddress(userId, builder.getResult());

        return ResponseEntity.ok().build();
    }

    @PutMapping("update-permissions")
    public ResponseEntity<Void> updatePermissions(Long userId, String permissionName) {
        userService.updateUserPermission(userId, permissionName);
        return ResponseEntity.ok().build();
    }


}

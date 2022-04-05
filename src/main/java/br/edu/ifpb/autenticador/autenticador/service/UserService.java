package br.edu.ifpb.autenticador.autenticador.service;

import br.edu.ifpb.autenticador.autenticador.domain.Address;
import br.edu.ifpb.autenticador.autenticador.domain.Permissions;
import br.edu.ifpb.autenticador.autenticador.domain.User;
import br.edu.ifpb.autenticador.autenticador.repository.UserRepository;
import br.edu.ifpb.autenticador.autenticador.service.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(@Valid User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent( u -> { throw new BadRequestException("E-mail já cadastrado!"); });

        this.userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll().stream().map(user -> {
            user.setPassword("****");
            return user;
        }).collect(Collectors.toList());
    }

    public void updateUserAddress(Long userId, @Valid Address address) {
        User user = userRepository.findById(userId).orElseThrow( () -> new BadRequestException("Usuário não existe!"));
        user.setAddress(address);
        userRepository.save(user);
    }

    // TODO - refatorar método para utilizar o padrão PROTOTYPE que forneça um registry para criar os quatro tipos de permissão (administrador, somenteLeitura, operador e default)
    public void updateUserPermission(Long userId, String permissionName) {
        User user = userRepository.findById(userId).orElseThrow( () -> new BadRequestException("Usuário não existe!"));
        Permissions permission = new Permissions();
        switch (permissionName) {
            case "administrador":
                permission.setAdminPermission(true);
                break;
            case "somenteLeitura":
                permission.setAdminPermission(false);
                permission.setListPermission(true);
                permission.setDeletePermission(false);
                permission.setInsertPermission(false);
                permission.setUpdatePermission(false);
                break;
            case "operador":
                permission.setAdminPermission(false);
                permission.setDeletePermission(false);
                permission.setListPermission(true);
                permission.setInsertPermission(true);
                permission.setUpdatePermission(true);
                break;
            default:
                permission.setAdminPermission(false);
                permission.setDeletePermission(false);
                permission.setListPermission(false);
                permission.setInsertPermission(false);
                permission.setUpdatePermission(false);
        }
        user.setPermission(permission);
        userRepository.save(user);
    }

}

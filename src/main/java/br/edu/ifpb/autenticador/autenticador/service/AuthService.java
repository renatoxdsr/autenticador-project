package br.edu.ifpb.autenticador.autenticador.service;

import br.edu.ifpb.autenticador.autenticador.domain.User;
import br.edu.ifpb.autenticador.autenticador.repository.UserRepository;
import br.edu.ifpb.autenticador.autenticador.service.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void validateCredentials(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow( () -> new BadRequestException("Login ou senha inválidos!"));
        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Login ou senha inválidos!");
        }
    }

}

package br.edu.ifpb.autenticador.autenticador.service;

import br.edu.ifpb.autenticador.autenticador.domain.User;
import br.edu.ifpb.autenticador.autenticador.util.UsersReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UsersImporter {

    private final UserService userService;

    public void importUsers() {
        try {
            List<User> users = UsersReader.loadUsersFromJson();
            users.forEach(userService::createUser);
        } catch (URISyntaxException | IOException e) {
            log.error("Falha ao importar usuários");
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void importUsersOnStartUp() {
        log.info("Importing users from json file");
        importUsers();
        log.info("Finished to import users");
    }

}

package br.edu.ifpb.autenticador.autenticador.util;

import br.edu.ifpb.autenticador.autenticador.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Arrays.asList;

// TODO - Transformar essa classe em um SINGLETON para guardar lista de usuários do arquivo, ao invés de carregar do arquivo em cada chamada
public class UsersReader {

    private UsersReader() {}

    private static final String JSON_FILE = "users.json";

    public static List<User> loadUsersFromJson() throws URISyntaxException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User[] users = objectMapper.readValue(new File(ClassLoader.getSystemResource(JSON_FILE).toURI()), User[].class);
        return asList(users);
    }

}

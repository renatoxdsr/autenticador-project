package br.edu.ifpb.autenticador.autenticador.Singleton;

import br.edu.ifpb.autenticador.autenticador.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class Singleton {
    private static Singleton instance;
    private List<User> listUsers;


    private Singleton(String JSON_FILE) throws URISyntaxException, IOException {
        this.listUsers = listUsersFromJson(JSON_FILE);
    }

    public static Singleton getInstance(String JSON_FILE) throws URISyntaxException, IOException {
        Singleton result = instance;
        if (result != null){
            return result;
        }
        synchronized (Singleton.class){
            if (instance == null){
                instance = new Singleton(JSON_FILE);
            }
            return instance;
        }
    }

    public static List<User> listUsersFromJson(String JSON_FILE)throws URISyntaxException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        User[] users = objectMapper.readValue(new File(ClassLoader.getSystemResource(JSON_FILE).toURI()), User[].class);
        return asList(users);
    }

    public List<User> getListUsers() {
        return listUsers;
    }
}
package br.edu.ifpb.autenticador.autenticador.repository;

import br.edu.ifpb.autenticador.autenticador.domain.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        userRepository.save(user);
    }

    @Test
    void findByEmailTest() {
        Optional<User> user = userRepository.findByEmail("test@test.com");
        assertThat(user.isPresent(), Matchers.is(true));
        assertThat(user.get().getName(), Matchers.is("test"));
        assertThat(user.get().getPassword(), Matchers.is("test"));
        assertThat(user.get().getEmail(), Matchers.is("test@test.com"));
    }

}

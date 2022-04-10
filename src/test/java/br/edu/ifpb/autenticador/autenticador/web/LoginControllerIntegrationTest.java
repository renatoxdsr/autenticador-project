package br.edu.ifpb.autenticador.autenticador.web;

import br.edu.ifpb.autenticador.autenticador.AutenticadorApplication;
import br.edu.ifpb.autenticador.autenticador.domain.User;
import br.edu.ifpb.autenticador.autenticador.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AutenticadorApplication.class })
@WebAppConfiguration
class LoginControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@test.com");
        userRepository.save(user);
    }

    @SneakyThrows
    @Test
    void checkCredentialsTestSuccess() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("email", "test@test.com");
        params.set("senha", "123");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void checkCredentialsTestFailed() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("email", "test@test.com");
        params.set("senha", "312");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/login").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

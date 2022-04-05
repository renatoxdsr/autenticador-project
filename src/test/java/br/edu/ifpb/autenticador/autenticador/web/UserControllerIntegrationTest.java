package br.edu.ifpb.autenticador.autenticador.web;

import br.edu.ifpb.autenticador.autenticador.AutenticadorApplication;
import br.edu.ifpb.autenticador.autenticador.domain.User;
import br.edu.ifpb.autenticador.autenticador.repository.AddressRepository;
import br.edu.ifpb.autenticador.autenticador.repository.PermissionsRepository;
import br.edu.ifpb.autenticador.autenticador.repository.UserRepository;
import lombok.SneakyThrows;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AutenticadorApplication.class })
@WebAppConfiguration
class UserControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void init() {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setPassword("123");
        user.setEmail("test@test.com");
        userRepository.save(user);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    void updateAddressTest() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("userId", "1");
        params.set("rua", "R. Lorem Ipsum");
        params.set("numero", "123");
        params.set("bairro", "Dolor");
        params.set("cidade", "João Pessoa");
        params.set("estado", "Paraíba");
        params.set("pais", "Brasil");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update-address").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> user = userRepository.findById(1L);
        MatcherAssert.assertThat(user.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getAddress(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getAddress().getStreet(), Matchers.equalTo("R. Lorem Ipsum"));
        MatcherAssert.assertThat(user.get().getAddress().getNumber(), Matchers.equalTo("123"));
        MatcherAssert.assertThat(user.get().getAddress().getNeighborhood(), Matchers.equalTo("Dolor"));
        MatcherAssert.assertThat(user.get().getAddress().getCity(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getAddress().getCity().getName(), Matchers.equalTo("João Pessoa"));
        MatcherAssert.assertThat(user.get().getAddress().getCity().getState(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getAddress().getCity().getState().getName(), Matchers.equalTo("Paraíba"));
        MatcherAssert.assertThat(user.get().getAddress().getCity().getState().getCountry(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getAddress().getCity().getState().getCountry().getName(), Matchers.equalTo("Brasil"));

    }

    @SneakyThrows
    @Test
    void updatePermissionsTestAdmin() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("userId", "1");
        params.set("permissionName", "administrador");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update-permissions").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> user = userRepository.findById(1L);
        MatcherAssert.assertThat(user.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getPermission().getAdminPermission(), Matchers.is(true));

    }

    @SneakyThrows
    @Test
    void updatePermissionsTestReadonly() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("userId", "1");
        params.set("permissionName", "somenteLeitura");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update-permissions").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> user = userRepository.findById(1L);
        MatcherAssert.assertThat(user.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getPermission().getAdminPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getListPermission(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission().getDeletePermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getInsertPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getUpdatePermission(), Matchers.is(false));

    }

    @SneakyThrows
    @Test
    void updatePermissionsTestOperator() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("userId", "1");
        params.set("permissionName", "operador");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update-permissions").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> user = userRepository.findById(1L);
        MatcherAssert.assertThat(user.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getPermission().getAdminPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getListPermission(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission().getDeletePermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getInsertPermission(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission().getUpdatePermission(), Matchers.is(true));

    }

    @SneakyThrows
    @Test
    void updatePermissionsTestDefault() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("userId", "1");
        params.set("permissionName", "inexistente");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update-permissions").params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<User> user = userRepository.findById(1L);
        MatcherAssert.assertThat(user.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(user.get().getPermission(), Matchers.notNullValue());
        MatcherAssert.assertThat(user.get().getPermission().getAdminPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getListPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getDeletePermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getInsertPermission(), Matchers.is(false));
        MatcherAssert.assertThat(user.get().getPermission().getUpdatePermission(), Matchers.is(false));

    }

}

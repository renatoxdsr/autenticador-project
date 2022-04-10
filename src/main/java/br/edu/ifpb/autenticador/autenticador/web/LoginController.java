package br.edu.ifpb.autenticador.autenticador.web;


import br.edu.ifpb.autenticador.autenticador.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> checkCredentials(@RequestParam("email") String email, @RequestParam("senha") String password) {
        authService.validateCredentials(email, password);
        return ResponseEntity.ok("Parabéns, credenciais corretas!");
    }

}

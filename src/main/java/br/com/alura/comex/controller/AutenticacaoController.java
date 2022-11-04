package br.com.alura.comex.controller;

import br.com.alura.comex.config.security.TokenService;
import br.com.alura.comex.model.dto.input.TokenDto;
import br.com.alura.comex.model.dto.output.LoginOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auntenticar(@RequestBody @Valid LoginOutputDto loginOutput){

        UsernamePasswordAuthenticationToken dadosLogin = loginOutput.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }

}

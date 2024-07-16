package com.rias.forohub.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rias.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.rias.forohub.domain.usuario.Usuario;
import com.rias.forohub.infra.security.DatosJWTToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private com.rias.forohub.infra.security.TokenService tokenService;

  @PostMapping
  public ResponseEntity autenticacion(@RequestBody DatosAutenticacionUsuario datos) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(datos.nombre(),
        datos.contrasena());
    var usuarioAutenticado = authenticationManager.authenticate(authToken);
    var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
    return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
  }

}

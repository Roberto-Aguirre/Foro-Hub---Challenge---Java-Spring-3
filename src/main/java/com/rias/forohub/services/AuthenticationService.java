package com.rias.forohub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rias.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.rias.forohub.domain.usuario.Usuario;
import com.rias.forohub.infra.security.DatosJWTToken;
import com.rias.forohub.infra.security.TokenService;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public DatosJWTToken crearToken(DatosAutenticacionUsuario datosAutenticacionUsuario){
        System.out.println(datosAutenticacionUsuario);
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombre(),
                datosAutenticacionUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(datosAutenticacionUsuario);
        var response = new DatosJWTToken(JWTtoken);
        return response;
    }
}

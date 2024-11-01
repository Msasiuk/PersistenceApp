package com.persistenceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistenceapp.dao.IUsuarioDao;
import com.persistenceapp.model.Usuario;
import com.persistenceapp.util.JWTUtil;

@RestController // Controlador REST responsable de manejar las solicitudes de inicio de sesion
public class AuthController {

	@Autowired
	IUsuarioDao usuarioDao; // Inyeccion de dependencia, se busca la implementacion de dicha interfaz
	
	@Autowired
	JWTUtil jwtUtil;
	
	@PostMapping(value = "api/login")
	public String login(@RequestBody Usuario usuario){ // Metodo login recibe un objeto Usuario y verifica sus credenciales
		Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
		
		if (usuarioLogueado != null) { // Si es correcto, genera un token JWT usando JWTUtil
			String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
			return tokenJwt;
		} else {
			return "Fail";
		}
	}
}

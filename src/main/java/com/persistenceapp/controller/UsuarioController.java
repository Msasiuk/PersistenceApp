package com.persistenceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistenceapp.dao.IUsuarioDao;
import com.persistenceapp.model.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController // Controlador REST encargado de gestionar usuarios, de tomar las peticiones del usuario y devolver json
public class UsuarioController {
	
	@Autowired
	IUsuarioDao usuarioDao; // Inyeccion de dependencia, se busca la implementacion de dicha interfaz
	
	@GetMapping(value = "api/usuarios")
	public List<Usuario> getUsuario(){ // Devuelve una lista de usuarios.
		//List<Usuario> user = usuarioRepository.obtenerUsuarios();
		return usuarioDao.getUsuarios();
	}
	
	@DeleteMapping(value = "api/usuarios/{id}")
	public void eliminar(@PathVariable Long id){ // Elimina un usuario segun su ID
		usuarioDao.eliminar(id);
	}
	
	@PostMapping(value = "api/usuarios")
	public void registrarUsuario(@RequestBody Usuario usuario){ // Registra un nuevo usuario, con contrasenia hasheada
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		//String hash = argon2.hash(1, 2024, 1, usuario.getPassword()); Deprecado hash(int, int, int, String)
		char[] passChars = usuario.getPassword().toCharArray(); // Se convierte la contraseña a char[]
	    String passHash = argon2.hash(1, 2024, 1, passChars);
	    usuario.setPassword(passHash);

	    argon2.wipeArray(passChars); // Se limpia el array de caracteres para seguridad

	    usuarioDao.registrar(usuario);
		
	}
}

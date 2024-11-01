package com.persistenceapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.persistenceapp.model.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImpl implements IUsuarioDao { // Implementacion de IUsuarioDao

	@PersistenceContext
	private EntityManager entityManager; // Utiliza EntityManager de JPA para interactuar con la base de datos

	@Override
	public List<Usuario> getUsuarios() {
		String query = "from Usuario";
		//return entityManager.createQuery(query).getResultList();
		//Con este cambio le indico a la query que espero una lista de Usuario y no una lista generica
		return entityManager.createQuery(query, Usuario.class).getResultList(); 
	}

	@Override
	public void eliminar(Long id) {
		/*Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.remove(usuario);*/
		entityManager.remove(entityManager.find(Usuario.class, id));
	}

	@Override
	public void registrar(Usuario usuario) {
		entityManager.merge(usuario); // Se guarda la instancia recibida
	}

	@Override
	public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
	    String query = "FROM Usuario WHERE email = :email"; // No incluimos la contraseña en la consulta
	    List<Usuario> lista = entityManager.createQuery(query, Usuario.class)
	            .setParameter("email", usuario.getEmail())
	            .getResultList();
	    
	    // Verificar si se encontró un usuario con el email especificado
	    if (lista.isEmpty()) {
	        return null; // Retorna un objeto null
	    }
	    
		String passHash = lista.get(0).getPassword(); // Contraseña hasheada almacenada en la base de datos
	    
	    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	    
	    //boolean passEsIgual = argon2.verify(passHash, usuario.getPassword()); Deprecado verify(String, String)
		
	    // Convertir la contraseña ingresada a char[]
	    char[] passChars = usuario.getPassword().toCharArray();
	    if(argon2.verify(passHash, passChars)) {
	        argon2.wipeArray(passChars); // Se limpia el array de caracteres para seguridad
	    	return lista.get(0);
	    } else {
	    	argon2.wipeArray(passChars); // Se limpia el array de caracteres para seguridad
	    	return null;
	    }    
	}
}
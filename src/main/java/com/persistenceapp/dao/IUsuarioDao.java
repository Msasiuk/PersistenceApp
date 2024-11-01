package com.persistenceapp.dao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.persistenceapp.model.Usuario;

@Transactional // Interfaz DAO (Data Access Object) que define metodos CRUD para la entidad Usuario
public interface IUsuarioDao {

	List<Usuario> getUsuarios();

	void eliminar(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}

package com.persistenceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //Implementa un constructor vacio
@Entity // Esta notacion buscara el EntityManager para hacer la persistencia
@Table(name = "usuario") // La entity a su vez indica como se llama su tabla en la BD
public class Usuario { // Entidad que representa a los usuarios en la base de datos y modela al objeto
	
	@Getter //Implementa metodo set
	@Id // Mapea a los atributos en la BD
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Establece que la generacion de la PK es autoincremetal
	@Column(name = "id")
	private Long id;
	
	@Getter @Setter //Implementa metodo get y set
	@Column(name = "nombre", length = 50, nullable = false) // Nombre del campo en la tabla de la bd y largo permitido
	private String nombre;

	@Getter @Setter
	@Column(name = "apellido", length = 50, nullable = false)
	private String apellido;
	
	@Getter @Setter
	@Column(name = "email", length = 255, nullable = false)
	private String email;
	
	@Getter @Setter
	@Column(name = "telefono", length = 50)
	private String telefono;
	
	@Getter @Setter
	@Column(name = "password", length = 50)
	private String password;

	public Usuario(String nombre, String apellido, String email, String telefono, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
	}
	
}

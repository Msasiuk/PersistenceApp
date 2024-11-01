package com.persistenceapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer { // Clase de configuracion que extiende para configurar servidores servlet

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		return application.sources(PersistenceAppApplication.class);
	}

}

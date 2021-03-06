package com.carolinathomaz.apicarolinathomaz.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carolinathomaz.apicarolinathomaz.services.DBService;


/*profile para dev instanciando o banco de dados 
 * spring.jpa.hibernate.ddl-auto - create
 */

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	
	@Bean
	public boolean instantiateDattabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

}

package server.config;

import javax.persistence.EntityManager;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import server.dto.VehicleModel;

@Configuration
public class HibernateConfig {

	@Bean
	public EntityManager configureHibernate() {

		return new org.hibernate.cfg.Configuration().addProperties(Environment.getProperties())
				.addAnnotatedClass(VehicleModel.class).buildSessionFactory().createEntityManager();

	}

}

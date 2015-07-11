package me.loki2302;

import me.loki2302.entities.NoteDomainEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = NoteDomainEvent.class)
@EnableJpaRepositories(basePackageClasses = NoteDomainEventRepository.class)
public class Config {
}

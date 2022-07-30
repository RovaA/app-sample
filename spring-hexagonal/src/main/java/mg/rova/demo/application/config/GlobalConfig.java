package mg.rova.demo.application.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "mg.rova.demo.infrastructure.adapters.secondary.repository")
// @EnableElasticsearchRepositories("mg.rova.demo.infrastructure.adapters.secondary.elasticsearch")
public class GlobalConfig {
}

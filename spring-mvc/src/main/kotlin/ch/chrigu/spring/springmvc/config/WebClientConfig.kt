package ch.chrigu.spring.springmvc.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Configuration
class WebClientConfig {

    @Bean
    fun mongoClientSettingsBuilderCustomizer() = MongoClientSettingsBuilderCustomizer { customizer ->
        customizer.applyToConnectionPoolSettings {
            it.maxConnecting(10)
                .maxSize(0)
        }
    }

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder, @Value("\${backend.url}") baseUrl: String): RestTemplate = restTemplateBuilder.rootUri(baseUrl).build()

    @Bean
    fun backendHealthIndicator(restTemplate: RestTemplate) = HealthIndicator {
        try {
            restTemplate.getForEntity<Any>("/actuator/health")
                .let { if (it.statusCode.is2xxSuccessful) Health.up() else Health.down() }
                .build()
        } catch (e: Exception) {
            Health.down(e).build()
        }
    }
}

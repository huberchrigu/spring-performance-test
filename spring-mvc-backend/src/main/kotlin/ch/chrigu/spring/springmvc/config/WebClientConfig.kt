package ch.chrigu.spring.springmvc.config

import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebClientConfig {
    @Bean
    fun mongoClientSettingsBuilderCustomizer() = MongoClientSettingsBuilderCustomizer { customizer ->
        customizer.applyToConnectionPoolSettings {
            it.maxConnecting(10)
                .maxSize(0)
        }
    }
}

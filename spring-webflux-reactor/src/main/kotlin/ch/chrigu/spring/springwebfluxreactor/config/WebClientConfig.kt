package ch.chrigu.spring.springwebfluxreactor.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.ReactiveHealthIndicator
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

@Configuration
class WebClientConfig {
    @Bean
    fun clientHttpConnector() = ConnectionProvider.builder("10'000 connections")
        .maxConnections(1000)
        .pendingAcquireMaxCount(-1)
        .build()
        .let {
            ReactorClientHttpConnector(HttpClient.create(it))
        }

    @Bean
    fun mongoClientSettingsBuilderCustomizer() = MongoClientSettingsBuilderCustomizer { customizer ->
        customizer.applyToConnectionPoolSettings {
            it.maxConnecting(10)
                .maxSize(0)
        }
    }

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder, @Value("\${backend.url}") baseUrl: String) = webClientBuilder.baseUrl(baseUrl).build()

    @Bean
    fun backendHealthIndicator(webClient: WebClient) = ReactiveHealthIndicator {
        webClient.get().uri("/actuator/health")
            .retrieve()
            .toBodilessEntity()
            .map { Health.up().build() }
            .onErrorResume(Exception::class.java) { Mono.just(Health.down(it).build()) }
    }
}

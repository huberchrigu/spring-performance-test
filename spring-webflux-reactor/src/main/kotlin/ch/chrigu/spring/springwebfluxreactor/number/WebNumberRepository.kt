package ch.chrigu.spring.springwebfluxreactor.number

import ch.chrigu.spring.springmvc.number.GeneratedNumber
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Repository
class WebNumberRepository(webClientBuilder: WebClient.Builder, @Value("\${backend.url}") baseUrl: String) : NumberRepository {
    private val webClient = webClientBuilder.baseUrl(baseUrl).build()

    override fun createNumber(callId: UUID): Mono<GeneratedNumber> {
        return webClient.post().uri("/numbers")
            .bodyValue(mapOf("callId" to callId))
            .retrieve().bodyToMono(GeneratedNumber::class.java)
    }

    override fun findById(callId: UUID): Flux<GeneratedNumber> {
        return webClient.get().uri("/numbers?callId={callId}", callId)
            .retrieve()
            .bodyToFlux(GeneratedNumber::class.java)
    }
}

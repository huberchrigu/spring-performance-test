package ch.chrigu.spring.springwebfluxreactor.number

import ch.chrigu.spring.springmvc.number.GeneratedNumber
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Repository
class WebNumberRepository(private val webClient: WebClient) : NumberRepository {

    override fun createNumber(callId: UUID, delay: Int): Mono<GeneratedNumber> {
        return webClient.post().uri("/numbers?delay={delay}", delay)
            .bodyValue(mapOf("callId" to callId))
            .retrieve().bodyToMono(GeneratedNumber::class.java)
    }

    override fun findById(callId: UUID, delay: Int): Flux<GeneratedNumber> {
        return webClient.get().uri("/numbers?callId={callId}&delay={delay}", callId, delay)
            .retrieve()
            .bodyToFlux(GeneratedNumber::class.java)
    }
}

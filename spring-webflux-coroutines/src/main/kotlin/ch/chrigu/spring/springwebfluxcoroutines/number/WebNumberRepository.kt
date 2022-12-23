package ch.chrigu.spring.springwebfluxcoroutines.number

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow
import java.util.*

@Repository
class WebNumberRepository(private val webClient: WebClient) : NumberRepository {

    override suspend fun createNumber(callId: UUID, delay: Int): GeneratedNumber {
        return webClient.post().uri("/numbers?delay={delay}", delay)
            .bodyValue(mapOf("callId" to callId))
            .retrieve()
            .awaitBody()
    }

    override fun findById(callId: UUID, delay: Int): Flow<GeneratedNumber> {
        return webClient.get().uri("/numbers?callId={callId}&delay={delay}", callId, delay)
            .retrieve()
            .bodyToFlow()
    }
}

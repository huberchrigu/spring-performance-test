package ch.chrigu.spring.springwebfluxcoroutines.number

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow
import java.util.*

@Repository
class WebNumberRepository(webClientBuilder: WebClient.Builder) : NumberRepository {
    private val webClient = webClientBuilder.baseUrl("http://localhost:9005").build()

    override suspend fun createNumber(callId: UUID): GeneratedNumber {
        return webClient.post().uri("/numbers", mapOf("callId" to callId))
            .retrieve()
            .awaitBody()
    }

    override fun findById(callId: UUID): Flow<GeneratedNumber> {
        return webClient.get().uri("/numbers?callId={callId}", callId)
            .retrieve()
            .bodyToFlow()
    }
}

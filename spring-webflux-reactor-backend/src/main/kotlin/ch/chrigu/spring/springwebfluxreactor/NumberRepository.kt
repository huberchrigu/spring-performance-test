package ch.chrigu.spring.springwebfluxreactor

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface NumberRepository : ReactiveCrudRepository<GeneratedNumber, UUID> {
    fun findByCallId(callId: UUID): Flux<GeneratedNumber>
}

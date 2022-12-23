package ch.chrigu.spring.springwebfluxreactor.number

import ch.chrigu.spring.springmvc.number.GeneratedNumber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface NumberRepository {
    fun createNumber(callId: UUID, delay: Int): Mono<GeneratedNumber>
    fun findById(callId: UUID, delay: Int): Flux<GeneratedNumber>
}

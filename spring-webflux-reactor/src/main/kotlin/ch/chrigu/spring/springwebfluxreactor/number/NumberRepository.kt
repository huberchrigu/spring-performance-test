package ch.chrigu.spring.springwebfluxreactor.number

import ch.chrigu.spring.springmvc.number.GeneratedNumber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface NumberRepository {
    fun createNumber(callId: UUID): Mono<GeneratedNumber>
    fun findById(callId: UUID): Flux<GeneratedNumber>
}

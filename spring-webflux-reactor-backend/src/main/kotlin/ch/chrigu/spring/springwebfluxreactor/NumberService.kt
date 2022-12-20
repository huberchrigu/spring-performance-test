package ch.chrigu.spring.springwebfluxreactor

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NumberService(private val numberRepository: NumberRepository) {
    fun findByCallId(callId: UUID) = numberRepository.findByCallId(callId)
    fun createNumber(callId: UUID) = numberRepository.save(GeneratedNumber(callId = callId))

}

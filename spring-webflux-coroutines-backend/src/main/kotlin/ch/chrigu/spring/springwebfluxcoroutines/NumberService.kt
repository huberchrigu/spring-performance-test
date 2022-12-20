package ch.chrigu.spring.springwebfluxcoroutines

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NumberService(private val numberRepository: NumberRepository) {
    fun findByCallId(callId: UUID) = numberRepository.findByCallId(callId)
    suspend fun createNumber(callId: UUID) = numberRepository.save(GeneratedNumber(callId = callId))

}

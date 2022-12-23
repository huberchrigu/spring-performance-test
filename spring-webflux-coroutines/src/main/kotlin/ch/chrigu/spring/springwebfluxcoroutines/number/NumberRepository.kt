package ch.chrigu.spring.springwebfluxcoroutines.number

import kotlinx.coroutines.flow.Flow
import java.util.*

interface NumberRepository {
    suspend fun createNumber(callId: UUID, delay: Int): GeneratedNumber
    fun findById(callId: UUID, delay: Int): Flow<GeneratedNumber>
}

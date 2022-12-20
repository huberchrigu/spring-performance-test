package ch.chrigu.spring.springwebfluxcoroutines.number

import kotlinx.coroutines.flow.Flow
import java.util.*

interface NumberRepository {
    suspend fun createNumber(callId: UUID): GeneratedNumber
    fun findById(callId: UUID): Flow<GeneratedNumber>
}

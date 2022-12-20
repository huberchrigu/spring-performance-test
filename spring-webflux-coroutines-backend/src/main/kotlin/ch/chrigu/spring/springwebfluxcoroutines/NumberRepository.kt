package ch.chrigu.spring.springwebfluxcoroutines

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NumberRepository : CoroutineCrudRepository<GeneratedNumber, UUID> {
    fun findByCallId(callId: UUID): Flow<GeneratedNumber>
}

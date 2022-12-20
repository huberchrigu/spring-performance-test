package ch.chrigu.spring.springmvc

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface NumberRepository : CrudRepository<GeneratedNumber, UUID> {
    fun findByCallId(callId: UUID): List<GeneratedNumber>
}

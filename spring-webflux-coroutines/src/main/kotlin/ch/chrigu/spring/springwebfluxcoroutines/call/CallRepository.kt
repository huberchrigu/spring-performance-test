package ch.chrigu.spring.springwebfluxcoroutines.call

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CallRepository : CoroutineCrudRepository<Call, UUID>

package ch.chrigu.spring.springwebfluxreactor.call

import ch.chrigu.spring.springmvc.call.Call
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CallRepository : ReactiveCrudRepository<Call, UUID>

package ch.chrigu.spring.springmvc.call

import ch.chrigu.spring.springmvc.call.Call
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CallRepository : CrudRepository<Call, UUID>

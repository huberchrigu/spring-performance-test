package ch.chrigu.spring.springwebfluxcoroutines

import org.springframework.data.annotation.Id
import java.util.*

data class GeneratedNumber(@Id val id: UUID = UUID.randomUUID(), val number: Int = Random().nextInt(), val callId: UUID)

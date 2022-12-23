package ch.chrigu.spring.springwebfluxreactor

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import java.util.*

data class GeneratedNumber(@Id val id: UUID = UUID.randomUUID(), val number: Int = Random().nextInt(), @Indexed val callId: UUID)

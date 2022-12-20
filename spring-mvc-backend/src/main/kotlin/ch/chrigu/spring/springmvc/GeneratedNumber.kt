package ch.chrigu.spring.springmvc

import org.springframework.data.annotation.Id
import java.util.*
import kotlin.random.Random

data class GeneratedNumber(@Id val id: UUID = UUID.randomUUID(), val number: Int = Random.nextInt(), val callId: UUID)

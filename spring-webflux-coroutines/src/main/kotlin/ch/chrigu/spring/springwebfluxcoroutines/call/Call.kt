package ch.chrigu.spring.springwebfluxcoroutines.call

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

data class Call(@Id val id: UUID = UUID.randomUUID(), val createdTime: LocalDateTime = LocalDateTime.now(), val count: Int = Counter.getRequestCount())
object Counter {
    private val requestCount = AtomicInteger()

    fun getRequestCount() = requestCount.getAndIncrement()
}

package ch.chrigu.spring.springwebfluxcoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import kotlin.time.Duration.Companion.seconds

@RestController
@RequestMapping("/numbers")
class NumberController(private val numberService: NumberService) {
    @GetMapping
    suspend fun findByCallId(@RequestParam callId: UUID, @RequestParam delay: Long): Flow<GeneratedNumber> {
        delay(delay.seconds)
        return numberService.findByCallId(callId)
    }

    @PostMapping
    suspend fun createNumber(@RequestBody body: CreateNumberBody, @RequestParam delay: Long): ResponseEntity<GeneratedNumber> {
        delay(delay.seconds)
        val generatedNumber = numberService.createNumber(body.callId)
        return ResponseEntity.created(URI("/numbers/${generatedNumber.id}")).body(generatedNumber)
    }

    data class CreateNumberBody(val callId: UUID)
}

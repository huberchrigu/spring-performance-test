package ch.chrigu.spring.springwebfluxcoroutines

import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/numbers")
class NumberController(private val numberService: NumberService) {
    @GetMapping
    fun findByCallId(@RequestParam callId: UUID): Flow<GeneratedNumber> {
        return numberService.findByCallId(callId)
    }

    @PostMapping
    suspend fun createNumber(@RequestBody body: CreateNumberBody): ResponseEntity<GeneratedNumber> {
        val generatedNumber = numberService.createNumber(body.callId)
        return ResponseEntity.created(URI("/numbers/${generatedNumber.id}")).body(generatedNumber)
    }

    data class CreateNumberBody(val callId: UUID)
}

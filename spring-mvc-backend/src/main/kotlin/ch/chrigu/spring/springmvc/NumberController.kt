package ch.chrigu.spring.springmvc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/numbers")
class NumberController(private val numberService: NumberService) {
    @GetMapping
    fun findByCallId(@RequestParam callId: UUID): List<GeneratedNumber> {
        return numberService.findByCallId(callId)
    }

    @PostMapping
    fun createNumber(@RequestBody body: CreateNumberBody): ResponseEntity<GeneratedNumber> {
        val generatedNumber = numberService.createNumber(body.callId)
        return ResponseEntity.created(URI("/numbers/${generatedNumber.id}")).body(generatedNumber)
    }

    data class CreateNumberBody(val callId: UUID)
}

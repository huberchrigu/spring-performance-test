package ch.chrigu.spring.springwebfluxreactor

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/numbers")
class NumberController(private val numberService: NumberService) {
    @GetMapping
    fun findByCallId(@RequestParam callId: UUID): Flux<GeneratedNumber> {
        return numberService.findByCallId(callId)
    }

    @PostMapping
    fun createNumber(@RequestBody body: CreateNumberBody): Mono<ResponseEntity<GeneratedNumber>> {
        return numberService.createNumber(body.callId)
            .map { generatedNumber ->
                ResponseEntity.created(URI("/numbers/${generatedNumber.id}")).body(generatedNumber)
            }
    }

    data class CreateNumberBody(val callId: UUID)
}

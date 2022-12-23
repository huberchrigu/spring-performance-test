package ch.chrigu.spring.springwebfluxreactor

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/calls")
class CallController(private val callService: CallService) {
    @PostMapping
    fun addCall(@RequestParam delay: Int): Mono<ResponseEntity<CallService.CallWithNumbers>> {
        return callService.addCall(delay)
            .map { callWithNumber ->
                ResponseEntity.created(URI("/calls/" + callWithNumber.call.id)).body(callWithNumber)
            }
    }

    @GetMapping("/{id}")
    fun getCall(@PathVariable id: UUID, @RequestParam delay: Int): Mono<ResponseEntity<CallService.CallWithNumbers>> {
        return callService.getCall(id, delay)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }
}

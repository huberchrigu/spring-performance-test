package ch.chrigu.spring.springwebfluxcoroutines

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/calls")
class CallController(private val callService: CallService) {
    @PostMapping
    suspend fun addCall(@RequestParam delay: Int): ResponseEntity<CallService.CallWithNumbers> {
        val callWithNumber = callService.addCall(delay)
        return ResponseEntity.created(URI("/calls/" + callWithNumber.call.id)).body(callWithNumber)
    }

    @GetMapping("/{id}")
    suspend fun getCall(@PathVariable id: UUID, @RequestParam delay: Int): ResponseEntity<CallService.CallWithNumbers> {
        return callService.getCall(id, delay)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}

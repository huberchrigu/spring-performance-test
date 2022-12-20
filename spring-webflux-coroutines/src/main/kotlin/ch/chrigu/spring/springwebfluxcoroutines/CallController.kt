package ch.chrigu.spring.springwebfluxcoroutines

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/calls")
class CallController(private val callService: CallService) {
    @PostMapping
    suspend fun addCall(): ResponseEntity<CallService.CallWithNumbers> {
        val callWithNumber = callService.addCall()
        return ResponseEntity.created(URI("/calls/" + callWithNumber.call.id)).body(callWithNumber)
    }

    @GetMapping("/{id}")
    suspend fun getCall(@PathVariable id: UUID): ResponseEntity<CallService.CallWithNumbers> {
        return callService.getCall(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}

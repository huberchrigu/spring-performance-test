package ch.chrigu.spring.springmvc

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
    fun addCall(): ResponseEntity<CallService.CallWithNumbers> {
        val callWithNumber = callService.addCall()
        return ResponseEntity.created(URI("/calls/" + callWithNumber.call.id)).body(callWithNumber)
    }

    @GetMapping("/{id}")
    fun getCall(@PathVariable id: UUID): ResponseEntity<CallService.CallWithNumbers> {
        return callService.getCall(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }
}

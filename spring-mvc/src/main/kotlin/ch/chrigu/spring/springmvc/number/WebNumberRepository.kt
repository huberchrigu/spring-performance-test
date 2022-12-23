package ch.chrigu.spring.springmvc.number

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject
import java.util.*

@Repository
class WebNumberRepository(private val restTemplate: RestTemplate) : NumberRepository {
    override fun createNumber(callId: UUID, delay: Int): GeneratedNumber {
        return restTemplate.postForObject("/numbers?delay={delay}", mapOf("callId" to callId), delay)
    }

    override fun findById(callId: UUID, delay: Int): List<GeneratedNumber> {
        return restTemplate.getForObject("/numbers?callId={callId}&delay={delay}", Array<GeneratedNumber>::class.java, callId, delay)!!.toList()
    }
}

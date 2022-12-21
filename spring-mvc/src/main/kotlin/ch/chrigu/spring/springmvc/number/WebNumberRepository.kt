package ch.chrigu.spring.springmvc.number

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.client.postForObject
import java.util.*

@Repository
class WebNumberRepository(restTemplateBuilder: RestTemplateBuilder, @Value("\${backend.url}") baseUrl: String) : NumberRepository {
    private val restTemplate = restTemplateBuilder.rootUri(baseUrl).build()

    override fun createNumber(callId: UUID): GeneratedNumber {
        return restTemplate.postForObject("/numbers", mapOf("callId" to callId))
    }

    override fun findById(callId: UUID): List<GeneratedNumber> {
        return restTemplate.getForObject("/numbers?callId={callId}", Array<GeneratedNumber>::class.java, callId)!!.toList()
    }
}

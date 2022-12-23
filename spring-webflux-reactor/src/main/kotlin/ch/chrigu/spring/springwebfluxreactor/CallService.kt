package ch.chrigu.spring.springwebfluxreactor

import ch.chrigu.spring.springmvc.call.Call
import ch.chrigu.spring.springwebfluxreactor.call.CallRepository
import ch.chrigu.spring.springmvc.number.GeneratedNumber
import ch.chrigu.spring.springwebfluxreactor.number.NumberRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.util.*
import kotlin.collections.List

@Service
class CallService(private val callRepository: CallRepository, private val numberRepository: NumberRepository) {
    fun addCall(delay: Int): Mono<CallWithNumbers> {
        return callRepository.save(Call())
            .flatMap { call ->
                (0 until 5).toFlux()
                    .concatMap { numberRepository.createNumber(call.id, delay) }
                    .collectList()
                    .map { CallWithNumbers(call, it) }
            }
    }

    fun getCall(id: UUID, delay: Int): Mono<CallWithNumbers> {
        return callRepository.findById(id)
            .flatMap { call ->
                numberRepository.findById(call.id, delay)
                    .collectList()
                    .map { CallWithNumbers(call, it) }
            }
    }

    data class CallWithNumbers(val call: Call, val numbers: List<GeneratedNumber>)
}

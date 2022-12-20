package ch.chrigu.spring.springmvc

import ch.chrigu.spring.springmvc.call.Call
import ch.chrigu.spring.springmvc.call.CallRepository
import ch.chrigu.spring.springmvc.number.GeneratedNumber
import ch.chrigu.spring.springmvc.number.NumberRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.List

@Service
class CallService(private val callRepository: CallRepository, private val numberRepository: NumberRepository) {
    fun addCall(): CallWithNumbers {
        val call = callRepository.save(Call())
        val numbers = (0 until 10)
            .map { numberRepository.createNumber(call.id) }
        return CallWithNumbers(call, numbers)
    }

    fun getCall(id: UUID): Optional<CallWithNumbers> {
        return callRepository.findById(id)
            .map { call ->
                CallWithNumbers(call, numberRepository.findById(call.id))
            }
    }

    data class CallWithNumbers(val call: Call, val numbers: List<GeneratedNumber>)
}

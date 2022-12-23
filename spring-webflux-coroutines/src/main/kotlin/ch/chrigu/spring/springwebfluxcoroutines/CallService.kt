package ch.chrigu.spring.springwebfluxcoroutines

import ch.chrigu.spring.springwebfluxcoroutines.call.Call
import ch.chrigu.spring.springwebfluxcoroutines.call.CallRepository
import ch.chrigu.spring.springwebfluxcoroutines.number.GeneratedNumber
import ch.chrigu.spring.springwebfluxcoroutines.number.NumberRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.List

@Service
class CallService(private val callRepository: CallRepository, private val numberRepository: NumberRepository) {
    suspend fun addCall(delay: Int): CallWithNumbers {
        val call = callRepository.save(Call())
        val numbers = (0 until 5)
            .map { numberRepository.createNumber(call.id, delay) }
        return CallWithNumbers(call, numbers)
    }

    suspend fun getCall(id: UUID, delay: Int): CallWithNumbers? {
        return callRepository.findById(id)
            ?.let { call ->
                CallWithNumbers(call, numberRepository.findById(call.id, delay).toList())
            }
    }

    data class CallWithNumbers(val call: Call, val numbers: List<GeneratedNumber>)
}

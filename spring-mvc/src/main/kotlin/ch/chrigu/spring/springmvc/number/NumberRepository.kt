package ch.chrigu.spring.springmvc.number

import java.util.*

interface NumberRepository {
    fun createNumber(callId: UUID, delay: Int): GeneratedNumber
    fun findById(callId: UUID, delay: Int): List<GeneratedNumber>
}

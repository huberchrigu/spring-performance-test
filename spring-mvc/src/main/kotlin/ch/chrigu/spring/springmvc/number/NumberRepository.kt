package ch.chrigu.spring.springmvc.number

import java.util.*

interface NumberRepository {
    fun createNumber(callId: UUID): GeneratedNumber
    fun findById(callId: UUID): List<GeneratedNumber>
}

package ch.chrigu.spring.springwebfluxreactor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebfluxReactorBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringWebfluxReactorBackendApplication>(*args)
}

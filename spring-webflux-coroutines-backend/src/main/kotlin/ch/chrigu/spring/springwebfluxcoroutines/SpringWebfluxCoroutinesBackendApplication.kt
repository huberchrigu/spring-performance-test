package ch.chrigu.spring.springwebfluxcoroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebfluxCoroutinesBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringWebfluxCoroutinesBackendApplication>(*args)
}

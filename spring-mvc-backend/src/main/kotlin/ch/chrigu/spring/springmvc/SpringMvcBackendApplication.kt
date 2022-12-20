package ch.chrigu.spring.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringMvcBackendApplication

fun main(args: Array<String>) {
    runApplication<SpringMvcBackendApplication>(*args)
}

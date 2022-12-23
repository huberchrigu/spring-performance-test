package ch.chrigu.spring.test

import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*

abstract class AbstractSpringTest(private val scenario: String, port: Int) : Simulation() {

    private fun createCall(delay: Int) =
        exec(
            http("Create call ($delay s delay)")
                .post("/calls?delay=$delay")
                .check(header("Location").saveAs("location"))
        )
            .pause(1)
            .exec(
                http("Get call ($delay s delay)")
                    .get("#{location}?delay=$delay")
            )
            .pause(1)

    private val httpProtocol =
        http.baseUrl("http://localhost:$port")
            .contentTypeHeader("application/json")
            .acceptHeader("application/json")

    private val scenarios = listOf(0, 2, 8)
        .map { delay ->
            scenario("$scenario $delay s")
                .exec(createCall(delay))
                .injectOpen(rampUsers(910 / (1 + delay)).during(30))
        }

    init {
        setUp(scenarios).protocols(httpProtocol)
    }
}

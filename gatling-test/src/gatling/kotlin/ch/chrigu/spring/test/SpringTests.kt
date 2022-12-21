package ch.chrigu.spring.test

import io.gatling.javaapi.core.*
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.*

class SpringTests : Simulation() {

    private fun createCall(id: String) =
        exec(
            http("$id - Create call")
                .post("/calls")
                .check(header("Location").saveAs("location"))
        )
            .pause(1)
            .exec(
                http("$id - Get call")
                    .get("#{location}")
            )
            .pause(1)

    private val httpProtocol =
        http
            .contentTypeHeader("application/json")
            .acceptHeader("application/json")

    private val scenarios = listOf(Pair("Spring MVC", 9000), Pair("Spring Webflux Reactor", 9002), Pair("Spring Webflux Coroutines", 9004))
        .map { (scenario, port) ->
            scenario(scenario)
                .exec(createCall(scenario))
                .injectOpen(rampUsers(3000).during(30))
                .protocols(http.baseUrl("http://localhost:$port"))
        }

    init {
        setUp(scenarios).protocols(httpProtocol)
    }
}

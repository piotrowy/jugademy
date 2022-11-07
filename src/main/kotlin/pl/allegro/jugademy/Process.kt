package pl.allegro.jugademy

import reactor.core.publisher.Mono
import java.time.Duration

object Process {
    fun calculate(delay: Long): Mono<String> =
        Mono.just("Processing takes $delay ms.")
            .delayElement(Duration.ofMillis(delay))
}
package pl.allegro.jugademy

import reactor.core.publisher.Mono
import java.time.Duration

object IO {
    fun process(delay: Long): Mono<String> =
        synchronized(IO) {
            Mono.just("IO takes $delay ms.")
                .delayElement(Duration.ofMillis(delay))
        }
}
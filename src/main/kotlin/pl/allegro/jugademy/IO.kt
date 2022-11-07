package pl.allegro.jugademy

import reactor.core.publisher.Mono
import java.time.Duration

object IO {
    fun process(delay: Long): Mono<String> =
        Mono.delay(Duration.ofMillis(delay))
            .then(Mono.justOrEmpty("IO takes $delay ms."))
}

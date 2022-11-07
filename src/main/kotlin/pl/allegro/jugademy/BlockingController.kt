package pl.allegro.jugademy

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/nonblocking")
class BlockingController {
    @GetMapping("/delay/{delay}/ms")
    fun delayXms(@PathVariable delay: Long): Mono<String> =
        Mono.zip(
            Process.calculate((delay / 10) * 9),
            IO.process(delay / 10)
        ).map { "${it.t1}\n${it.t2}" }
}

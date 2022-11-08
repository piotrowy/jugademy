package pl.allegro.jugademy

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/nonblocking")
object NonblockingController {
    @GetMapping("/delay/{delay}/ms")
    suspend fun delayXms(@PathVariable delay: Long): String = coroutineScope {
        val m1 = async { Process.calculate((delay / 10) * 9) }
        val m2 = async { IO.process(delay / 10) }
        "${m1.await()}\n${m2.await()}"
    }
}

package pl.allegro.jugademy

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blocking")
class BlockingController {
    @GetMapping("/delay/{delay}/ms")
    fun delayXms(@PathVariable delay: Long): String {
        val processing = Process.calculate(delay / 10 * 9)
        val io = IO.process(delay / 10)
        return "$processing\n$io"
    }
}

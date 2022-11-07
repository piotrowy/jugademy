package pl.allegro.jugademy

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blocking")
class BlockingController {

    private fun message(delay: Long): String {
        return "I'm delayed $delay milliseconds."
    }

    @GetMapping("/delay/{delay}/ms")
    fun delayXms(@PathVariable delay: Long): String {
        Thread.sleep(delay)
        return message(delay)
    }
}

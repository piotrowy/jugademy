package pl.allegro.jugademy

import kotlinx.coroutines.delay

object Process {
    suspend fun calculate(delay: Long): String {
        delay(delay)
        return "Processing takes $delay ms."
    }
}
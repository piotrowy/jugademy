package pl.allegro.jugademy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object IO {
    suspend fun process(delay: Long): String = withContext(Dispatchers.IO) {
        delay(delay)
        "IO takes $delay ms."
    }
}

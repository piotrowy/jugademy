package pl.allegro.jugademy

object IO {
    fun process(delay: Long): String {
        Thread.sleep(delay)
        return "IO takes $delay ms."
    }
}

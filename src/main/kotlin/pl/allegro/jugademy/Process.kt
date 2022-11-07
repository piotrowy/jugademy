package pl.allegro.jugademy

object Process {
    fun calculate(delay: Long): String {
        Thread.sleep(delay)
        return "Processing takes $delay ms."
    }
}

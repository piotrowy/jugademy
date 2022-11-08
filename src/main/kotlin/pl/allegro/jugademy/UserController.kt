package pl.allegro.jugademy

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

    @PostMapping("/{token}")
    fun createUser(@PathVariable token: String): User {
        val name = userWebService.getName(token)
        val email = userWebService.getName(token)
        val pesel = peselWebService.getName(token)
        val login = "x${name}x${calculateAge(pesel)}"
        User user = User (name, email, login, pesel)
        db.store(user)
        emailService.sendGreetings(user)
        if(user.hasAdsConsent()) {
            emailService.sendAdvertisingEmail(user)
        }
        return user
    }
}
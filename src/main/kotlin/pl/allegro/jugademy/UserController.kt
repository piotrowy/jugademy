package pl.allegro.jugademy

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

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
        if (user.hasAdsConsent()) {
            emailService.sendAdvertisingEmail(user)
        }
        return user
    }
}


@RestController
@RequestMapping("/api/users")
class UserController {

    @PostMapping("/{token}")
    suspend fun createUser(@PathVariable token: String): User {
        val name = userWebService.getName(token)
        val email = userWebService.getName(token)
        val pesel = peselWebService.getName(token)
        val login = "x${name}x${calculateAge(pesel)}"
        User user = User (name, email, login, pesel)
        db.store(user)
        emailService.sendGreetings(user)
        if (user.hasAdsConsent()) {
            emailService.sendAdvertisingEmail(user)
        }
        return user
    }
}


@RestController
@RequestMapping("/api/users")
class UserController {

    @PostMapping("/{token}")
    fun createUser(@PathVariable token: String): Mono<User> {
        return Mono.zip(
            userWebService.getName(token),
            userWebService.getName(token),
            peselWebService.getName(token)
        ) // Tuple(String, String, String)
            .map { userData ->
                User(
                    userData.v1,
                    userData.v2,
                    "x${userData.v1}x${calculateAge(userData.v3)}",
                    userData.v3
                )
            } // User
            .flatMap { user -> db.store(user) } // User
            .doOnNext { user -> emailService.sendGreetings(user) } // User from previous mono
            .doOneNext {
                if (user -> user.hasAdsConsent()) {
                    emailService.sendAdvertisingEmail(user)
                        .subscribe()
                }
            }
    }
}
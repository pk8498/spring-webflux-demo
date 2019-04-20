package hyeyoung.demo.web;

import hyeyoung.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import hyeyoung.demo.repository.UserRepository;

/**
 * Spring Web MVC 와 같은 방식인 어노테이션 기반 설정 지원
 */
@RestController
@RequestMapping("/annotation/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{loginId}")
    private Mono<User> getUserByLoginId(@PathVariable String loginId) {

        return Mono.defer(() -> Flux.fromIterable(userRepository.findByLoginId(loginId))
                                    .take(1)
                                    .next() // Flux -> Mono
                                    .subscribeOn(Schedulers.elastic()));


    }

    @GetMapping("/all")
    private Flux<User> getAllUser() {
        return Flux.defer(() -> Flux.fromIterable(userRepository.findAll())
                                    .subscribeOn(Schedulers.elastic()));
    }

}

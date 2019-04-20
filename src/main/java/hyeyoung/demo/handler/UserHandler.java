package hyeyoung.demo.handler;

import hyeyoung.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import hyeyoung.demo.repository.UserRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Functional Endpoints 기반
 */
@Component
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> getUserByLoginId(ServerRequest request) {
        String loginId = request.pathVariable("loginId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = Mono.defer(() -> Flux.fromIterable(userRepository.findByLoginId(loginId))
                .take(1)
                .next() // Flux -> Mono
                .subscribeOn(Schedulers.elastic()));

        return userMono.flatMap(user -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(user)))
                .switchIfEmpty(notFound);

    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<User> user = Flux.defer(() -> Flux.fromIterable(userRepository.findAll())
                .subscribeOn(Schedulers.elastic()));

        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, User.class);
    }



}

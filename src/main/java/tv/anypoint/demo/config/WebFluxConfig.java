package tv.anypoint.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import tv.anypoint.demo.handler.UserHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

    /**
     * @RestController 와 동일한 역할을 한다.
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {

        return RouterFunctions
                .route(GET("/user/{loginId}").and(accept(APPLICATION_JSON)), handler::getUserByLoginId)
                .andRoute(GET("/user").and(accept(APPLICATION_JSON)), handler::getAllUser);
    }

}

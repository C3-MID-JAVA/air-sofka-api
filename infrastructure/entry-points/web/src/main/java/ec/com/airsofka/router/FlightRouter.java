package ec.com.airsofka.router;

import ec.com.airsofka.handler.FlightHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FlightRouter {
    private final FlightHandler flightHandler;

    public FlightRouter(FlightHandler flightHandler) {
        this.flightHandler = flightHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> flightRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/flights").and(accept(MediaType.APPLICATION_JSON)), flightHandler::create)
                .andRoute(RequestPredicates.GET("/flights"), flightHandler::getAll)
                .andRoute(RequestPredicates.POST("/flights/update").and(accept(MediaType.APPLICATION_JSON)), flightHandler::update);
    }
}

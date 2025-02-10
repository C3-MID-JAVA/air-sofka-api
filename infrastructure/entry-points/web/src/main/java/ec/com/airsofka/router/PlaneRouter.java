package ec.com.airsofka.router;

import ec.com.airsofka.handler.PlaneHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class PlaneRouter {
    private PlaneHandler planeHandler;

    public PlaneRouter(PlaneHandler planeHandler) {
        this.planeHandler = planeHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> planeRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/planes").and(accept(MediaType.APPLICATION_JSON)), planeHandler::create)
                .andRoute(RequestPredicates.GET("/planes"), planeHandler::getAll);
    }
}

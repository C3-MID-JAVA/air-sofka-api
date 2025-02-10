package ec.com.airsofka.router;

import ec.com.airsofka.handler.SeatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class SeatRouter {
    private final SeatHandler seatHandler;

    public SeatRouter(SeatHandler seatHandler) {
        this.seatHandler = seatHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> seatRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/seatReservation").and(accept(MediaType.APPLICATION_JSON)), seatHandler::update)
                .andRoute(RequestPredicates.GET("/seats/{idFlight}"), seatHandler::getAllByFlightId);
    }


}

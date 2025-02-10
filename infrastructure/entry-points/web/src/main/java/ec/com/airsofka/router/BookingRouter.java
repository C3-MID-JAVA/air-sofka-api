package ec.com.airsofka.router;

import ec.com.airsofka.handler.BookingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BookingRouter {

    private final BookingHandler bookingHandler;

    public BookingRouter(BookingHandler bookingHandler) {
        this.bookingHandler = bookingHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> bookingRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/booking").and(accept(MediaType.APPLICATION_JSON)), bookingHandler::create)
                .andRoute(RequestPredicates.POST("/booking/cost").and(accept(MediaType.APPLICATION_JSON)), bookingHandler::getCostBreakdown);
    }
}


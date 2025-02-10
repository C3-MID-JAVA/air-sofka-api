package ec.com.airsofka.router;

import ec.com.airsofka.handler.MaintenanceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class MaintenanceRouter {
    private MaintenanceHandler maintenanceHandler;

    public MaintenanceRouter(MaintenanceHandler maintenanceHandler) {
        this.maintenanceHandler = maintenanceHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> maintenanceRoutes(){
        return RouterFunctions
                .route(RequestPredicates.POST("/maintenances").and(accept(MediaType.APPLICATION_JSON)), maintenanceHandler::create);
    }
}

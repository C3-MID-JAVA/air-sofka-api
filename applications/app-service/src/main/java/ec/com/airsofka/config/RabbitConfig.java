package ec.com.airsofka.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    private final RabbitProperties envProperties;


    public RabbitConfig(RabbitProperties envProperties) {
        this.envProperties = envProperties;
    }

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange(envProperties.getBookingExchange(), true, false);
    }

    @Bean
    public Queue bookingQueue() {
        return new Queue(envProperties.getBookingQueue(), true);
    }

    @Bean
    public Binding bookingBinding() {
        return BindingBuilder.bind(bookingQueue())
                .to(bookingExchange())
                .with(envProperties.getBookingRoutingKey());
    }

    @Bean
    public TopicExchange flightCreatedExchange() {
        return new TopicExchange(envProperties.getFlightCreatedExchange(), true, false);
    }

    @Bean
    public Queue flightCreatedQueue() {
        return new Queue(envProperties.getFlightCreatedQueue(), true);
    }

    @Bean
    public Binding flightCreatedBinding() {
        return BindingBuilder.bind(flightCreatedQueue())
                .to(flightCreatedExchange())
                .with(envProperties.getFlightCreatedRoutingKey());
    }

    @Bean
    public TopicExchange flightUpdatedExchange() {
        return new TopicExchange(envProperties.getFlightUpdatedExchange(), true, false);
    }

    @Bean
    public Queue flightUpdatedQueue() {
        return new Queue(envProperties.getFlightUpdatedQueue(), true);
    }

    @Bean
    public Binding flightUpdatedBinding() {
        return BindingBuilder.bind(flightUpdatedQueue())
                .to(flightUpdatedExchange())
                .with(envProperties.getFlightUpdatedRoutingKey());
    }

    @Bean
    public TopicExchange userCreatedExchange() {
        return new TopicExchange(envProperties.getUserCreatedExchange(), true, false);
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue(envProperties.getUserCreatedQueue(), true);
    }

    @Bean
    public Binding userCreatedBinding() {
        return BindingBuilder.bind(userCreatedQueue())
                .to(userCreatedExchange())
                .with(envProperties.getUserCreatedRoutingKey());
    }

    @Bean
    public TopicExchange userUpdatedExchange() {
        return new TopicExchange(envProperties.getUserUpdatedExchange(), true, false);
    }

    @Bean
    public Queue userUpdatedQueue() {
        return new Queue(envProperties.getUserUpdatedQueue(), true);
    }

    @Bean
    public Binding userUpdatedBinding() {
        return BindingBuilder.bind(userUpdatedQueue())
                .to(userUpdatedExchange())
                .with(envProperties.getUserUpdatedRoutingKey());
    }

    @Bean
    public TopicExchange planeCreatedExchange() {
        return new TopicExchange(envProperties.getPlaneCreatedExchange(), true, false);
    }

    @Bean
    public Queue planeCreatedQueue() {
        return new Queue(envProperties.getPlaneCreatedQueue(), true);
    }

    @Bean
    public Binding planeCreatedBinding() {
        return BindingBuilder.bind(planeCreatedQueue())
                .to(planeCreatedExchange())
                .with(envProperties.getPlaneCreatedRoutingKey());
    }

    @Bean
    public TopicExchange maintenanceExchange() {
        return new TopicExchange(envProperties.getMaintenanceExchange(), true, false);
    }

    @Bean
    public Queue maintenanceQueue() {
        return new Queue(envProperties.getMaintenanceQueue(), true);
    }

    @Bean
    public Binding maintenanceBinding() {
        return BindingBuilder.bind(maintenanceQueue())
                .to(maintenanceExchange())
                .with(envProperties.getMaintenanceRoutingKey());
    }

    @Bean
    public TopicExchange planeUpdatedExchange() {
        return new TopicExchange(envProperties.getPlaneUpdatedExchange(), true, false);
    }

    @Bean
    public Queue planeUpdatedQueue() {
        return new Queue(envProperties.getPlaneUpdatedQueue(), true);
    }

    @Bean
    public Binding planeUpdatedBinding() {
        return BindingBuilder.bind(planeUpdatedQueue())
                .to(planeUpdatedExchange())
                .with(envProperties.getPlaneCreatedRoutingKey());
    }

    @Bean
    public TopicExchange seatCreatedExchange() {
        return new TopicExchange(envProperties.getSeatCreatedExchange(), true, false);
    }

    @Bean
    public Queue seatCreatedQueue() {
        return new Queue(envProperties.getSeatCreatedQueue(), true);
    }

    @Bean
    public Binding seatCreatedBinding() {
        return BindingBuilder.bind(seatCreatedQueue())
                .to(seatCreatedExchange())
                .with(envProperties.getSeatCreatedRoutingKey());
    }

    public TopicExchange seatReservedExchange() {
        return new TopicExchange(envProperties.getSeatReservedExchange(), true, false);
    }

    @Bean
    public Queue seatReservedQueue() {
        return new Queue(envProperties.getSeatReservedQueue(), true);
    }

    @Bean
    public Binding seatReservedBinding() {
        return BindingBuilder.bind(seatReservedQueue())
                .to(seatReservedExchange())
                .with(envProperties.getSeatReservedRoutingKey());
    }

    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(envProperties.getEmailExchange(), true, false);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(envProperties.getEmailQueue(), true);
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue())
                .to(emailExchange())
                .with(envProperties.getEmailRoutingKey());
    }

    @Bean
    public TopicExchange billingExchange() {
        return new TopicExchange(envProperties.getBillingExchange(), true, false);
    }

    @Bean
    public Queue billingQueue() {
        return new Queue(envProperties.getBillingQueue(), true);
    }

    @Bean
    public Binding billingBinding() {
        return BindingBuilder.bind(billingQueue())
                .to(billingExchange())
                .with(envProperties.getBillingRoutingKey());
    }

    @Bean
    public TopicExchange contactExchange() {
        return new TopicExchange(envProperties.getContactExchange(), true, false);
    }

    @Bean
    public Queue contactQueue() {
        return new Queue(envProperties.getContactQueue(), true);
    }

    @Bean
    public Binding contactBinding() {
        return BindingBuilder.bind(contactQueue())
                .to(contactExchange())
                .with(envProperties.getContactRoutingKey());
    }

    @Bean
    public TopicExchange passengerExchange() {
        return new TopicExchange(envProperties.getPassengerExchange(), true, false);
    }

    @Bean
    public Queue passengerQueue() {
        return new Queue(envProperties.getPassengerQueue(), true);
    }

    @Bean
    public Binding passengerBinding() {
        return BindingBuilder.bind(passengerQueue())
                .to(passengerExchange())
                .with(envProperties.getPassengerRoutingKey());
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeBeans(AmqpAdmin amqpAdmin) {
        return event -> {
            amqpAdmin.declareExchange(bookingExchange());
            amqpAdmin.declareQueue(bookingQueue());
            amqpAdmin.declareBinding(bookingBinding());

            amqpAdmin.declareExchange(flightCreatedExchange());
            amqpAdmin.declareQueue(flightCreatedQueue());
            amqpAdmin.declareBinding(flightCreatedBinding());

            amqpAdmin.declareExchange(userCreatedExchange());
            amqpAdmin.declareQueue(userCreatedQueue());
            amqpAdmin.declareBinding(userCreatedBinding());


            amqpAdmin.declareExchange(userUpdatedExchange());
            amqpAdmin.declareQueue(userUpdatedQueue());
            amqpAdmin.declareBinding(userUpdatedBinding());

            amqpAdmin.declareExchange(planeCreatedExchange());
            amqpAdmin.declareQueue(planeCreatedQueue());
            amqpAdmin.declareBinding(planeCreatedBinding());

            amqpAdmin.declareExchange(maintenanceExchange());
            amqpAdmin.declareQueue(maintenanceQueue());
            amqpAdmin.declareBinding(maintenanceBinding());

            amqpAdmin.declareExchange(planeUpdatedExchange());
            amqpAdmin.declareQueue(planeUpdatedQueue());
            amqpAdmin.declareBinding(planeUpdatedBinding());

            amqpAdmin.declareExchange(seatCreatedExchange());
            amqpAdmin.declareQueue(seatCreatedQueue());
            amqpAdmin.declareBinding(seatCreatedBinding());

            amqpAdmin.declareExchange(seatReservedExchange());
            amqpAdmin.declareQueue(seatReservedQueue());
            amqpAdmin.declareBinding(seatReservedBinding());

            amqpAdmin.declareExchange(emailExchange());
            amqpAdmin.declareQueue(emailQueue());
            amqpAdmin.declareBinding(emailBinding());

            amqpAdmin.declareExchange(billingExchange());
            amqpAdmin.declareQueue(billingQueue());
            amqpAdmin.declareBinding(billingBinding());

            amqpAdmin.declareExchange(contactExchange());
            amqpAdmin.declareQueue(contactQueue());
            amqpAdmin.declareBinding(contactBinding());

            amqpAdmin.declareExchange(passengerExchange());
            amqpAdmin.declareQueue(passengerQueue());
            amqpAdmin.declareBinding(passengerBinding());
        };
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplateBean(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}

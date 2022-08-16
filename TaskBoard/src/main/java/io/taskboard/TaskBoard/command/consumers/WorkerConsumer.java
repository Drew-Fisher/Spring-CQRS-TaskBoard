package io.taskboard.TaskBoard.command.consumers;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import io.taskboard.TaskBoard.command.features.publishevent.IEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class WorkerConsumer {
    private final IEventPublisher.Publisher eventPublisher;

    public WorkerConsumer(IEventPublisher.Publisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    // Create a message channel for messages arriving from the subscription `sub-one`.
    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    // Create an inbound channel adapter to listen to the subscription `sub-one` and send
// messages to the input message channel.
    @Bean
    public PubSubInboundChannelAdapter inboundChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, "Workers");
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(String.class);
        return adapter;
    }

    // Define what happens to the messages arriving in the message channel.
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {

        System.out.println(payload);
//        try {
//            Decoder decoder = DecoderFactory.get().jsonDecoder(WorkerCreated.getClassSchema(),payload);
//            DatumReader<WorkerCreated> reader = new SpecificDatumReader<>(WorkerCreated.getClassSchema());
//
//            WorkerCreated rawDataCapsule = reader.read(null , decoder);
//
//            CreateWorker.Event event = CreateWorker.Event.builder()
//                    .aggregateId(UUID.fromString(String.valueOf(rawDataCapsule.getWorkerId())))
//                    .build();
//            eventPublisher.publish(event);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
            message.ack();
//        }
    }
}

package micronaut.logbook.example;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.*;
import io.micronaut.http.netty.channel.ChannelPipelineCustomizer;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.netty.*;
import javax.inject.Singleton;

@Singleton
public class LogbookPipelineCustomizer implements BeanCreatedEventListener<ChannelPipelineCustomizer> {
    private final Logbook logbook;

    public LogbookPipelineCustomizer() {
        this.logbook = Logbook.create();
    }

    @Override
    public ChannelPipelineCustomizer onCreated(BeanCreatedEvent<ChannelPipelineCustomizer> event) {
        ChannelPipelineCustomizer customizer = event.getBean();

        if (customizer.isServerChannel()) {
            customizer.doOnConnect(pipeline -> {
                pipeline.addAfter(
                        ChannelPipelineCustomizer.HANDLER_HTTP_SERVER_CODEC,
                        "logbook",
                        new LogbookServerHandler(logbook)
                );
                return pipeline;
            });
        } else {
            customizer.doOnConnect(pipeline -> {
                pipeline.addAfter(
                        ChannelPipelineCustomizer.HANDLER_HTTP_CLIENT_CODEC,
                        "logbook",
                        new LogbookClientHandler(logbook)
                );
                return pipeline;
            });
        }
        return customizer;
    }
}

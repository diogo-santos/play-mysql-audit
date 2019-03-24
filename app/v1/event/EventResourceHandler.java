package v1.event;

import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Handles presentation of Event resources, which map to JSON.
 */
public class EventResourceHandler {
    private final EventRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public EventResourceHandler(EventRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<Stream<EventResource>> find() {
        return repository
                .list()
                .thenApplyAsync(postDataStream -> postDataStream.map(this::mapDataToResource), ec.current());
    }

    public CompletionStage<EventResource> create(EventResource resource) {
        final EventData data =  mapResourceToData(resource);
        return repository
                .create(data)
                .thenApplyAsync(this::mapDataToResource, ec.current());
    }

    public CompletionStage<Optional<EventResource>> lookup(String id) {
        return repository
                .get(Long.parseLong(id))
                .thenApplyAsync(optionalData -> optionalData.map(this::mapDataToResource), ec.current());
    }

    private EventData mapResourceToData(EventResource resource) {
        if (resource == null) {
            return null;
        }
        return new EventData(resource.getId(),
                resource.getUserId(),
                resource.getTenant(),
                resource.getEventTime(),
                resource.getOperation(),
                resource.getUserRole(),
                resource.getIdentifier(),
                resource.getAction(),
                resource.getEntity(),
                resource.getEntityId(),
                resource.getSessionId(),
                resource.getDetails());
    }

    private EventResource mapDataToResource(EventData data) {
        if (data == null) {
            return null;
        }
        return new EventResource(data.getId(),
                data.getUserId(),
                data.getTenant(),
                data.getEventTime(),
                data.getOperation(),
                data.getUserRole(),
                data.getIdentifier(),
                data.getAction(),
                data.getEntity(),
                data.getEntityId(),
                data.getSessionId(),
                data.getDetails());
    }
}
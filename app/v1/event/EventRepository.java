package v1.event;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface EventRepository {

    CompletionStage<Stream<EventData>> list();

    CompletionStage<EventData> create(EventData EventData);

    CompletionStage<Optional<EventData>> get(Long id);
}
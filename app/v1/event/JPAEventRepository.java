package v1.event;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * A repository that provides a non-blocking API with a custom execution context
 * and circuit breaker.
 */
@Singleton
public class JPAEventRepository implements EventRepository {

    private final JPAApi jpaApi;
    private final EventExecutionContext ec;
    private final CircuitBreaker circuitBreaker = new CircuitBreaker().withFailureThreshold(1).withSuccessThreshold(3);

    @Inject
    public JPAEventRepository(JPAApi api, EventExecutionContext ec) {
        this.jpaApi = api;
        this.ec = ec;
    }

    @Override
    public CompletionStage<Stream<EventData>> list() {
        return supplyAsync(() -> wrap(this::select), ec);
    }

    @Override
    public CompletionStage<EventData> create(EventData EventData) {
        return supplyAsync(() -> wrap(em -> insert(em, EventData)), ec);
    }

    @Override
    public CompletionStage<Optional<EventData>> get(Long id) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> lookup(em, id))), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Optional<EventData> lookup(EntityManager em, Long id) throws SQLException {
        throw new SQLException("Call this to cause the circuit breaker to trip");
        //return Optional.ofNullable(em.find(EventData.class, id));
    }

    private Stream<EventData> select(EntityManager em) {
        TypedQuery<EventData> query = em.createQuery("SELECT p FROM EventData p", EventData.class);
        return query.getResultList().stream();
    }

    private EventData insert(EntityManager em, EventData eventData) {
        return em.merge(eventData);
    }
}
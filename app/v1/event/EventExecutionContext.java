package v1.event;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

/**
 * Custom execution context wired to "event.repository" thread pool
 */
public class EventExecutionContext extends CustomExecutionContext {

    @Inject
    public EventExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "event.repository");
    }
}
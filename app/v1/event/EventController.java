package v1.event;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(EventAction.class)
public class EventController extends Controller {

    private HttpExecutionContext ec;
    private EventResourceHandler handler;

    @Inject
    public EventController(HttpExecutionContext ec, EventResourceHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> list() {
        return handler
                .find()
                .thenApplyAsync(posts ->
                                {final List<EventResource> postList = posts.collect(Collectors.toList());
                                return ok(Json.toJson(postList));}
                                , ec.current());
    }

    public CompletionStage<Result> show(String id) {
        return handler
                .lookup(id)
                .thenApplyAsync(optionalResource ->
                        optionalResource
                                .map(resource -> ok(Json.toJson(resource)))
                                .orElseGet(Results::notFound)
                        , ec.current());
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        final EventResource resource = Json.fromJson(json, EventResource.class);
        return handler
                .create(resource)
                .thenApplyAsync(savedResource -> created(Json.toJson(savedResource)), ec.current());
    }
}
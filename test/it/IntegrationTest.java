package it;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import v1.event.EventData;
import v1.event.EventRepository;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.*;

public class IntegrationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testList() {
        EventRepository repository = app.injector().instanceOf(EventRepository.class);
        EventData data = new EventData();

        repository.create(data);

        Http.RequestBuilder request = new Http.RequestBuilder()
                                                .method(GET)
                                                .uri("/v1/events");
        Result result = route(app, request);
        final String tenant = contentAsString(result);
        assertThat(tenant, containsString("tenant"));
    }

    @Test
    public void testCircuitBreakerOnShow() {
        EventRepository repository = app.injector().instanceOf(EventRepository.class);
        repository.create(new EventData());

        Http.RequestBuilder request = new Http.RequestBuilder()
                                                .method(GET)
                                                .uri("/v1/events/1");

        Result result = route(app, request);
        assertThat(result.status(), equalTo(SERVICE_UNAVAILABLE));
    }
}
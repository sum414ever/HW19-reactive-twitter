package edu.cursor.reactaccesstwitter.controller;

import edu.cursor.reactaccesstwitter.service.TwitterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import twitter4j.Place;
import twitter4j.Status;

@RestController
@RequestMapping("/tweet")
public class TwitterController {

    @GetMapping(path = "/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> feed() {
        ConnectableFlux<Status> flux = TwitterService.getTwitterStream();

        return flux.map(Status::getText);
    }

    @GetMapping(path = "/filtered", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> filtered() {
        ConnectableFlux<Status> flux = TwitterService.getTwitterStream();

        return flux
                .filter(status -> status.getText().contains("the"))
                .map(Status::getText);
    }

    @GetMapping(path = "/onePerSecond", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> onePerSecond() {
        ConnectableFlux<Status> flux = TwitterService.getTwitterStream();

        Flux<Status> filtered = flux.filter(status -> {
            Place place = status.getPlace();
            if (place != null) {
                return status.getPlace().getCountryCode().equalsIgnoreCase("us");
            }
            return false;
        });

        return filtered
                .map(status -> status.getCreatedAt().toGMTString() + " " + status.getPlace().getCountryCode() + " " + status.getText());
    }
}

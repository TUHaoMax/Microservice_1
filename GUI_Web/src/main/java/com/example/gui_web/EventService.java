package com.example.gui_web;

import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@Log
public class EventService {
    public String EventMsg;
    @StreamListener(Sink.INPUT)
    public void handleReaderEvent(AuthorEvent authorEvent) {
        log.info("handleReaderEvent() >> event=" + authorEvent);

        EventMsg= "ID: "+"RWR-"+authorEvent.author.getId()+" ; "+
                " Name: "+authorEvent.getAuthor().getName()+" ; " +
                " status: "+authorEvent.eventMessage +" ; " +
                " value:"+authorEvent.paid+" ; "+
                " date: "+authorEvent.time;
    }


}

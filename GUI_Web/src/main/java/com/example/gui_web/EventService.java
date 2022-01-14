package com.example.gui_web;

import com.example.gui_web.Service_Controller.Author_Controller;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
@Log
public class EventService {

    public String EventMsg="no message now";

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

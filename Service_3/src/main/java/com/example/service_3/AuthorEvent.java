package com.example.service_3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Source;
import java.time.Instant;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorEvent {

    public enum EventMessage {
        Paid,underpaid,income
    }
   long time;
   Author author;
   EventMessage eventMessage;
   long paid;

   public static AuthorEvent PaidorUn(Author author,long paid){
       if(paid>0){
           return new AuthorEvent(Instant.now().toEpochMilli(),author,EventMessage.Paid,paid);
       }else {
           return new AuthorEvent(Instant.now().toEpochMilli(),author,EventMessage.underpaid,paid);
       }

   }

    public static AuthorEvent income(Author author,long paid){
        return new AuthorEvent(Instant.now().toEpochMilli(),author,EventMessage.income,paid);
    }

}

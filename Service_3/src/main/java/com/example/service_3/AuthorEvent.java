package com.example.service_3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Source;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorEvent {

    public enum EventMessage {
        Paid,underpaid,income
    }
   String time;
   Author author;
   EventMessage eventMessage;
   long paid;

   public static AuthorEvent PaidorUn(Author author,long paid){
       Date date = new Date();
       SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

       if(paid>0){
           return new AuthorEvent(dateFormat.format(date),author,EventMessage.Paid,paid);
       }else {
           return new AuthorEvent(dateFormat.format(date),author,EventMessage.underpaid,paid);
       }

   }

    public static AuthorEvent income(Author author,long paid){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return new AuthorEvent(dateFormat.format(date),author,EventMessage.income,paid);
    }

}

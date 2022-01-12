package com.example.payment_management;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor   // optional since default
@Getter
@ToString
public class AuthorEvent {

    public enum EventMessage {
        Paid,underpaid,income
    }
   long time;
   Author author;
   EventMessage eventMessage;
   long paid;



}

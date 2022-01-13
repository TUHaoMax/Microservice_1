package com.example.gui_web;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@Getter
@ToString
public class AuthorEvent {
    public enum EventMessage {
        Paid,underpaid,income
    }
    String time;
   Author author;
   EventMessage eventMessage;
   long paid;

}

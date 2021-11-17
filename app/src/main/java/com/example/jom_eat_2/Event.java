package com.example.jom_eat_2;

//greet when have any event to celebrate
public class Event{

    private String event_name;
    private String img_event;

    //constructor
    public Event(String event_name, String img_event) {
        this.event_name = event_name;
        this.img_event = img_event;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getImg_event() {
        return img_event;
    }

    public void setImg_event(String img_event) {
        this.img_event = img_event;
    }
}

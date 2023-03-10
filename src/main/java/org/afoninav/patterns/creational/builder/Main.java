package com.afoninav.patterns.creational.builder;

/**
 * Пример шаблона Builder (Строитель).
 * Где объектом - распорядителем является Календарь,
 * который создаёт клиент.
 * Где строителями являются два класса:
 * TaskBuilder и MeetingBuilder.
 */
public class Main {
    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        calendar.setBuilder(new MeetingBuilder());
        Event event = calendar.createEvent("Выкинуть мусор", "2023,02,19","At home","22-00","22-30");
        System.out.println(event);
    }
}


/**
 * Календарь (Director), или распорядитель.
 * Конструирует объект, пользуясь интерфейсом AbstractBuilder.
 */
class MyCalendar {
    AbstractBuilder builder;

    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

    public Event createEvent(String title, String date, String location, String startTime, String endTime) {
        try {
            builder.buildEvent();
            builder.buildEventType();
            builder.buildEventTitle(title);
            builder.buildEventDate(date);
            builder.buildEventLocation(location);
            builder.buildEventStartTime(startTime);
            builder.buildEventEndTime(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.getEvent();
    }
}

/**
 * Событие (Product)
 */
class Event {
    private EventType type;
    private String title;
    private String date;
    private String location;
    private String startTime;
    private String endTime;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setSimpleTaskType() {
        this.type = EventType.SimpleTask;
    }

    public void setMeetingType() {
        this.type = EventType.Meeting;
    }

    private enum EventType {
        SimpleTask, Meeting
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

/**
 * AbstractBuilder - задает абстрактный интерфейс
 * для создания частей объекта Event.
 */
interface AbstractBuilder {
    void buildEvent();
    void buildEventType();
    void buildEventTitle(String title);
    void buildEventDate(String date);
    void buildEventLocation(String location) throws WrongBuilderException;
    void buildEventStartTime(String startTime) throws WrongBuilderException;
    void buildEventEndTime(String endTime)throws WrongBuilderException;
    Event getEvent();
}

/**
 * Строитель для простых задач-напоминалок.
 * Конструирует и собирает вместе части продукта
 * посредством реализации интерфейса.
 */
class TaskBuilder implements AbstractBuilder {
    protected Event event;

    @Override
    public void buildEvent() {
        if (this.event == null) {
            this.event = new Event();
        }
    }

    @Override
    public void buildEventType() {
        this.event.setSimpleTaskType();
    }

    @Override
    public void buildEventTitle(String title) {
        this.event.setTitle(title);
    }

    @Override
    public void buildEventDate(String date) {
        this.event.setDate(date);
    }

    @Override
    public void buildEventLocation(String location) throws WrongBuilderException {
        throw new WrongBuilderException();
    }

    @Override
    public void buildEventStartTime(String startTime) throws WrongBuilderException {
        throw new WrongBuilderException();
    }

    @Override
    public void buildEventEndTime(String endTime) throws WrongBuilderException {
        throw new WrongBuilderException();
    }

    @Override
    public Event getEvent() {
        return this.event == null ? new Event() : event;
    }
}

/**
 * Строитель для событий, с указанием даты и время начала и окончания.
 * Конструирует и собирает вместе части продукта
 * посредством реализации интерфейса.
 */
class MeetingBuilder extends TaskBuilder {
    @Override
    public void buildEventType() {
        this.event.setMeetingType();
    }

    @Override
    public void buildEventLocation(String location) {
        this.event.setLocation(location);
    }

    @Override
    public void buildEventStartTime(String startTime) {
        this.event.setStartTime(startTime);
    }

    @Override
    public void buildEventEndTime(String endTime) {
        this.event.setEndTime(endTime);
    }
}

class WrongBuilderException extends Exception {
}







package com.afoninav.patterns.behavoiral.command;

/**
 * Пример шаблона Command.
 */
public class Main {
    public static void main(String[] args) {
        Meeting meeting = new Meeting("Совет директоров", "Конференц зал", "24.11.2025");
        System.out.println(meeting);

        ChangeLocationCommand command = new ChangeLocationCommand(meeting);
        command.setNewLocation("Ледовый дворец спорта");
        command.execute();
        System.out.println(meeting);

        command.undo();
        System.out.println(meeting);

        command.redo();
        System.out.println(meeting);

    }
}

/**
 * Интерфейс определяет базовую операцию.
 */
interface Command {
    void execute();
}

/**
 * Расширение базового интерфейса
 * операциями отмены и возврата.
 */
interface UndoableCommand extends Command {
    void undo();
    void redo();
}

/**
 * Класс описывающий событие.
 * В данном случае это встреча.
 */
class Meeting {
    private String title;
    private String location;
    private String date;

    public Meeting(String title, String location, String date) {
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

/**
 * Класс дополняет приложение логикой, необходимой для
 * изменения места запланированного события.
 */
class ChangeLocationCommand implements UndoableCommand {
    private Meeting meeting;
    private String oldLocation;
    private String newLocation;

    public ChangeLocationCommand(Meeting meeting) {
        this.meeting = meeting;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    @Override
    public void execute() {
        oldLocation = meeting.getLocation();
        meeting.setLocation(newLocation);
    }

    @Override
    public void undo() {
        meeting.setLocation(oldLocation);
    }

    @Override
    public void redo() {
        meeting.setLocation(newLocation);
    }
}
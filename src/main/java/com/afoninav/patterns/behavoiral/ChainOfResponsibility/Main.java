package com.afoninav.patterns.behavoiral.ChainOfResponsibility;

import java.util.LinkedList;
import java.util.List;

/**
 * Пример Шаблона "Цепочка Ответственности"
 */
public class Main {
    public static void main(String[] args) {
        ProjectItem project = new Project("Главный проект", "Главный инженер: Иванов", "Выдача кредитов онлайн");
        ProjectItem item1 = new Task("Задача 1","Сбор данных о заёмщике", project);
        ProjectItem item11 = new Task("Задача 1.1","Младший инженер: Петров",
                "Распознавание подлинности отфотографированных документов.",item1);

        ProjectItem item2 = new Task("Задача2", "Инженер: Сидоров","Верификация данных",project);
        ProjectItem item21 = new Task("Ззадача 2.2","Сверка данных с гос.реестром",item2);
        ProjectItem item211 = new Task("Задача 2.1.1","Парсинг данных из реестра в API приложения",item21);


        System.out.println(item1.getDetail());
        System.out.println(item11.getDetail());

        System.out.println(item211.getDetail());
    }
}

/**
 * Интерфейс определяет методы для
 * взаимодействия элемнтов, которые являются
 * любой из застей проекта.
 */
interface ProjectItem {
    ProjectItem getParent();
    String getOwner();
    String getDetail();
    List<ProjectItem> getProjectItems();
    void addProjectItem(ProjectItem item);
    void removerProjectItem(ProjectItem item);
}

/**
 * Корневой элемент проекта.
 */
class Project implements ProjectItem {
    private String name;
    private String owner;
    private String details;
    private List<ProjectItem> projectItems;

    public Project(String name, String owner, String details) {
        this.name = name;
        this.owner = owner;
        this.details = details;
        this.projectItems = new LinkedList<>();
    }

    @Override
    public ProjectItem getParent() {
        return null;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public String getDetail() {
        return this.details;
    }

    @Override
    public List<ProjectItem> getProjectItems() {
        return this.projectItems;
    }

    @Override
    public void addProjectItem(ProjectItem item) {
        this.projectItems.add(item);
    }

    @Override
    public void removerProjectItem(ProjectItem item) {
        this.projectItems.remove(item);

    }
}

/**
 * Задачи, из которых состоит проект.
 * Задача может быть как простой так и составной.
 * В методах getOwner() и getDetail() прослеживаются
 * элементы шаблона "Цепочка ответственности".
 */
class Task implements ProjectItem {
    private boolean primaryTask;
    private String name;
    private String owner;
    private String details;
    private List<ProjectItem> projectItems;
    private ProjectItem parent;

    public Task(String name, String details, ProjectItem parent) {
        this.name = name;
        this.owner = null;
        this.details = details;
        this.parent = parent;
        this.primaryTask = false;
    }

    public Task(String name, String owner, String details, ProjectItem parent) {
        this.name = name;
        this.owner = owner;
        this.details = details;
        this.parent = parent;
        this.primaryTask = true;
    }

    @Override
    public ProjectItem getParent() {
        return parent;
    }

    /**
     * По логике вещей, если задача не является первичной,
     * то за её исполнение отвечает владелец основной задачи.
     * @return
     */
    @Override
    public String getOwner() {
        if (owner == null) {
            return parent.getOwner();
        } else {
            return owner;
        }
    }

    /**
     * Аналогично, если ззадача не является первичной, а лишь
     * частью общей задачи, то вызов метода у такой задачи вернёт
     * более общую картину из описания вышестоящих задач.
     * @return
     */
    @Override
    public String getDetail() {
        if (primaryTask) {
            return details;
        } else {
            return parent.getDetail() + "\n" + details;
        }
    }

    @Override
    public List<ProjectItem> getProjectItems() {
        return projectItems;
    }

    @Override
    public void addProjectItem(ProjectItem item) {
        this.projectItems.add(item);
    }

    @Override
    public void removerProjectItem(ProjectItem item) {
        this.projectItems.remove(item);
    }
}









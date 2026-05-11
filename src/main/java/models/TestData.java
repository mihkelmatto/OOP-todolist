package models;

import java.time.LocalDateTime;

public class TestData {

    public static TaskGroup getTestgroup() {

        // USER
        User user = new User("Toomas");

        // TASKS
        Task task1 = new Task(
                "Buy groceries",
                "Milk, bread, eggs",
                LocalDateTime.of(2026, 7, 10, 15, 30)
        );

        Task task2 = new Task(
                "Finish homework",
                "Math exercises",
                LocalDateTime.of(2026, 6, 5, 18, 0)
        );

        Task task3 = new Task(
                "Gym workout",
                "Leg day training",
                LocalDateTime.now().plusDays(1)
        );

        Task task4 = new Task(
                "Read book",
                "Clean Code chapters 1-3",
                LocalDateTime.now().plusDays(3)
        );

        Task task5 = new Task(
                "Project meeting",
                "OOP project discussion",
                LocalDateTime.now().plusHours(5)
        );

        Task task6 = new Task(
                "Call mom",
                "Weekly check-in call",
                LocalDateTime.now().plusDays(2)
        );

        Task task7 = new Task(
                "Fix bugs",
                "JavaFX UI issues",
                LocalDateTime.now().plusHours(10)
        );

        Task task8 = new Task(
                "Write report",
                "Final project documentation",
                LocalDateTime.now().plusDays(7)
        );

        Task[] tasks = {
                task1, task2, task3, task4,
                task5, task6, task7, task8
        };

        // TASK GROUP
        TaskGroup group = new TaskGroup(user.getUUID());
        group.setGroupname("My Tasks");
        group.addTasks(tasks);

        return group;
    }
}

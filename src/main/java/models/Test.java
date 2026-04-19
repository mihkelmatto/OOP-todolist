package models;

public class Test {
    public static void main(String[] args) {
        /*
        Igal kasutajal võiks tekkida loomise hetkel isiklik taskgroup, kuhu ta ülesandeid lisab
        gruppe saab juurde teha ning grupi loomise ajal saab sinna liigutada vanu ülesandeid
        Gruppi saab lisada teisi kasutajaid, mis juhul on kõik ülesanded jagatud
        Kui UI avaneb, siis otsitakse, mis taskGroupis ta on ning edastatakse vastavad grupid

        TODO: Userite nimekirja peaks ehk Setiks tegema, et ei peaks duplikaate kontrollima ning otsimisaeg oleks väiksem (Setil on vist O1)
        */
        User user1 = new User("user1");
        User user2 = new User("user2");

        Task task1 = new Task("title1", "description");
        Task task2 = new Task("title2", "description");
        Task task3 = new Task("title3", "description");
        Task[] tasks = {task1, task2, task3};

        // grupi loomine
        TaskGroup taskGroup1 = new TaskGroup(user1);
        taskGroup1.addTasks(tasks);
        System.out.println(taskGroup1);

        // gruppi saab ka loomise hetkel argumente lisada,
        // hiljem kasutajaid lisada ja nime vahetada
        TaskGroup taskGroup2 = new TaskGroup(user2, tasks);
        taskGroup2.addUsers(user1);
        taskGroup2.setGroupname("nickname");
        System.out.println(taskGroup2);
        
    }
}

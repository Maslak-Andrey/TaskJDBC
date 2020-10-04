package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        //drop
        userService.dropUsersTable();
        //creat
        userService.createUsersTable();
        //add
        userService.saveUser("Ivan", "Ivanov", (byte) 55);
        userService.saveUser("Petr", "Petrov", (byte) 44);
        userService.saveUser("Sidr", "Sidorov", (byte) 33);
        userService.saveUser("Alesha", "Popovich", (byte) 22);
        //getAll
        System.out.println(userService.getAllUsers());
        //clear
        userService.cleanUsersTable();
        //drop
        userService.dropUsersTable();

        Util.shutdown();
    }
}

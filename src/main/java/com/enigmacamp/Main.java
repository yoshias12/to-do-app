package com.enigmacamp;

import com.enigmacamp.config.HibernateUtil;
import com.enigmacamp.controller.TodoController;
import com.enigmacamp.controller.UserController;
import com.enigmacamp.model.User;
import com.enigmacamp.service.UserService;
import com.enigmacamp.util.Helper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        Helper helper = new Helper();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateUtil.class);

        UserController uc = (UserController) applicationContext.getBean("userController");
        TodoController td = (TodoController) applicationContext.getBean("todoController"); ;

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = helper.inputInt("Pilih menu");
            switch (choice) {
                case 1:
                    uc.register();
                    break;
                case 2:
                    uc.login();
                    if (uc.getLoggedInUser() != null) {
                        User loggedInUser = uc.getLoggedInUser();
                        while (true) {
                            System.out.println("1. Add Todo");
                            System.out.println("2. View Todo");
                            System.out.println("3. Update Todo");
                            System.out.println("4. Delete Todo");
                            System.out.println("5. Logout");
                            int todoChoice = helper.inputInt("Pilih menu");

                            switch (todoChoice) {
                                case 1:
                                    td.createTodo(loggedInUser);
                                    break;
                                case 2:
                                    td.listTodo(loggedInUser);
                                    break;
                                case 3:
                                    td.listTodo(loggedInUser);
                                    td.updateTodo(loggedInUser);
                                    break;
                                case 4:
                                    td.listTodo(loggedInUser);
                                    td.deleteTodo(loggedInUser);
                                    break;
                                case 5:
                                    System.out.println("Logged out successfully!");
                                    uc.setLoggedInUser(null);
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }
                            if (todoChoice == 5) {
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }
}

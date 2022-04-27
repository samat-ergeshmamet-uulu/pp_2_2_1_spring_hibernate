package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car("Tesla", 12);
        Car car2 = new Car("Audi", 22);
        Car car3 = new Car("Mercedes_Benz", 32);
        userService.add(new User("Samat", "Suban", "samat@mail.ru", car1));
        userService.add(new User("Oleg", "Mihailov", "oleg@mail.ru", car2));
        userService.add(new User("Masha", "Sidorova", "masha@mail.ru", car3));
        userService.add(new User("Elena", "Orlova", "elena@mail.ru", car3));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println("User who owns the car2 - " + userService.userWhoOwnsTheCar(car2.getModel(), car2.getSeries()));

        context.close();
    }
}

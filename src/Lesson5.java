import lesson5.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Lesson5 {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван", "Иванович", "Иванов", dateFormat.parse("01.01.1980"), "Инженер", (long) 3500000, "ivan@ivanov.ru", "+7-999-999-99-99"));
        employees.add(new Employee("Петр", "Петрович", "Петров", dateFormat.parse("02.02.1970"), "Менеджер", 35000.50, "petr@petrov.ru", "+7-888-999-99-99"));
        employees.add(new Employee("Сидор", "Сидорович", "Сидоров", dateFormat.parse("03.03.1975"), "Директор", 60000.70, "sidor@sidorov.ru", "+7-777-999-99-99"));
        employees.add(new Employee("Ксения", "Петровна", "Иванова", dateFormat.parse("04.04.1990"), "Консультант", (long) 2500000, "kseniya@ivanova.ru", "+7-666-999-99-99"));
        employees.add(new Employee("Нина", "Ивановна", "Петрова", dateFormat.parse("05.05.1995"), "Кофе-менеджер", (long) 1500000, "nina@petrova.ru", "+7-555-999-99-99"));

        System.out.println("Все сотрудники: " + employees);
        System.out.println("\nСотрудники старше 40 лет: ");
        for (Employee employee : employees) {
            if (employee.getBirthday().before(new Date(new Date().getTime() - 1261440000000L))) {
                employee.print();
            }
        }
    }
}

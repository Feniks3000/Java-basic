package staff;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    String name;
    String patronymic;
    String surname;
    Date birthday;
    String position;
    Long salary; // в копейках
    String email;
    String phone;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    // Принимает зарплату в копейках
    public Employee(String name, String patronymic, String surname, Date birthday, String position, Long salaryInKopecks, String email, String phone) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthday = birthday;
        this.position = position;
        this.salary = salaryInKopecks;
        this.email = email;
        this.phone = phone;
    }

    public Employee(String name, String patronymic, String surname, Date birthday, String position, Double salaryInRubles, String email, String phone) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthday = birthday;
        this.position = position;
        this.salary = (long) (salaryInRubles * 100);
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + dateFormat.format(birthday) +
                ", position='" + position + '\'' +
                ", salary=" + String.format("%d руб. %d коп.", salary / 100, salary % 100) +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void print() {
        System.out.println(toString());
    }
}

package login.DB.model.models;

public class User {

    public final int id;
    private String name;
    private String surname;
    private int age;
    private String city;
    private String email;
    private String username;
    private String password;

    public User(int id, String name, String surname, int age, String city, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
        this.username = username;
        this.password = password;
    }

    public User(int id, String name, String surname, int age, String city, String email, String username, String password) {
        this(id, name, surname, age, city, username, password);
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                '}';
    }
}

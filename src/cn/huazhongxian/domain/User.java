package cn.huazhongxian.domain;

public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String  QQ;
    private String  email;
    private String username;
    private String password;

    public User(String name, String gender, int age, String address, String QQ, String email) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.QQ = QQ;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String name, String gender, int age, String address, String QQ, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.QQ = QQ;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(int id, String name, String gender, int age, String address, String QQ, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.QQ = QQ;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", QQ='" + QQ + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

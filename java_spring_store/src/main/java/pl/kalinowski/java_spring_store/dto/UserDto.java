package pl.kalinowski.java_spring_store.dto;


import pl.kalinowski.java_spring_store.model.User;


public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.surname = user.getSurname();
        userDto.email = user.getEmail();
        userDto.login = user.getLogin();
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id); // dla update
        user.setName(userDto.name);
        user.setSurname(userDto.surname);
        user.setEmail(userDto.email);
        user.setLogin(userDto.login);
        return user;
    }
}

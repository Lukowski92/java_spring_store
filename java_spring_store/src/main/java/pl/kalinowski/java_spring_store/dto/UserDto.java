package pl.kalinowski.java_spring_store.dto;


import jakarta.validation.constraints.*;
import pl.kalinowski.java_spring_store.model.User;


public class UserDto {

    @Null(message = "ID must not be provided during creation")
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Size(min = 2, message = "Surname must have at least 2 characters")
    private String surname;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers")
    private String login;

    private BasketDto basket;

    public BasketDto getBasket() {
        return basket;
    }

    public void setBasket(BasketDto basket) {
        this.basket = basket;
    }

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
        if (user.getBasket() != null) {
            userDto.setBasket(BasketDto.fromEntity(user.getBasket()));
        }
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id);
        user.setName(userDto.name);
        user.setSurname(userDto.surname);
        user.setEmail(userDto.email);
        user.setLogin(userDto.login);

        if (userDto.basket != null) {
            user.setBasket(BasketDto.toEntity(userDto.basket));
        }

        return user;
    }

}

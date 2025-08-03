package pl.kalinowski.java_spring_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kalinowski.java_spring_store.dto.BasketDto;
import pl.kalinowski.java_spring_store.dto.UserDto;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.model.User;
import pl.kalinowski.java_spring_store.service.UserService;
import java.util.List;


@Tag(name = "User", description = "User managment API")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(summary = "Get all users")
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Add new user")
    @PostMapping("/addUser")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto) {
        User createdUser = userService.createUser(dto);
        return ResponseEntity.status(201).body(UserDto.fromEntity(createdUser));
    }



    @Operation(summary = "Update an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserByID(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create basket for user")
    @PostMapping("/{id}/basket")
    public ResponseEntity<UserDto> createBasket(@PathVariable Long id) {
        UserDto userWithBasket = userService.createBasketForUser(id);
        return ResponseEntity.ok(userWithBasket);
    }


}

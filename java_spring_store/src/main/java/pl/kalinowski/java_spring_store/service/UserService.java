package pl.kalinowski.java_spring_store.service;

import org.springframework.stereotype.Service;
import pl.kalinowski.java_spring_store.dto.UserDto;
import pl.kalinowski.java_spring_store.exception.ResourceNotFoundException;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.model.User;
import pl.kalinowski.java_spring_store.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long id) {
        return UserDto.fromEntity(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public User createUser(UserDto userDto) {
        User user = UserDto.toEntity(userDto);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }

    public UserDto createBasketForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getBasket() != null) {
            throw new IllegalStateException("User already has a basket");
        }

        Basket basket = new Basket();
        basket.setUser(user);
        user.setBasket(basket);

        userRepository.save(user);

        return UserDto.fromEntity(user);
    }

}

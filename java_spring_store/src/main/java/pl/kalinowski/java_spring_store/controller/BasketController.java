package pl.kalinowski.java_spring_store.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kalinowski.java_spring_store.dto.BasketDto;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.service.BasketService;

import java.util.List;

@Tag(name="Basket", description = "Basket managment APIs")
@RestController
@RequestMapping("/api")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @Operation(summary = "Get all baskets")
    @GetMapping("/baskets")
    public ResponseEntity<List<BasketDto>> getAllBaskets() {
        List<BasketDto> baskets = basketService.getAllBaskets();
        return ResponseEntity.ok(baskets);
    }

    @Operation(summary = "Get basket by ID")
    @GetMapping("/basket/{id}")
    public ResponseEntity<BasketDto> getBasketById(@PathVariable Long id) {
        BasketDto basket = basketService.getBasketById(id);
        return ResponseEntity.ok(basket);
    }

    @Operation(summary = "Create a new basket")
    @PostMapping("/basket")
    public ResponseEntity<Basket> createBasket(@RequestBody BasketDto basket) {
        Basket createdBasket = basketService.createBasket(basket);
        return ResponseEntity.status(201).body(createdBasket);
    }

    @Operation(summary = "Update an existing basket")
    @PutMapping("/basket/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable Long id, @RequestBody BasketDto basket) {
        Basket updatedBasket = basketService.updateBasket(id, basket);
        return ResponseEntity.ok(updatedBasket);
    }

    @Operation(summary = "Delete a basket")
    @DeleteMapping("/basket/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Long id) {
        basketService.deleteBasketById(id);
        return ResponseEntity.noContent().build();
    }
}

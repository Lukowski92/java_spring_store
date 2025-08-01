package pl.kalinowski.java_spring_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kalinowski.java_spring_store.dto.BasketDto;
import pl.kalinowski.java_spring_store.dto.BasketItemDto;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.service.BasketService;

import java.math.BigDecimal;
import java.util.List;

@Tag(name="Basket", description = "Basket managment APIs")
@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @Operation(summary = "Get all baskets")
    @GetMapping("/allbaskets")
    public ResponseEntity<List<BasketDto>> getAllBaskets() {
        List<BasketDto> baskets = basketService.getAllBaskets();
        return ResponseEntity.ok(baskets);
    }

    @Operation(summary = "Get basket by ID")
    @GetMapping("/onebasket/{id}")
    public ResponseEntity<BasketDto> getBasketById(@PathVariable Long id) {
        BasketDto basket = basketService.getBasketById(id);
        return ResponseEntity.ok(basket);
    }

    @Operation(summary = "Create a new basket")
    @PostMapping("/createbasket")
    public ResponseEntity<BasketDto> createBasket() {
        Basket basket = basketService.createBasket();
        return ResponseEntity.ok(BasketDto.fromEntity(basket));
    }
    @Operation(summary = "Get items in basket with quantities")
    @GetMapping("/{id}/items")
    public ResponseEntity<List<BasketItemDto>> getItemsInBasket(@PathVariable Long id) {
        List<BasketItemDto> items = basketService.getItems(id);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Delete a basket")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Long id) {
        basketService.deleteBasketById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Add existing item to basket by item ID and quantity")
    @PostMapping("/{id}/items")
    public ResponseEntity<BasketDto> addItemToBasket(
            @PathVariable Long id,
            @RequestBody BasketItemDto basketItemDto) {

        BasketDto updatedBasket = basketService.addItemToBasket(id, basketItemDto);
        return ResponseEntity.ok(updatedBasket);
    }

    @Operation(summary = "Get total value of basket")
    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getBasketPriceById(@PathVariable Long id) {
        BigDecimal total = basketService.getBasketPriceById(id);
        return ResponseEntity.ok(total);
    }




}

package pl.kalinowski.java_spring_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kalinowski.java_spring_store.dto.ItemDto;
import pl.kalinowski.java_spring_store.model.Item;
import pl.kalinowski.java_spring_store.service.ItemService;

import java.util.List;

@Tag(name = "Item", description = "Item managment APIs")
@RestController
@RequestMapping("/apit")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Get all items")
    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Get item by ID")
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> getItemByID(@PathVariable Long id) {
        ItemDto item = itemService.getItemByID(id);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Create a new item")
    @PostMapping("/item")
    public ResponseEntity<Item> createBasket(@RequestBody ItemDto item) {
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(201).body(createdItem);
    }

    @Operation(summary = "Update an existing item")
    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemDto item) {
        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @Operation(summary = "Delete item")
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

}

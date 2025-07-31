package pl.kalinowski.java_spring_store.dto;

import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.model.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class BasketDto {
    long id;
    List<BasketItemDto> items;

    public List<BasketItemDto> getItems() {
        return items;
    }

    public void setItems(List<BasketItemDto> items) {
        this.items = items;
    }

    // Zamiana encji na DTO
    public static BasketDto fromEntity(Basket basket) {
        BasketDto dto = new BasketDto();
        dto.setId(basket.getId());

        if (basket.getBasketItems() != null) {
            dto.setItems(
                    basket.getBasketItems().stream()
                            .map(BasketItemDto::fromEntity)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Zamiana DTO na encję
    public static Basket toEntity(BasketDto dto) {
        Basket basket = new Basket();
        basket.setId(dto.getId());
        // koszyk bez produktow dodajemy je pzez serwis
        return basket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}

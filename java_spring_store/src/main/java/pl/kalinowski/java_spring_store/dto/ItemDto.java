package pl.kalinowski.java_spring_store.dto;

import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.model.Item;

public class ItemDto {

    long id;
    String name;
    float price;
    int quantity;

    //	zamiana encji na DTO (np. JSON)
    public static ItemDto fromEntity(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.id = item.getId();
        itemDto.name = item.getItemName();
        itemDto.price = item.getItemPrice();
        itemDto.quantity = item.getQuantity();

        return itemDto;
    }

    // zamiana Dto (np. JSON) na encję
    public static Item toEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.id);
        item.setItemName(itemDto.name);
        item.setItemPrice(itemDto.price);
        item.setQuantity(itemDto.quantity);

        return item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

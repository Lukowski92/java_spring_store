package pl.kalinowski.java_spring_store.dto;

import pl.kalinowski.java_spring_store.model.BasketItem;

import java.math.BigDecimal;

public class BasketItemDto {
    private Long itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private Integer quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static BasketItemDto fromEntity(BasketItem basketItem) {
        BasketItemDto dto = new BasketItemDto();
        dto.setItemId(basketItem.getItem().getId());
        dto.setItemName(basketItem.getItem().getItemName());
        dto.setItemPrice(basketItem.getItem().getItemPrice());
        dto.setQuantity(basketItem.getQuantity());
        return dto;
    }


}
package pl.kalinowski.java_spring_store.dto;
import pl.kalinowski.java_spring_store.model.Basket;


public class BasketDto {
    long id;
    String item;

    //	zamiana encji na DTO (np. JSON)
    public static BasketDto fromEntity(Basket basket) {
        BasketDto basketDTO = new BasketDto();
        basketDTO.id = basket.getId();
        basketDTO.item = basket.getItem();
        return basketDTO;
    }
    // zamiana Dto (np. JSON) na encję
    public static Basket toEntity(BasketDto basketDTO) {
        Basket basket = new Basket();
        basket.setId(basketDTO.id);
        basket.setItem(basketDTO.item);
        return basket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

package pl.kalinowski.java_spring_store.model;


import jakarta.persistence.*;

@Entity
    @Table(name = "basket_item")
    public class BasketItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "basket_id")
        private Basket basket;

        @ManyToOne
        @JoinColumn(name = "item_id")
        private Item item; // istniejący produkt

        @Column(nullable = false)
        private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

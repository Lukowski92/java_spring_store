package pl.kalinowski.java_spring_store.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Float promotionPercentage;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPromotionPercentage(Float promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }

    public Float getPromotionPercentage() {
        return promotionPercentage;
    }


}

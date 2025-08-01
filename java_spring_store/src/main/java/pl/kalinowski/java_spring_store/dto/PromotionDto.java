package pl.kalinowski.java_spring_store.dto;

import pl.kalinowski.java_spring_store.model.Promotion;


public class PromotionDto {

    long id;
    Float promotionPercentage;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Float getPromotionPercentage() {
        return promotionPercentage;
    }

    public void setPromotionPercentage(Float promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }

    // Konwersje
    public Promotion toEntity() {
        Promotion promotion = new Promotion();

        promotion.setId(id);
        promotion.setPromotionPercentage(promotionPercentage);
        return promotion;
    }

    public static PromotionDto fromEntity(Promotion promotion) {
        PromotionDto dto = new PromotionDto();
        dto.setId(promotion.getId());
        dto.setPromotionPercentage(promotion.getPromotionPercentage());
        return dto;
    }
}

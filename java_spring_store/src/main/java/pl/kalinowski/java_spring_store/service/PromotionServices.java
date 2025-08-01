package pl.kalinowski.java_spring_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kalinowski.java_spring_store.dto.PromotionDto;
import pl.kalinowski.java_spring_store.model.Promotion;
import pl.kalinowski.java_spring_store.repository.PromotionRepository;

@Service
public class PromotionServices {

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServices(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public PromotionDto createPromotion(PromotionDto dto) {
        Promotion promotion = dto.toEntity();
        promotion.setActive(true);
        Promotion saved = promotionRepository.save(promotion);
        return PromotionDto.fromEntity(saved);
    }
}


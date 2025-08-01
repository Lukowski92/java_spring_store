package pl.kalinowski.java_spring_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.kalinowski.java_spring_store.dto.PromotionDto;
import pl.kalinowski.java_spring_store.service.PromotionServices;

@Tag(name="promotion", description = "promotion APIs")
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    private final PromotionServices promotionServices;

    public PromotionController(PromotionServices promotionServices) {
        this.promotionServices = promotionServices;
    }

    @Operation(summary = "Set up promotion")
    @PostMapping("/promo")
    public ResponseEntity<PromotionDto> createPromotion(@RequestBody PromotionDto dto) {
        PromotionDto created = promotionServices.createPromotion(dto);
        return ResponseEntity.ok(created);
    }

}

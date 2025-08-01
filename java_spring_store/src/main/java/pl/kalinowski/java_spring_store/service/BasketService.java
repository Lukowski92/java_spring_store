package pl.kalinowski.java_spring_store.service;

import org.springframework.stereotype.Service;
import pl.kalinowski.java_spring_store.dto.BasketDto;
import pl.kalinowski.java_spring_store.dto.BasketItemDto;
import pl.kalinowski.java_spring_store.exception.ResourceNotFoundException;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.model.BasketItem;
import pl.kalinowski.java_spring_store.model.Item;
import pl.kalinowski.java_spring_store.model.Promotion;
import pl.kalinowski.java_spring_store.repository.BasketRepository;
import pl.kalinowski.java_spring_store.repository.ItemRepository;
import pl.kalinowski.java_spring_store.repository.PromotionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BasketService {

    private final BasketRepository basketRepository;

    private final ItemRepository itemRepository;

    private final PromotionRepository promotionRepository;

    public BasketService(BasketRepository basketRepository, ItemRepository itemRepository, PromotionRepository promotionRepository) {
        this.basketRepository = basketRepository;
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
    }


    public BasketDto getBasketById(Long id) {
        return BasketDto.fromEntity(basketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found")));
    }

    public Basket createBasket() {
        Basket basket = new Basket();
        return basketRepository.save(basket);
    }

    public List<BasketDto> getAllBaskets() {
        return basketRepository.findAll().stream().map(BasketDto::fromEntity).toList();
    }


    public void deleteBasketById(Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
        basketRepository.delete(basket);

    }

    public BasketDto addItemToBasket(Long basketId, BasketItemDto dto) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        if (item.getQuantity() < dto.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for item: " + item.getItemName());
        }

        // Zmniejsz ilość w magazynie
        item.setQuantity(item.getQuantity() - dto.getQuantity());
        itemRepository.save(item);

        // Sprawdź, czy item już istnieje w koszyku
        Optional<BasketItem> existing = basket.getBasketItems().stream()
                .filter(bi -> bi.getItem().getId().equals(item.getId()))
                .findFirst();

        if (existing.isPresent()) {
            BasketItem basketItem = existing.get();
            basketItem.setQuantity(basketItem.getQuantity() + dto.getQuantity());
        } else {
            BasketItem basketItem = new BasketItem();
            basketItem.setItem(item);
            basketItem.setBasket(basket);
            basketItem.setQuantity(dto.getQuantity());
            basket.getBasketItems().add(basketItem);
        }

        basketRepository.save(basket);

        return BasketDto.fromEntity(basket);
    }
    public List<BasketItemDto> getItems(Long basketId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

        return basket.getBasketItems().stream()
                .map(BasketItemDto::fromEntity)
                .collect(Collectors.toList());
    }

    //wartosc koszyka z ewentulaną promocją
    public BigDecimal getBasketPriceById(Long basketId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

        // Oblicz podstawową wartość koszyka
        BigDecimal total = basket.getBasketItems().stream()
                .map(bi -> bi.getItem().getItemPrice().multiply(BigDecimal.valueOf(bi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Pobierz pierwszą (lub aktywną) promocję z repozytorium
        Optional<Promotion> promotionOpt = promotionRepository.findFirstByActiveTrue(); // np. ostatnia dodana

        if (promotionOpt.isPresent()) {
            Promotion promotion = promotionOpt.get();
            Float discountPercent = promotion.getPromotionPercentage();

            if (discountPercent != null && discountPercent > 0) {
                BigDecimal discount = total.multiply(BigDecimal.valueOf(discountPercent / 100.0));
                total = total.subtract(discount);
            }
        }

        return total;
    }


}

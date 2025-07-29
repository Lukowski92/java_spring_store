package pl.kalinowski.java_spring_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kalinowski.java_spring_store.dto.BasketDto;
import pl.kalinowski.java_spring_store.exception.ResourceNotFoundException;
import pl.kalinowski.java_spring_store.model.Basket;
import pl.kalinowski.java_spring_store.repository.BasketRepository;

import java.util.List;


@Service
public class BasketService {

    private BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public BasketDto getBasketById(Long id) {
        return BasketDto.fromEntity(basketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Basket not found")));
    }

    public Basket createBasket(BasketDto basketDto) {
        Basket basket = BasketDto.toEntity(basketDto);
        return basketRepository.save(basket);
    }

    public List<BasketDto> getAllBaskets() {
        return basketRepository.findAll().stream().map(BasketDto::fromEntity).toList();
    }

    public Basket updateBasket(Long id, BasketDto basketDetails) {
        Basket basket = basketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
        basket.setItem(basketDetails.getItem());
        return basketRepository.save(basket);
    }
    public void deleteBasketById(Long id) {
       Basket basket= basketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
       basketRepository.delete(basket);

    }

}

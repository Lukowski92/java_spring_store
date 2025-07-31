package pl.kalinowski.java_spring_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kalinowski.java_spring_store.dto.ItemDto;
import pl.kalinowski.java_spring_store.exception.ResourceNotFoundException;
import pl.kalinowski.java_spring_store.model.Item;
import pl.kalinowski.java_spring_store.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {


    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto getItemByID(Long id) {
        return ItemDto.fromEntity(itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found")));
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(ItemDto::fromEntity).collect(Collectors.toList());
    }

    public void deleteItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        itemRepository.delete(item);
    }

    public Item createItem(ItemDto itemDto) {
        Item item = ItemDto.toEntity(itemDto);
        return itemRepository.save(item);

    }

    public Item updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        item.setItemName(itemDto.getName());
        item.setItemPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        return itemRepository.save(item);
    }
}

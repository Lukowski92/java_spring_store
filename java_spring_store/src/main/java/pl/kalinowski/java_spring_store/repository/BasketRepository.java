package pl.kalinowski.java_spring_store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kalinowski.java_spring_store.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}

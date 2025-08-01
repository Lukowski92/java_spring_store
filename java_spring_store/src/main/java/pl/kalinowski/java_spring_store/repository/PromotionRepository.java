package pl.kalinowski.java_spring_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.kalinowski.java_spring_store.model.Promotion;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

   // boolean getPromotionById(Long id);

    Optional<Promotion> findFirstByActiveTrue();
}

package androidApi.repository;

import androidApi.model.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishesRepository extends JpaRepository<Wishes, Integer> {
}

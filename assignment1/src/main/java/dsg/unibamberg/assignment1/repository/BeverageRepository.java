package dsg.unibamberg.assignment1.repository;

import dsg.unibamberg.assignment1.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}

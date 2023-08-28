package dsg.unibamberg.assignment1.repository;

import dsg.unibamberg.assignment1.model.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottleRepository extends JpaRepository<Bottle, Long> {
}

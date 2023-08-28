package dsg.unibamberg.assignment1.repository;

import dsg.unibamberg.assignment1.model.Crate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrateRepository extends JpaRepository<Crate, Long> {
}

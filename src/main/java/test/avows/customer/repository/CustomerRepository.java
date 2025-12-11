package test.avows.customer.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.avows.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<@NonNull Customer, @NonNull Long> {
}

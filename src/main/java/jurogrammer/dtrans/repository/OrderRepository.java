package jurogrammer.dtrans.repository;

import jurogrammer.dtrans.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

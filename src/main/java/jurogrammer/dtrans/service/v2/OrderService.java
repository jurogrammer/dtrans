package jurogrammer.dtrans.service.v2;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Order order) {
        orderRepository.save(order);
        throw new RuntimeException("order 저장시 에러 발생");
    }
}

package jurogrammer.dtrans.service.badcase;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.OrderRepository;
import jurogrammer.dtrans.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BadSaveService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void save() {
        userRepository.save(new User("홍길동"));

        try {
            saveOrder();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrder() {
        orderRepository.save(new Order());
        throw new RuntimeException("order 저장시 에러 발생");
    }
}

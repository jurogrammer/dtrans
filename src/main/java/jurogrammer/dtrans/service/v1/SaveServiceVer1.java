package jurogrammer.dtrans.service.v1;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.OrderRepository;
import jurogrammer.dtrans.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveServiceVer1 {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void save() {

        userRepository.save(new User("홍길동"));

        // 만약 order 저장시 에러가 발생 경우에도, user는 그대로 저장하고 싶다면?
        orderRepository.save(new Order());
        throw new RuntimeException("order 저장시 에러 발생");
    }
}

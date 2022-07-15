package jurogrammer.dtrans.service.v3;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.OrderRepository;
import jurogrammer.dtrans.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveServiceVer3 {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TransactionHelper transactionHelper;

    @Transactional
    public void save() {
        userRepository.save(new User("홍길동"));
        try {
            transactionHelper.requiredNew(() -> {
                orderRepository.save(new Order());
                throw new RuntimeException("order 저장시 에러 발생");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

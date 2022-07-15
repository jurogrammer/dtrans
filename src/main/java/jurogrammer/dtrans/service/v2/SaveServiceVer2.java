package jurogrammer.dtrans.service.v2;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveServiceVer2 {

    private final UserRepository userRepository;
    private final OrderService orderService;

    @Transactional
    public void save() {
        userRepository.save(new User("홍길동"));

        try {
            orderService.save(new Order());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

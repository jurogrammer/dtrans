package jurogrammer.dtrans.service.badcase;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.OrderRepository;
import jurogrammer.dtrans.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BadSaveServiceTest {
    @Autowired
    BadSaveService badSaveService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    // repository interface 의 기본 전략은 save시 트랜잭션 열고 commit
    @Test
    @DisplayName("user 1개 order 1개를 저장하면, 1개, 1개가 조회된다.")
    void test() {
        try {
            badSaveService.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        int userSize = users.size();
        int orderSize = orders.size();

        assertThat(userSize).isEqualTo(1);
        assertThat(orderSize).isEqualTo(1);

        removeAll();
    }

    private void removeAll() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }
}
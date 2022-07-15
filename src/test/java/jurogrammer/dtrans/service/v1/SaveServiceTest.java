package jurogrammer.dtrans.service.v1;

import jurogrammer.dtrans.entity.Order;
import jurogrammer.dtrans.entity.User;
import jurogrammer.dtrans.repository.OrderRepository;
import jurogrammer.dtrans.repository.UserRepository;
import jurogrammer.dtrans.service.v2.SaveServiceVer2;
import jurogrammer.dtrans.service.v3.SaveServiceVer3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SaveServiceTest {

    @Autowired
    SaveServiceVer1 saveServiceVer1;
    @Autowired
    SaveServiceVer2 saveServiceVer2;
    @Autowired
    SaveServiceVer3 saveServiceVer3;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("user, order 모두 롤백되어 저장되지 않는다")
    void save_service_ver1_test() {
        try {
            saveServiceVer1.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        int userSize = users.size();
        int orderSize = orders.size();

        assertThat(userSize).isEqualTo(0);
        assertThat(orderSize).isEqualTo(0);

        removeAll();
    }

    @Test
    @DisplayName("order service를 호출하여 트랜잭션을 분리하면 user는 저장되고, order는 롤백되어 저장되지 않는다.")
    void save_service_ver2_test() {
        saveServiceVer2.save();

        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        int userSize = users.size();
        int orderSize = orders.size();

        assertThat(userSize).isEqualTo(1);
        assertThat(orderSize).isEqualTo(0);

        removeAll();
    }

    @Test
    @DisplayName("transaction helper를 이용하여 트랜잭션을 분리하면 user는 저장되고, order는 롤백되어 저장되지 않는다.")
    void save_service_ver3_test() {
        saveServiceVer3.save();

        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        int userSize = users.size();
        int orderSize = orders.size();

        assertThat(userSize).isEqualTo(1);
        assertThat(orderSize).isEqualTo(0);

        removeAll();
    }

    private void removeAll() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }
}
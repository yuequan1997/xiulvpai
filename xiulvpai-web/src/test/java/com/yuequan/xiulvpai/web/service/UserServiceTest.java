package com.yuequan.xiulvpai.web.service;

import com.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import com.yuequan.xiulvpai.web.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void save() {
        var user = UserFactory.getUser();
        userService.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void findById() {
        var user = UserFactory.getUser();
        userService.save(user);
        assertNotNull(userService.findById(user.getId()));
        assertThrows(ResourceNotFoundException.class, () -> userService.findById("test"));
    }

    @Test
    public void destroy() {
        var user = UserFactory.getUser();
        userService.save(user);
        userService.destroy(user);
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(user.getId()));
    }

    @Test
    public void getUsers() {
        var users = UserFactory.getUsers(10);
        users.forEach(user -> userService.save(user));
        assertEquals(users.size(), userService.getUsers(PageRequest.of(0, 10)).getContent().size());
    }
}
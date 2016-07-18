package com.scj.service.test;

import com.scj.user.entity.User;
import com.scj.user.repository.UserRepository;
import com.scj.user.service.UserService;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class UserServiceTest extends TestBase{

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserService userService;

    @Test
    public void testJpa()
    {
        List<User> users =userRepository.findByUsername("scj");
        users.stream().forEach(
                u->
                {
                    System.out.println(u.getUsername());
                    System.out.println(u.getPassword());
                }
             );

    }

    @Test
    public void testJpaInsert()
    {
        User user =new User();
        user.setUsername("lyy");
        user.setPassword("123456");
        userRepository.save(user);
    }


    @Test
    public void testRegister()
    {
        User user =new User();
        user.setUsername("lyy3");
        user.setPassword("123456");
        user.setNickname("123");

        userService.register(user);
    }

    @Test
    public void testLogin()
    {
        userService.login("lyy","1234f56");
    }

    @Test
    public void testQuery()
    {
        List<User> users =userRepository.findByNickname("123");
        users.stream().forEach(
                u->
                {
                    System.out.println(u.getUsername());
                    System.out.println(u.getPassword());
                }
        );
    }

    @Test
    public void testPaging()
    {
        Page<User> users =userRepository.findAll(new PageRequest(0,2));
        System.out.println(users.getTotalPages());
        System.out.println(users.getTotalElements());

        List<User> userList =users.getContent();
        userList.stream().forEach(
                u->
                {
                    System.out.println(u.getUsername());
                    System.out.println(u.getPassword());
                }
        );

        System.out.println("the second method");

        for (User user : users) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        }
    }

    @Test
    public  void testMap()
    {
        User user =userRepository.findOne(9);
        if(user.getTalks().size()>0)
        {
            user.getTalks().stream().forEach(t->System.out.println(t));
        }
    }
}

package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.modal.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    //注入Redis模板
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }


    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findUserNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUserCriteria(Integer page, Integer size, User user) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<User> usersPage = userRepository.findAll(new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=user.getUsername()&&!"".equals(user.getUsername())){
                    list.add(criteriaBuilder.equal(root.get("username").as(String.class), user.getUsername()));
                }
               /* if(null!=user.getPassword()&&!"".equals(user.getPassword())){
                    list.add(criteriaBuilder.equal(root.get("password").as(String.class), user.getPassword()));
                }
                if(null!=user.getAge()&&!"".equals(user.getAge())){
                    list.add(criteriaBuilder.equal(root.get("age").as(Integer.class), user.getAge()));
                }*/
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return usersPage;
    }
}

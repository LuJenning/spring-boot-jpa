package com.example.demo.service;

import com.example.demo.modal.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> userList();

    User findUserById(long id);

    void edit(User user);

    void delete(long id);

    /**
     * 不带查询条件的分页
     * @param page
     * @param size
     * @return
     */
    Page<User> findUserNoCriteria(Integer page,Integer size);

    /**
     * 带查询条件的分页
     * @param page
     * @param size
     * @param user
     * @return
     */
    Page<User> findUserCriteria(Integer page,Integer size,User user);
}

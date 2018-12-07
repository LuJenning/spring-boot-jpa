package com.example.demo.dao;

import com.example.demo.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,Long>,
        JpaSpecificationExecutor<User> {

    User findById(long id);

    void deleteById(Long id);
}

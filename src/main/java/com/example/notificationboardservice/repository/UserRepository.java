package com.example.notificationboardservice.repository;

import com.example.notificationboardservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
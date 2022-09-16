package com.example.notificationboardservice.repository;

import com.example.notificationboardservice.entity.Role;
import com.example.notificationboardservice.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
}

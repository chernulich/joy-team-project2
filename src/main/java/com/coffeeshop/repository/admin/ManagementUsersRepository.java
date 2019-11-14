package com.coffeeshop.repository.admin;

import com.coffeeshop.model.entity.admin.ManagementUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementUsersRepository extends JpaRepository<ManagementUsers, Long> {
}

package com.coffeeshop.controller.admin;

import com.coffeeshop.model.entity.admin.ManagementUsers;
import com.coffeeshop.model.web.admin.UserRequest;
import com.coffeeshop.repository.admin.ManagementUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/users")
@Profile("dev")
public class UsersCreatingWithProfileDevController {

    private final ManagementUsersRepository managementUsersRepository;

    @Autowired
    public UsersCreatingWithProfileDevController(ManagementUsersRepository managementUsersRepository) {
        this.managementUsersRepository = managementUsersRepository;
    }

    @PostMapping("/add")
    @Transactional
    public void addUser(@RequestBody @Valid UserRequest userRequest) {
        ManagementUsers user = ManagementUsers.builder()
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .avatarImage(userRequest.getAvatarImage())
                .isTestUser(userRequest.getIsTestUser()).build();
        managementUsersRepository.save(user);
    }
}

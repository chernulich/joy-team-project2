package com.coffeeshop.model.entity.admin;

import com.coffeeshop.model.entity.BaseDate;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DynamicInsert
@Entity
@Table(name = "MANAGEMENT_USERS")
public class ManagementUsers extends BaseDate {

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "AVATAR_IMAGE")
    private String avatarImage;

    @Column(name = "IS_TEST_USER", nullable = false)
    private boolean isTestUser;

    @Builder
    public ManagementUsers(Long id, LocalDateTime createdOn, LocalDateTime updatedOn,
                           String fullName, String email, String avatarImage, boolean isTestUser) {
        super(id, createdOn, updatedOn);
        this.fullName = fullName;
        this.email = email;
        this.avatarImage = avatarImage;
        this.isTestUser = isTestUser;
    }
}

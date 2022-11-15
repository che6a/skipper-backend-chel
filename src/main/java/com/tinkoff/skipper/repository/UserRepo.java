package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.DTO.StatsDTO;
import com.tinkoff.skipper.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    //заменить данный метод sql-запросом
    Optional<StatsDTO> findMenteeInfoById(Long id);


}

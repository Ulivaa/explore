package ru.practicum.explore.repository.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.user.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User WHERE id in :ids or :ids is null")
    Collection<User> findAllById(List<Long> ids, PageRequest pageRequest);
}

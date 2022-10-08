package ru.practicum.explore.repository.compilation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.compilation.Compilation;

import java.util.Collection;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    Collection<Compilation> findAllByPinned(boolean pinned, PageRequest pageRequest);
}

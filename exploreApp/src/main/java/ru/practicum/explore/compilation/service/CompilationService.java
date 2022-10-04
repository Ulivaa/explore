package ru.practicum.explore.compilation.service;

import ru.practicum.explore.compilation.model.Compilation;

import java.util.Collection;

public interface CompilationService {
    Compilation addCompilation(Compilation compilation);

    void deleteCompilation(long compId);

    void deleteEventFromCompilation(long eventId, long compId);

    void addEventToCompilation(long eventId, long compId);

    void isPinCompilation(long compId, boolean pinned);

//    void pinCompilation(long compId);

    Collection<Compilation> getCompilations(boolean pinned, int from, int size);

    Compilation getCompilationById(long compId);
}

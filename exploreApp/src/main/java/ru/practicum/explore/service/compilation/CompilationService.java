package ru.practicum.explore.service.compilation;

import ru.practicum.explore.model.compilation.Compilation;

import java.util.Collection;

public interface CompilationService {
    /**
     * добавить подборку
     */
    Compilation addCompilation(Compilation compilation);

    /**
     * удалить подборку
     */
    void deleteCompilation(long compId);

    /**
     * удалить событие из подборки
     */
    void deleteEventFromCompilation(long eventId, long compId);

    /**
     * добавить событие в подборку
     */
    void addEventToCompilation(long eventId, long compId);

    /**
     * закрепить и открепить подборку
     */
    void isPinCompilation(long compId, boolean pinned);

    /**
     * достать подборку постранично с возможностью выбора закрепленности
     */
    Collection<Compilation> getCompilations(boolean pinned, int from, int size);

    /**
     * достать подборку по id
     */
    Compilation getCompilationById(long compId);
}

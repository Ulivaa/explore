package ru.practicum.explore.compilation.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.compilation.repository.CompilationRepository;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.exception.NotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventService eventService;

    public CompilationServiceImpl(CompilationRepository compilationRepository, EventService eventService) {
        this.compilationRepository = compilationRepository;
        this.eventService = eventService;
    }

    @Override
    public Compilation addCompilation(Compilation compilation) {
        compilation.setEvents(compilation.getEvents().stream().map(event -> eventService.getEventById(event.getId())).collect(Collectors.toSet()));
        return compilationRepository.save(compilation);
    }

    @Override
    public void deleteCompilation(long compId) {
        compilationRepository.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompilation(long eventId, long compId) {
        Compilation compilation = getCompilationById(compId);
        compilation.getEvents().removeIf(event -> event.getId().equals(eventId));
        compilationRepository.save(compilation);
    }

    @Override
    public void addEventToCompilation(long eventId, long compId) {
        Event event = eventService.getEventById(eventId);
        Compilation compilation = getCompilationById(compId);
        Set<Event> eventSet = compilation.getEvents();
        eventSet.add(event);
        compilation.setEvents(eventSet);
        compilationRepository.save(compilation);
    }

    @Override
    public void isPinCompilation(long compId, boolean pinned) {
        Compilation compilation = getCompilationById(compId);
        compilation.setPinned(pinned);
        compilationRepository.save(compilation);
    }

    @Override
    public Collection<Compilation> getCompilations(boolean pinned, int from, int size) {
        return compilationRepository.findAllByPinned(pinned, PageRequest.of(from, size));
    }

    @Override
    public Compilation getCompilationById(long compId) {
        return compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Подборка не найдена"));
    }
}

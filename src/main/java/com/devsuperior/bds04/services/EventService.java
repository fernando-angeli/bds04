package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        copyDtoToEntity(dto, event);
        repository.save(event);
        return new EventDTO(event);
    }

    private void copyDtoToEntity(EventDTO dto, Event event) {
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());
        event.setDate(dto.getDate());
        event.setCity(new City(dto.getCityId(), null));
    }
}

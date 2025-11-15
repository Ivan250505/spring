package com.futbol.app.service;

import com.futbol.app.entity.Competicion;
import com.futbol.app.repository.CompeticionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompeticionService {

    private final CompeticionRepository competicionRepository;

    @Transactional(readOnly = true)
    public List<Competicion> findAll() {
        return competicionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Competicion> findById(Long id) {
        return competicionRepository.findById(id);
    }

    @Transactional
    public Competicion save(Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    @Transactional
    public void deleteById(Long id) {
        competicionRepository.deleteById(id);
    }
}

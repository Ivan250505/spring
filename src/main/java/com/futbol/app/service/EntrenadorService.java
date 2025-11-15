package com.futbol.app.service;

import com.futbol.app.entity.Entrenador;
import com.futbol.app.repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    @Transactional(readOnly = true)
    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Entrenador> findById(Long id) {
        return entrenadorRepository.findById(id);
    }

    @Transactional
    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @Transactional
    public void deleteById(Long id) {
        entrenadorRepository.deleteById(id);
    }
}

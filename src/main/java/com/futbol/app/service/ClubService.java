package com.futbol.app.service;

import com.futbol.app.entity.Club;
import com.futbol.app.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }

    @Transactional
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    @Transactional
    public void deleteById(Long id) {
        clubRepository.deleteById(id);
    }
}

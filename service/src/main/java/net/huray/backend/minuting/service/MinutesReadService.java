package net.huray.backend.minuting.service;

import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.dto.MinutesDto;
import net.huray.backend.minuting.entity.MinutesEntity;
import net.huray.backend.minuting.repository.MinutesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MinutesReadService {

    private final MinutesRepository minutesRepository;
    private final ModelMapper modelMapper;

    public List<MinutesDto.MinutesSimple> list() {
        return minutesRepository.findAll().stream()
                .map(it -> modelMapper.map(it, MinutesDto.MinutesSimple.class))
                .toList();
    }

    public MinutesDto.MinutesDetail getDetailById(Long id) {
        MinutesEntity minutes = minutesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(minutes, MinutesDto.MinutesDetail.class);
    }
}

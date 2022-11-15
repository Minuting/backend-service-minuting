package net.huray.backend.minuting.service;

import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.dto.MinutesDto;
import net.huray.backend.minuting.entity.MinutesEntity;
import net.huray.backend.minuting.repository.MinutesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MinutesWriteService {

    private final MinutesRepository minutesRepository;
    private final ModelMapper modelMapper;

    public MinutesDto.MinutesSimple createMinutes(MinutesDto.CreateReq req) {
        MinutesEntity minutesEntity = minutesRepository.save(new MinutesEntity(req.getTitle(), req.getContents()));

        return modelMapper.map(minutesEntity, MinutesDto.MinutesSimple.class);
    }
}

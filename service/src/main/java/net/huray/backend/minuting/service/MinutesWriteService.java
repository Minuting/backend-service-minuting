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

@Service
@RequiredArgsConstructor
@Transactional
public class MinutesWriteService {

    private final MinutesRepository minutesRepository;
    private final ModelMapper modelMapper;

    public MinutesDto.MinutesSimple create(MinutesDto.CreateReq req) {
        MinutesEntity minutesEntity = minutesRepository.save(new MinutesEntity(req.getTitle(), req.getContents()));

        return modelMapper.map(minutesEntity, MinutesDto.MinutesSimple.class);
    }

    public void update(Long id, MinutesDto.UpdateReq req) {
        MinutesEntity minutesEntity = minutesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        minutesEntity.updateMinutes(req.getTitle(), req.getContents());
    }

    public void hardDelete(Long id) {
        minutesRepository.deleteById(id);
    }
}

package net.huray.backend.minuting.service;

import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.domain.SampleCreateReq;
import net.huray.backend.minuting.domain.SampleListReq;
import net.huray.backend.minuting.domain.SampleSimple;
import net.huray.backend.minuting.domain.SampleUpdateReq;
import net.huray.backend.minuting.entity.SampleEntity;
import net.huray.backend.minuting.repository.SampleDslRepository;
import net.huray.backend.minuting.repository.SampleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;
    private final SampleDslRepository sampleDslRepository;
    private final ModelMapper modelMapper;

    public SampleSimple add(SampleCreateReq req) {
        SampleEntity save = sampleRepository.save(new SampleEntity(req.getNo(), req.getData()));

        return modelMapper.map(save, SampleSimple.class);
    }

    public List<SampleSimple> listSimple(SampleListReq req) {
        return sampleDslRepository.findAllByData(req.getData())
                .stream().map(sampleEntity -> modelMapper.map(sampleEntity, SampleSimple.class))
                .collect(Collectors.toList());
    }

    public SampleSimple getOne(Long id) {
        SampleEntity sampleEntity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(sampleEntity, SampleSimple.class);
    }

    @Transactional
    public SampleSimple update(Long id, SampleUpdateReq sampleUpdateReq) {
        SampleEntity sampleEntity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        sampleEntity.updateEntity(sampleUpdateReq.getNo(), sampleUpdateReq.getData());

        return modelMapper.map(sampleEntity, SampleSimple.class);
    }

    public Long hardDelete(Long id) {
        SampleEntity sampleEntity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        sampleRepository.delete(sampleEntity);

        return sampleEntity.getId();
    }

}

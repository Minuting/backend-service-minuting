package net.huray.backend.minuting.service;

import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.repository.MinutesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MinutesReadService {

    private final MinutesRepository minutesRepository;

}

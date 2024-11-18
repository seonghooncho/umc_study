package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RegionHandler;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.request.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    @Override
    public Store addStore(StoreRequestDTO.CreateDTO dto) {
        Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(()->
                new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        return storeRepository.save(dto.toStore(region));
    }
}

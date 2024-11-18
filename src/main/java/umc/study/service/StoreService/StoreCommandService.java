package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.web.dto.request.StoreRequestDTO;

public interface StoreCommandService {
    public Store addStore(StoreRequestDTO.CreateDTO dto);
}

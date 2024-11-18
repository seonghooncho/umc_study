package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.request.StoreRequestDTO;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<?> addStore(@RequestBody @Valid StoreRequestDTO.CreateDTO dto) {
        Store store=  storeCommandService.addStore(dto);
        return ApiResponse.onSuccess();
    }
}

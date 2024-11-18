package umc.study.web.dto.response;


import lombok.Builder;
import lombok.Getter;
import umc.study.domain.Region;

@Getter
@Builder
public class StoreResponseDTO {
    private Long id;                // 가게 ID
    private String name;            // 가게 이름
    private String address;         // 가게 주소
    private Float score;            // 가게 평점
    private String regionName;      // 지역 이름

    public static StoreResponseDTO fromEntity(umc.study.domain.Store store) {
        return StoreResponseDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .score(store.getScore() != null ? store.getScore() : null)
                .regionName(store.getRegion() != null ? store.getRegion().getName() : null)
                .build();
    }
}


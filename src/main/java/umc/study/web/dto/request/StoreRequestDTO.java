package umc.study.web.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import umc.study.domain.Region;
import umc.study.domain.Store;


public class StoreRequestDTO {
    @Getter
    public static class  CreateDTO{
        @NotBlank
        private String name;
        @NotBlank
        private String address;
        @NotNull
        private Long regionId; // 지역 ID

        public Store toStore(Region region) {
            return Store.builder()
                    .name(this.name)
                    .address(this.address)
                    .region(region)
                    .build();
        }
    }

    public static class UpdateStoreRequestDTO{
        private String name;
        private String address;
        private Float score;
        private Long regionId; // 지역 ID

        public Store toStore(Region region) {
            return Store.builder()
                    .name(this.name)
                    .address(this.address)
                    .score(this.score)
                    .region(region)
                    .build();
        }
    }





}
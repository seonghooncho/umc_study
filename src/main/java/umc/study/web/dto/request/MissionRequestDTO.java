package umc.study.web.dto.request;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import umc.study.domain.Mission;
import umc.study.domain.Store;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {
    @Getter
    public static class CreateDTO{
        @NotBlank
        private String missionSpec;

        @NotNull
        @Min(1)
        private Integer reward;

        @NotNull
        @Future// 오늘이거나 과거라면 유효성 실패
        private LocalDate deadline;

        @NotNull
        private Long storeId; // 가게 ID

        public Mission toMission(Store store) {
            return Mission.builder()
                    .missionSpec(this.missionSpec)
                    .reward(this.reward)
                    .deadline(this.deadline)
                    .store(store)
                    .build();
        }
    }
}

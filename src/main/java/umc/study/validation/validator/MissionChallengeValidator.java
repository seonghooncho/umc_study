package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.memberService.MemberQueryService;
import umc.study.validation.annotation.NotAlreadyChallenged;
import umc.study.web.dto.request.MemberMissionRequestDTO;
@RequiredArgsConstructor
public class MissionChallengeValidator implements ConstraintValidator<NotAlreadyChallenged, MemberMissionRequestDTO.MemberMissionIdDTO> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(NotAlreadyChallenged constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.MemberMissionIdDTO dto, ConstraintValidatorContext context) {
        boolean isValid = memberQueryService.isMissionAlreadyChallenged(dto.getMemberId(), dto.getMissionId());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
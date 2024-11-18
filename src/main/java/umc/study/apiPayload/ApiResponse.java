package umc.study.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import umc.study.apiPayload.code.BaseCode;
import umc.study.apiPayload.code.status.SuccessStatus;

@Builder
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonProperty("result")
    private T result;


    // 성공한 경우 응답 생성

    public static <T> ApiResponse<T> onSuccess(T result){
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(SuccessStatus._OK.getCode())
                .message(SuccessStatus._OK.getMessage())
                .result(result)
                .build();
    }
    public static <T> ApiResponse<T> onSuccess() {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .code(SuccessStatus._OK.getCode())
                .message(SuccessStatus._OK.getMessage())
                .build();
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
            return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
package umc.study.apiPayload.exception.handler;

import org.hibernate.tool.schema.spi.ExceptionHandler;
import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

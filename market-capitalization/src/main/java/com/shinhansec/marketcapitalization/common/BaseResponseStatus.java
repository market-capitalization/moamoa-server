package com.shinhansec.marketcapitalization.common;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request Error
     */

    /**
     * 3000: Response Error
     */
    INVALID_MEMBER_ID(false, 3000, "유저 아이디가 유효하지 않습니다."),
    FULL_MEETING(false, 3001, "이미 모임이 가득 찼습니다"),

    /**
     * 4000: DB Error
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}

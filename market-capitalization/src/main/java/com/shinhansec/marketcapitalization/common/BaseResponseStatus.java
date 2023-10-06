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
    NOT_YOUR_PROFILE(false, 2000, "자신의 프로필이 아니므로 수정이 불가능합니다."),

    /**
     * 3000: Response Error
     */
    INVALID_MEMBER_ID(false, 3000, "유저 아이디가 유효하지 않습니다."),
    DUPLICATE_NICK(false, 3001, "유저 닉네임이 중복됩니다."),

    INVALID_MEETING_ID(false, 3200, "모임 아이디가 유효하지 않습니다."),
    FULL_MEETING(false, 3201, "이미 모임이 가득 찼습니다"),
    ALREADY_PARTICIPATE(false, 3202, "이미 해당 모임에 가입 중인 유저입니다."),
    NOT_MEETING_MEMBER(false, 3203, "해당 모임의 접근 권한이 없는 유저입니다."),

    INVALID_STOCK_ID(false, 3400, "주식 아이디가 유효하지 않습니다."),

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

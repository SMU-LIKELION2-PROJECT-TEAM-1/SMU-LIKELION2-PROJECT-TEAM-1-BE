package com.kwakmunsu.likelionprojectteam1.global.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    // <=============== MEMBER ===============>
    NOT_FOUND_MEMBER("ERROR - 회원을 찾을 수 없습니다."),
    BAD_REQUEST_MEMBER("ERROR - 잘못된 회원 요청"),
    BAD_REQUEST_PASSWORD("ERROR - 잘못된 비밀번호 요청"),
    DUPLICATE_EMAIL("ERROR - 회원가입 ID 중복: "),
    DUPLICATE_NICKNAME("ERROR - 회원가입 닉네임 중복: "),

    // <=============== RECIPE ===============>
    NOT_FOUND_RECIPE("ERROR - 해당 레시피를 찾을 수 없습니다."),
    BAD_REQUEST_RECIPE("ERROR - 잘못된 레시피 요청"),
    MODIFY_UNAUTHORIZED_RECIPE("ERROR - 수정 권한이 없습니다."),
    DELETE_UNAUTHORIZED_RECIPE("ERROR - 삭제 권한이 없습니다."),

    // <=============== COMMENT ===============>
    NOT_FOUND_COMMENT("ERROR - 해당 댓글을 찾을 수 없습니다."),
    BAD_REQUEST_COMMENT("ERROR - 잘못된 댓글 요청"),

    // <=============== VOTE ===============>
    DUPLICATE_VOTE("ERROR - 이미 투표 하였습니다."),

    // <=============== JWT ===============>
    TOKEN_EXPIRED("ERROR - JWT 토큰 만료"),
    INVALID_TOKEN("ERROR - 유효하지 않은 토큰입니다."),
    NOT_FOUND_TOKEN("ERROR - 토큰을 찾을 수 없습니다."),
    BAD_REQUEST_TOKEN("ERROR - 잘못된 토큰 요청"),
    WRONG_AUTH_HEADER("ERROR - Bearer 로 시작하는 토큰이 없습니다."),
    TOKEN_VALIDATION_TRY_FAILED("ERROR - 토큰 인증 실패"),
    TOKEN_HASH_NOT_SUPPORTED("ERROR - 지원하지 않는 형식의 토큰"),

    //  <=============== AWS ===============>
    AWS_S3_ERROR("ERROR - AWS S3 내부 에러"),
    NOT_FOUND_FILE("ERROR - 파일이 존재하지 않습니다."),
    FAILED_TO_UPLOAD_FILE("ERROR - 파일 업로드에 실패하였습니다."),
    INVALID_FILE_EXTENSION("ERROR - 지원하지 않는 파일 확장자입니다."),

    // <=============== ETC ===============>
    INTERNAL_SERVER_ERROR("ERROR - 서버 내부 에러"),
    UNAUTHORIZED_ERROR("ERROR - 인증되지 않은 사용자입니다."),
    FORBIDDEN_ERROR("ERROR - 접근 권한이 없습니다."),
    BAD_REQUEST_ARGUMENT("ERROR - 유효하지 않은 인자입니다."),
    MODIFY_UNAUTHORIZED("ERROR - 수정 권한이 없습니다."),
    DELETE_UNAUTHORIZED("ERROR - 삭제 권한이 없습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
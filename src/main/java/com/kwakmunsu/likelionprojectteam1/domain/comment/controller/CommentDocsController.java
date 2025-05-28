package com.kwakmunsu.likelionprojectteam1.domain.comment.controller;

import com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.response.CommentCreateResponse;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Comment Controller", description = "Comment API")
public abstract class CommentDocsController {

    @Operation(
            summary = "댓글 생성 - JWT O",
            description = " 특정 레시피 글(recipeId)에 댓글을 작성합니다. 댓글 생성 후 댓글 id를 반환한다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "댓글 생성 성공, 성공 후 댓글 id를 반환한다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파리미터 요청 오류 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 레시피 글입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<CommentCreateResponse> create(
            Long memberId,
            @Parameter(description = "댓글을 작성할 레시피 글의 ID(recipeId)", example = "1", required = true)
            Long recipeId,
            CommentCreateRequest request
    );

    @Operation(
            summary = "댓글 삭제 - JWT O",
            description = " 특정 레시피 글(recipeId)에 댓글을 삭제합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "댓글 삭제 성공"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파리미터 요청 오류 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "댓글을 찾을 수 없습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<Void> delete(
            @Parameter(description = "삭제할 댓글의 ID", example = "1", required = true)
            Long commentId
    );

    @Operation(
            summary = "댓글 수정 - JWT O",
            description = "댓글을 수정합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "댓글 수정 성공"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "댓글을 찾을 수 없습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<Void> update(
            @Parameter(description = "수정할 댓글의 ID", example = "1", required = true)
            Long commentId
    );
}
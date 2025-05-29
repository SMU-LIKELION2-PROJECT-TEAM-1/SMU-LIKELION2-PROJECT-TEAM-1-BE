package com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Schema(description = "마이페이지 무한스크롤 레시피 글 목록 DTO(좋아요, 찜, 댓글)")
@Builder
public record RecipeInfinityPreviewResponse(

        @Schema(description = "레시피 글 목록")
        List<RecipePreviewResponse> responses,

        @Schema(description = "다음 커서 id, 다음 조회할 글이 없다면 null이 들어온다.", example = "1 or null")
        Long nextCursorId,

        @Schema(description = "더이상 불러올 데이터가 없다면 false, 있다면 true", example = "true")
        boolean hasNext

) {

}
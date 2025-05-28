package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeUpdateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.WeeklyTop3RecipesResponse;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Recipe Controller", description = "Recipe API")
public abstract class RecipeDocsController {

    @Operation(
            summary = "레시피 등록 - JWT O",
            description = """
                    레시피 게시글을 생성합니다.<br>
                    - 게시판 종류, 제목, 소개, 조리 시간, 태그(시간/상황, 목적, 음식 종류), 난이도, 재료, 조리법을 입력할 수 있습니다.
                    - 이미지는 여러 장 업로드할 수 있으며, 생략 가능합니다.
                    - 게시글 생성 시 Header 안에 Location에 담아서 uri를 반환합니다.
                    - 파일은 multipart/form-data 형식입니다. 현재 오류로 인해 swagger 에서 표시 안됨
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "레시피 등록 성공, Header 안에 Location에 uri를 담아서 반환합니다."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 입력값 또는 필수값 누락",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<Void> create(
            @AuthMember Long memberId,
            RecipeCreateRequest request,
            @Parameter(
                    description = "업로드할 이미지 파일 리스트 (여러 장 가능, 생략 가능)",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            List<MultipartFile> images
    );

    @Operation(
            summary = "레시피 글 조회 요청",
            description = "레시피 글을 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "레시피 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeDetailResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파라미터 요청 오류, 레시피 글 존재 X.. 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<RecipeDetailResponse> getRecipe(Long recipeId);

    @Operation(
            summary = "레시피 목록 조회",
            description = """
                    레시피 목록을 조회합니다.<br>
                    - 게시판(DAILY, CHALLENGE) 선택이 가능합니다.
                        - 챌린지 레시피 or 데일리 레시피 클릭 시 /recipes?boardType=DAILY 로 요청
                    - 시간/상황, 조리 시간, 목적, 음식 유형 등 다양한 태그별로 필터링이 가능합니다.
                    - 정렬 기준과 페이징 처리도 지원합니다.
                    - 메인페이지 미리보기 9개는 page=1, size= 9로 요청하시면 됩니다.
                    <ul>
                        <li><b>정렬 기준(sortBy):</b> createAt(최신순, 기본값), oldest(오래된순), likes(좋아요순), favorites(찜순)</li>
                        <li><b>태그별 필터:</b> 각 파라미터는 선택적으로 입력할 수 있습니다.</li>
                    </ul>
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "레시피 목록 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipePaginationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 입력값 또는 필수값 누락",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<RecipePaginationResponse> getRecipes(
            @Parameter(
                    description = "게시글 유형 선택(DAILY, CHALLENGE)",
                    example = "DAILY"
            )
            String boardType,

            @Parameter(
                    description = "시간/상황 (아침, 점심, 저녁, 야식, 간식 중 하나 선택)",
                    example = "아침"
            )
            String timeSituation,

            @Parameter(
                    description = "조리 시간 (5분, 10분, 15~20분, 30분, 1시간 중 하나 선택)",
                    example = "10분"
            )
            String cookingTime,

            @Parameter(
                    description = "목적 (다이어트 식단, 벌크업 식단, 건강식, 해장용, 혼밥용 중 하나 선택)",
                    example = "다이어트 식단"
            )
            String purpose,

            @Parameter(
                    description = "음식 유형 (한식, 양식, 일식, 중식 중 하나 선택)",
                    example = "한식"
            )
            String foodType,

            @Parameter(
                    description = """
                            정렬 기준 (기본값: createAt)<br>
                            - createAt: 최신순<br>
                            - oldest: 오래된순<br>
                            - likes: 좋아요 많은 순<br>
                            - favorites: 찜 많은 순
                            """,
                    example = "likes"
            )
            String sortBy,

            @Parameter(
                    description = "재료 검색",
                    example = "달걀"
            )
            String ingredient,

            @Parameter(
                    description = "페이지 번호 (1부터 시작)",
                    example = "1"
            )
            int page,

            @Parameter(
                    description = "한 페이지당 레시피 개수",
                    example = "10"
            )
            int size
    );

    @Operation(
            summary = "레시피 검색",
            description = """
                    레시피를 검색 합니다. 레시피 제목에 해당 키워드가 포함되어 있다면 조회 목록에 포함됩니다.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipePaginationResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파라미터 오류 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<RecipePaginationResponse> search(
            @Parameter(description = "검색 키워드를 입력한다. 제목을 기반으로 검색한다.", example = "자취 음식") String query,
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1") int page,
            @Parameter(description = "한 페이지당 레시피 개수", example = "10") int size
    );

    @Operation(
            summary = "이 주의 레시피 Top 3를 조회합니다.",
            description = """
                    최근 일주일 간 좋아요를 가장 많이 받은 레시피 1~3위를 조회합니다.<br>
                    매주 월요일 00:00에 순위가 초기화됩니다.<br>
                    <ul>
                        <li>집계 기간: 최근 월요일 00:00 ~ 이번 주 일요일 23:59</li>
                        <li>동점인 경우, 더 먼저 등록된 레시피가 우선</li>
                    </ul>
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "이 주의 레시피 Top 3 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WeeklyTop3RecipesResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<WeeklyTop3RecipesResponse> weekly();

    @Operation(
            summary = "레시피 글 수정 요청",
            description = """
                    작성자만이 레시피를 수정 할 수 있습니다. 수정된 값만 보내는 것이 아니라 전체를 다 보낸다.<br>
                    - 파일은 multipart/form-data 형식입니다. 현재 오류로 인해 swagger 에서 표시 안됨
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "레시피 수정 성공"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파라미터 요청 오류, 레시피 글 존재 X.. 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<Void> update(
            Long memberId,
            Long recipeId,
            RecipeUpdateRequest request,
            List<MultipartFile> images
            );

    @Operation(
            summary = "레시피 글 삭제 요청",
            description = "작성자만이 레시피를 삭제 할 수 있습니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "레시피 삭제 성공"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파라미터 요청 오류, 레시피 글 존재 X.. 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<Void> delete(Long recipeId);

    @Operation(
            summary = "챌린지 레시피 재료 투표 요청",
            description = "다음 주차 챌린지 레시피 재료 투표를 합니다. 중복 투표는 불가하며 기간은 일주일 입니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "재료 투표 성공"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(파라미터 요청 오류, 레시피 글 존재 X.. 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "중복 투표 방지",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )

    })
    public abstract ResponseEntity<Void> vote(Long ingredientId);

}
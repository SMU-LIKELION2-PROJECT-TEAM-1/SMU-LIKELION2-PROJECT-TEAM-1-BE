package com.kwakmunsu.likelionprojectteam1.global;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SecurityOverrideCustomizer implements OpenApiCustomizer {

    public static final String UNSECURED = "security.open";

    private static final List<Function<PathItem, Operation>> OPERATION_GETTERS = Arrays.asList(
            PathItem::getGet, PathItem::getPost, PathItem::getDelete, PathItem::getHead,
            PathItem::getOptions, PathItem::getPatch, PathItem::getPut);

    @Override
    public void customise(OpenAPI openApi) {
        openApi.getPaths().forEach((path, item) -> getOperations(item).forEach(operation -> {
            List<String> tags = operation.getTags();
            if (tags != null && tags.contains(UNSECURED)) {
                operation.setSecurity(Collections.emptyList()); // security 제거
                operation.setTags(filterTags(tags)); // 태그 정리(문서에 안 보이게)
            }
        }));
    }

    private static Stream<Operation> getOperations(PathItem pathItem) {
        return OPERATION_GETTERS.stream()
                .map(getter -> getter.apply(pathItem))
                .filter(Objects::nonNull);
    }

    private static List<String> filterTags(List<String> tags) {
        return tags.stream()
                .filter(t -> !t.equals(UNSECURED))
                .collect(Collectors.toList());
    }

}
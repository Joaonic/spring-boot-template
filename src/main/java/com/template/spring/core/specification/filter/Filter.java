package com.template.spring.core.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements IFilter {
    @Builder.Default
    private Boolean simplified = true;
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer pageSize = 10;
    @Builder.Default
    private String sort = "id";
    private List<String> sortList;
    @Builder.Default
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    @Builder.Default
    private String simplifiedField = "";
}

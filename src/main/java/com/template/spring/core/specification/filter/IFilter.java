package com.template.spring.core.specification.filter;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface IFilter {
    Boolean getSimplified();

    void setSimplified(Boolean value);

    Integer getPage();

    void setPage(Integer value);

    Integer getPageSize();

    void setPageSize(Integer value);

    String getSort();

    void setSort(String value);

    List<String> getSortList();

    void setSortList(List<String> values);

    Sort.Direction getSortDirection();

    void setSortDirection(Sort.Direction value);
}

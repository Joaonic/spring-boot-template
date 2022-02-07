package com.template.spring.core.specification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
public class Pagination {
    private Integer pageNumber;
    private int pageSize;
    private String sort;
    private List<String> sortList;
    private Sort.Direction sortDirection = Sort.Direction.ASC;

    public Pagination(Integer pageNumber, Integer pageSize) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15 : pageSize;

    }

    public Pagination(Integer pageNumber, Integer pageSize, String sort, Sort.Direction sortDirection) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15  : pageSize;
        this.sort = sort;
        this.sortDirection = sortDirection;

    }

    public Pagination(Integer pageNumber, Integer pageSize, List<String> sortList, Sort.Direction sortDirection) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15  : pageSize;
        this.sortList = sortList;
        this.sortDirection = sortDirection;

    }

    public Pageable build() {

        if(pageNumber == null) {

            return  PageRequest.of(0 , pageSize, Sort.unsorted());

        }

        if(sortList != null && !sortList.isEmpty()) {

            return PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortList.toArray(new String[0])));

        } else if(sort != null && !sort.isEmpty()) {

            return PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sort));

        } else {

            return PageRequest.of(pageNumber, pageSize);

        }

    }
}

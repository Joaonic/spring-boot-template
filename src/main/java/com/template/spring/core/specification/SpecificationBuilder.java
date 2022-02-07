package com.template.spring.core.specification;

import com.template.spring.core.specification.filter.IFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificationBuilder<T, Filter extends IFilter> {
    protected Root<T> root;
    protected CriteriaQuery<?> query;
    protected CriteriaBuilder cb;

    protected Pagination pagination;

    List<Predicate> predicates;
    List<Predicate> simplifiedPredicates;

    public Specification<T> build(Filter filter) {

        return (root, query, cb) -> {

            this.root = root;
            this.query = query;
            this.cb = cb;
            this.predicates = new ArrayList<>();
            this.simplifiedPredicates = new ArrayList<>();

            loadPredicates(filter);
            loadSimplifiedPredicates(filter);

            return filter.getSimplified() == null || filter.getSimplified()
                    ? generateSimplifiedPredicates()
                    : generatePredicates();
        };

    }

    public Pageable buildPageable(Filter filter) {

        if(filter.getSortList() != null && !filter.getSortList().isEmpty()){

            return new Pagination(filter.getPage(), filter.getPageSize(), filter.getSortList(), filter.getSortDirection()).build();

        } else if(filter.getSort() != null) {

            return new Pagination(filter.getPage(), filter.getPageSize(), filter.getSort(), filter.getSortDirection()).build();
        }

        return new Pagination(filter.getPage(), filter.getPageSize()).build();

    }


    /**
     * This method should be used when an advanced search is required.
     * Advanced search means a multiple parameters search
     *
     * @param filter Filter to be used when searching for entity
     */
    protected abstract void loadPredicates(Filter filter);

    /**
     * This method should be used when a simple search is required.
     * Simple search means one input parameter search string for one or
     * more fields in the entity
     *
     * @param filter Filter to be used when searching for entity
     */
    protected abstract void loadSimplifiedPredicates(Filter filter);

    protected void addPredicates(Predicate predicate) {

        if(predicate == null){

            return;
        }

        predicates = predicates != null ? predicates : new ArrayList<>();

        predicates.add(predicate);
    }

    protected void addSimplifiedPredicates(Predicate predicate) {

        if(predicate == null){

            return;

        }

        simplifiedPredicates = simplifiedPredicates != null ? simplifiedPredicates : new ArrayList<>();

        simplifiedPredicates.add(predicate);
    }


    private Predicate generatePredicates() {

        Predicate[] predicatesArray = this.predicates.toArray(new Predicate[this.predicates.size()]);

        return this.cb.and(predicatesArray);

    }

    private Predicate generateSimplifiedPredicates() {

        Predicate[] predicatesArray = this.simplifiedPredicates.toArray(new Predicate[this.simplifiedPredicates.size()]);

        return this.cb.and(predicatesArray);

    }

    protected String getNormalizedParam(String param) {

        return param == null ? null : '%' + (Normalizer.normalize(param.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[.&,\\-)(?']", "")) + '%';
    }
    protected String getNormalizedParamIgnorePrefix(String param) {

        return param == null ? null : (Normalizer.normalize(param.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[.&,\\-)(?']", "")) + '%';
    }

    protected Expression<String> getNormalizedEmpression(Path<Object> column) {

        return this.cb.lower(
                this.cb.function(
                        "replace",
                        String.class,
                        this.cb.function("unaccent", String.class, column),
                        this.cb.literal("_"), this.cb.literal(" ")
                )
        );
    }
}

package com.zenroku.financial.records.api.app.transactionhistory.specification;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.settings.model.Filter;
import com.zenroku.financial.records.api.settings.util.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TransactionHistorySpecification implements Specification<TransactionHistory> {

    Filter filter;

    TransactionHistorySpecification (Filter filter) { this.filter = filter; }

    @Override
    public Predicate toPredicate(Root<TransactionHistory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return new SpecificationBuilder().getPredicate(root,criteriaBuilder,filter);
    }
}

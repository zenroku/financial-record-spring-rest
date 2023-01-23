package com.zenroku.financial.records.api.app.transactionhistory.specification;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.Filter;
import com.zenroku.financial.records.api.settings.util.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistorySpecificationBuilder {

    SpecificationBuilder builder = new SpecificationBuilder();

    public Specification<TransactionHistory> build(BaseRequest request) {
        List<Filter> filterSet = builder.jsonToFilter(request);
        List<Filter> searchSet = builder.jsonToSearch(request);

        List<Specification> filterSpec = filterSet.stream().map(TransactionHistorySpecification::new).collect(Collectors.toList());
        List<Specification> searchSpec = searchSet.stream().map(TransactionHistorySpecification::new).collect(Collectors.toList());

        return builder.generate(filterSpec,searchSpec);
    }
}

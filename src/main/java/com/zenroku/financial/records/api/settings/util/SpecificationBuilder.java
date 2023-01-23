package com.zenroku.financial.records.api.settings.util;

import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.Filter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecificationBuilder {

    public List<Filter> jsonToFilter(BaseRequest request){
        List<Filter> returnValue = new ArrayList<>();
        if (request.getFilters() != null && request.getFilters().length() > 0){
            JSONArray filterArray = request.getFilters();
            for (int i = 0; i < filterArray.length(); i++) {
                Filter filter = new Filter();
                JSONObject filterObj = filterArray.getJSONObject(i);
                filter.setField(filterObj.getString("field"));
                filter.setOp(filterObj.getString("op"));
                filter.setValue(String.valueOf(filterObj.get("value")));
                returnValue.add(filter);
            }
            return returnValue;

        }


        return returnValue;
    }

    public List<Filter> jsonToSearch(BaseRequest request){
        List<Filter> returnValue = new ArrayList<>();
        if (request.getSearchFields() != null && request.getSearch() != null){
            if (request.getSearch().isBlank()) return returnValue;
            JSONArray searchFields = request.getSearchFields();

            for (int i = 0; i < searchFields.length(); i++) {
                Filter filter = new Filter();
                filter.setField(searchFields.getString(i));
                filter.setOp("like");
                filter.setValue(request.getSearch());
                returnValue.add(filter);
            }
            return returnValue;

        }

        return returnValue;
    }

    public Specification generate(List<Specification> filters, List<Specification> searchs){
        Specification resultFilter = filters.size() > 0 ? filters.get(0) : null;
        Specification resultSearch = searchs.size() > 0 ? searchs.get(0) : null;

        for (int i = 0; i < filters.size(); i++) {
            resultFilter = Specification.where(resultFilter).and(filters.get(i));
        }

        for (int i = 0; i < searchs.size(); i++) {
            resultSearch = Specification.where(resultSearch).or(searchs.get(i));
        }

        if (filters.size() > 0 && searchs.size() > 0){
            return resultFilter.and(resultSearch);
        } else if (filters.size() > 0){
            return resultFilter;
        } else if (searchs.size() > 0){
            return resultSearch;
        }

        return null;
    }

    public Predicate getPredicate(Root<?> root, CriteriaBuilder criteria, Filter filter){
        String[] tableRelation = filter.getField().replace("__",".").split("\\.");
        if (tableRelation.length > 1){
            Path path = null;
            if (tableRelation.length == 2){
                path = root.join(tableRelation[0].concat("Relations"), JoinType.LEFT).get(tableRelation[1]);
            } else if (tableRelation.length == 3) {
                path = root
                        .join(tableRelation[0].concat("Relations"),JoinType.LEFT)
                        .join(tableRelation[1].concat("Relations"),JoinType.LEFT).get(tableRelation[2]);
            }

            if (filter.getOp().equals("=")){
                return criteria.equal(path.as(String.class),filter.getValue());
            }
        }

        if (filter.getOp().equals("=")){
            return criteria.equal(root.get(filter.getField()).as(String.class),filter.getValue());
        }


        return null;
    }
}

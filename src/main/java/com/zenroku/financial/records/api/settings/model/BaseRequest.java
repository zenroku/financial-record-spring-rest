package com.zenroku.financial.records.api.settings.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BaseRequest implements Serializable {
    Integer start = 0;

    Integer limit = 5;

    JSONArray sort;

    @JsonIgnore
    Pageable pageable;


    public Pageable getPageable() throws Exception {
        Sort order = Sort.unsorted();

        if (this.sort != null && this.sort.length() > 0){
            List<Sort.Order> sortList = new ArrayList<>();
            for (int i = 0; i < this.sort.length(); i++) {
                JSONObject sortObject = new JSONObject(sort.get(i).toString());
                String field = sortObject.getString("field").replace("__","Relations.");
                String dir = sortObject.getString("dir");
                if (dir.equals("desc")){
                    sortList.add(Sort.Order.desc(field));
                } else {
                    sortList.add(Sort.Order.asc(field));
                }
            }
            order = Sort.by(sortList);
        }

        Integer page = limit == -1 ? Math.floorDiv(start,Integer.MAX_VALUE) : Math.floorDiv(start,limit);
        Integer size = limit == -1 ? Integer.MAX_VALUE : limit;

        return PageRequest.of(page,size,order);
    }
}

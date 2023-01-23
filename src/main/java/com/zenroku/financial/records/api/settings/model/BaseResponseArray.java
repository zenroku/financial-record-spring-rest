package com.zenroku.financial.records.api.settings.model;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Embeddable
public class BaseResponseArray extends AbstractResponse{
    Collection<?> data;

    Integer page;

    Integer limit;

    Long totalData;

    Integer totalPage;

    public void setProperties(Integer page,Integer limit, Long totalData, Integer totalPage){
        this.setPage(page);
        this.setLimit(limit);
        this.setTotalData(totalData);
        this.setTotalPage(totalPage);
    }
}

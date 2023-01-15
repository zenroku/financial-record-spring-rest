package com.zenroku.financial.records.api.settings.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Embeddable
public class BaseResponseArray extends AbstractResponse{
    Collection<?> datas;
}

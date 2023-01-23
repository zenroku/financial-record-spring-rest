package com.zenroku.financial.records.api.settings.model;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class AbstractResponse implements Serializable {
    private Boolean success = true;

    private Integer status = 200;
}

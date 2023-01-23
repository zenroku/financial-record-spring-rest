package com.zenroku.financial.records.api.settings.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter {
    String field;
    String op;
    String value;
}

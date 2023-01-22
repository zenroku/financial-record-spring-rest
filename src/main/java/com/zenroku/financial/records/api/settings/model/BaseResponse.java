package com.zenroku.financial.records.api.settings.model;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Embeddable
public class BaseResponse extends AbstractResponse{
    Map<String,?> data;
 }

package com.zenroku.financial.records.api.app.transactionhistory.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType,String> {
    @Override
    public String convertToDatabaseColumn(ActionType actionType) {
        return actionType.getReadable();
    }

    @Override
    public ActionType convertToEntityAttribute(String s) {
        for (ActionType value : ActionType.values()) {
            if (value.getReadable().equals(s)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + s + " pleas provide Debit or Credit");
    }
}

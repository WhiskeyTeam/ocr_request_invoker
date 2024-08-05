package com.whiskey.ocr.libs.model.filtered;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class FilteredBusiness {
    private String registrationNumber;
    private String businessName;
    private String ownerName;
    private String businessAddress;
    private List<SubField> subFields;

    @Getter
    @Setter
    @ToString
    public static class SubField {
        private String inferText;
        private double inferConfidence;
    }
}

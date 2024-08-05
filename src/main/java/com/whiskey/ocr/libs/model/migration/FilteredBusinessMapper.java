package com.whiskey.ocr.libs.model.migration;

import com.whiskey.ocr.libs.model.ResponseHeadForBusiness;
import com.whiskey.ocr.libs.model.filtered.FilteredBusiness;

import java.util.stream.Collectors;

public class FilteredBusinessMapper {
    public static FilteredBusiness map(ResponseHeadForBusiness response) {
        FilteredBusiness filteredBusiness = new FilteredBusiness();

        ResponseHeadForBusiness.Image image = response.getImages().get(0);

        // Extract registration number
        ResponseHeadForBusiness.Image.Field regNumberField = image.getFields().stream()
                .filter(field -> "등록번호".equals(field.getName()))
                .findFirst()
                .orElse(null);

        if (regNumberField != null) {
            filteredBusiness.setRegistrationNumber(regNumberField.getInferText());
        }

        // Extract business name
        ResponseHeadForBusiness.Image.Field businessNameField = image.getFields().stream()
                .filter(field -> "상호명".equals(field.getName()))
                .findFirst()
                .orElse(null);

        if (businessNameField != null) {
            filteredBusiness.setBusinessName(businessNameField.getInferText());
        }

        // Extract owner name
        ResponseHeadForBusiness.Image.Field ownerNameField = image.getFields().stream()
                .filter(field -> "사업자명".equals(field.getName()))
                .findFirst()
                .orElse(null);

        if (ownerNameField != null) {
            filteredBusiness.setOwnerName(ownerNameField.getInferText());
        }

        // Extract business address
        ResponseHeadForBusiness.Image.Field businessAddressField = image.getFields().stream()
                .filter(field -> "사업장주소".equals(field.getName()))
                .findFirst()
                .orElse(null);

        if (businessAddressField != null) {
            filteredBusiness.setBusinessAddress(businessAddressField.getInferText());
        }

        // Extract subFields
        if (businessAddressField != null) {
            filteredBusiness.setSubFields(businessAddressField.getSubFields().stream().map(subField -> {
                FilteredBusiness.SubField filteredSubField = new FilteredBusiness.SubField();
                filteredSubField.setInferText(subField.getInferText());
                filteredSubField.setInferConfidence(subField.getInferConfidence());
                return filteredSubField;
            }).collect(Collectors.toList()));
        }

        return filteredBusiness;
    }
}
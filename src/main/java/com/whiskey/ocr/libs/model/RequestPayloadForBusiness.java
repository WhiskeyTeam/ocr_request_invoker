package com.whiskey.ocr.libs.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class RequestPayloadForBusiness {
    private String version;
    private String requestId;
    private long timestamp;
    private String lang;

    private List<ImageHeadForBusiness> images = new ArrayList<>();
    private boolean enableTableDetection;
}


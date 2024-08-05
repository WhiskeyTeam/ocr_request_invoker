package com.whiskey.ocr.libs.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class RequestPayloadForReceipt {
    private String version;
    private String requestId;
    private long timestamp;
    // todo. private List<> images = new ArrayList<>();
    private List<ImageHeadForReceipt> images = new ArrayList<>();
}

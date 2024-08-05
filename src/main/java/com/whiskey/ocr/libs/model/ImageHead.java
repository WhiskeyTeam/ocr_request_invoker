package com.whiskey.ocr.libs.model;

import lombok.*;

@Getter
@Setter
public class ImageHead {
    private String format;
    private String url;
    private String data;
    private String name;
    private int[] templateIds;
}

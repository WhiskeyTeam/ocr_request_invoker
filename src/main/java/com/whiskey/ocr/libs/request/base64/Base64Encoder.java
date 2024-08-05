package com.whiskey.ocr.libs.request.base64;

import java.util.Base64;

public class Base64Encoder {
    public String encode(String str) {
        byte[] ba = new ImageStream().downloadImageAsByteArray(str);
        return Base64.getEncoder().encodeToString(ba);  // byte array -> base64 string
    }
}

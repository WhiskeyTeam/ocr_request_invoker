package com.whiskey.ocr.libs.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
public class ResponseHeadForReceipt {
    private String version;
    private String requestId;
    private long timestamp;
    private List<ImageForReceipt> images = new ArrayList<>();
}

@Getter
@Setter
@ToString
class ImageForReceipt {
    private String uid;
    private String name;
    private String inferResult;
    private String message;
    private ValidationResultForReceipt validationResult;

}

@Getter
@Setter
@ToString
class ValidationResultForReceipt {
    private String result;
}

@Getter
@Setter
@ToString
class Receipt {
    private Meta meta;
    private ResultForReceipt result;
}

@Getter
@Setter
@ToString
class Meta {
    private String estimatedLanguage;
}

@Getter
@Setter
@ToString
class ResultForReceipt {
    private StoreInfo storeInfo;
    private PaymentInfo paymentInfo;
    private List<SubResult> subResults = new ArrayList<>();
    private TotalPrice totalPrice;
    private List<SubTotal> subTotal = new ArrayList<>();
}

@Getter
@Setter
@ToString
class SubTotal {
    private List<TaxPrice> taxPrice = new ArrayList<>();
}

@Getter
@Setter
@ToString
class TaxPrice {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class TotalPrice {
    private TotalPricePrice price;
}

@Getter
@Setter
@ToString
class TotalPricePrice {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class SubResult {
    List<SubResultItem> items = new ArrayList<>();
}

@Getter
@Setter
@ToString
class SubResultItem {
    private SubResultItemDefinition name;
    private SubResultItemDefinition count;
    private SubResultItemPrice price;
}

@Getter
@Setter
@ToString
class SubResultItemDefinition {
    private String name;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class SubResultItemPrice {
    private SubSubPrice price;
    private UnitPrice unitPrice;
}

@Getter
@Setter
@ToString
class UnitPrice {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class SubSubPrice {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class StoreInfo {
    private StoreName name;
    private BizNum bizNum;
    private List<Address> address = new ArrayList<>();
    private List<Tel> tel = new ArrayList<>();
}

@Getter
@Setter
@ToString
class CardInfo {
    private Company company;
    private CardNumber number;
}

@Getter
@Setter
@ToString
class CardNumber {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Company {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class PaymentInfo {
    private DateForReceipt date;
    private CardInfo cardInfo;
}

@Getter
@Setter
@ToString
class DateForReceipt {
    private String text;
    private DateFormatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Tel {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class BizNum {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Address {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class StoreName {
    private String text;
    private Formatted formatted;
    private String keyText;
    private double confidenceScore;
    private List<BoundingPolyForReceipt> boundingPolys = new ArrayList<>();
    private List<MaskingPoly> maskingPolys = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Formatted {
    private String value;
}

@Getter
@Setter
@ToString
class DateFormatted {
    private String hour;
    private String minute;
    private String second;
}

@Getter
@Setter
@ToString
class BoundingPolyForReceipt {
    private List<VerticesForReceipt> vertices = new ArrayList<>();
}

@Getter
@Setter
@ToString
class VerticesForReceipt {
    private int x;
    private int y;
}

@Getter
@Setter
@ToString
class MaskingPoly {
}


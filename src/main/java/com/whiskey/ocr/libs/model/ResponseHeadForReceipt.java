package com.whiskey.ocr.libs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponseHeadForReceipt {
    private String version;
    private String requestId;
    private long timestamp;
    private List<Image> images;

    @Getter
    @Setter
    @ToString
    public static class Image {
        private String uid;
        private String name;
        private String inferResult;
        private String message;
        private ValidationResult validationResult;
        private Receipt receipt;

        @Getter
        @Setter
        @ToString
        public static class ValidationResult {
            private String result;
        }

        @Getter
        @Setter
        @ToString
        public static class Receipt {
            private Meta meta;
            private Result result;

            @Getter
            @Setter
            @ToString
            public static class Meta {
                private String estimatedLanguage;
            }

            @Getter
            @Setter
            @ToString
            public static class Result {
                private StoreInfo storeInfo;
                private PaymentInfo paymentInfo;
                private List<SubResult> subResults;
                private SubPrice totalPrice;
                private List<SubTotal> subTotal;

                @Getter
                @Setter
                @ToString
                public static class StoreInfo {
                    private Name name;
                    private BizNum bizNum;
                    private List<Address> addresses;
                    private List<Tel> tel;

                    @Getter
                    @Setter
                    @ToString
                    public static class Name {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                        private List<?> maskingPolys;
                    }

                    @Getter
                    @Setter
                    @ToString
                    public static class BizNum {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                        private List<?> maskingPolys;
                    }

                    @Getter
                    @Setter
                    @ToString
                    public static class Address {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                        private List<?> maskingPolys;
                    }

                    @Getter
                    @Setter
                    @ToString
                    public static class Tel {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                    }
                }

                @Getter
                @Setter
                @ToString
                public static class PaymentInfo {
                    private Date date;
                    private Time time;
                    private CardInfo cardInfo;

                    @Getter
                    @Setter
                    @ToString
                    public static class Date {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                        private List<?> maskingPolys;
                    }

                    @Getter
                    @Setter
                    @ToString
                    public static class Time {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                        private List<?> maskingPolys;
                    }

                    @Getter
                    @Setter
                    @ToString
                    public static class CardInfo {
                        private Company company;
                        private Number number;

                        @Getter
                        @Setter
                        @ToString
                        public static class Company {
                            private String text;
                            private Formatted formatted;
                            private String keyText;
                            private double confidenceScore;
                            private List<BoundingPoly> boundingPolys;
                            private List<?> maskingPolys;
                        }

                        @Getter
                        @Setter
                        @ToString
                        public static class Number {
                            private String text;
                            private Formatted formatted;
                            private String keyText;
                            private double confidenceScore;
                            private List<BoundingPoly> boundingPolys;
                            private List<?> maskingPolys;
                        }
                    }
                }

                @Getter
                @Setter
                @ToString
                public static class SubResult {
                    private List<Item> items;

                    @Getter
                    @Setter
                    @ToString
                    public static class Item {
                        private Name name;
                        private Count count;
                        private Price price;

                        @Getter
                        @Setter
                        @ToString
                        public static class Name {
                            private String text;
                            private Formatted formatted;
                            private String keyText;
                            private double confidenceScore;
                            private List<BoundingPoly> boundingPolys;
                            private List<?> maskingPolys;
                        }

                        @Getter
                        @Setter
                        @ToString
                        public static class Count {
                            private String text;
                            private Formatted formatted;
                            private String keyText;
                            private double confidenceScore;
                            private List<BoundingPoly> boundingPolys;
                        }

                        @Getter
                        @Setter
                        @ToString
                        public static class Price {
                            private PriceDetail price;
                            // private UnitPrice unitPrice;

                            @Getter
                            @Setter
                            @ToString
                            public static class PriceDetail {
                                private String text;
                                private Formatted formatted;
                                private String keyText;
                                private double confidenceScore;
                                private List<BoundingPoly> boundingPolys;
                            }

                            @Getter
                            @Setter
                            @ToString
                            public static class UnitPrice {
                                private String text;
                                private Formatted formatted;
                                private String keyText;
                                private double confidenceScore;
                                private List<BoundingPoly> boundingPolys;
                            }
                        }
                    }
                }

                @Getter
                @Setter
                @ToString
                public static class SubPrice {
                    private SubSubPrice price;
                }

                @Getter
                @Setter
                @ToString
                public static class SubSubPrice {
                    private String text;
                    private Formatted formatted;
                    private String keyText;
                    private double confidenceScore;
                    private List<BoundingPoly> boundingPolys;
                }

                @Getter
                @Setter
                @ToString
                public static class SubTotal {
                    private List<TaxPrice> taxPrice;

                    @Getter
                    @Setter
                    @ToString
                    public static class TaxPrice {
                        private String text;
                        private Formatted formatted;
                        private String keyText;
                        private double confidenceScore;
                        private List<BoundingPoly> boundingPolys;
                    }
                }
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Formatted {
            private String value;
        }

        @Getter
        @Setter
        @ToString
        public static class BoundingPoly {
            private List<Vertex> vertices;

            @Getter
            @Setter
            @ToString
            public static class Vertex {
                private double x;
                private double y;
            }
        }
    }
}

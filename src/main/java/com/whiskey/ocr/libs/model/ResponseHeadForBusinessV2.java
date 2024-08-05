package com.whiskey.ocr.libs.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponseHeadForBusinessV2 {
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
        private MatchedTemplate matchedTemplate;
        private ValidationResult validationResult;
        private ConvertedImageInfo convertedImageInfo;
        private List<Field> fields;
        private Title title;

        @Getter
        @Setter
        @ToString
        public static class MatchedTemplate {
            private int id;
            private String name;
        }

        @Getter
        @Setter
        @ToString
        public static class ValidationResult {
            private String result;
        }

        @Getter
        @Setter
        @ToString
        public static class ConvertedImageInfo {
            private int width;
            private int height;
            private int pageIndex;
            private boolean longImage;
        }

        @Getter
        @Setter
        @ToString
        public static class Field {
            private String name;
            private String valueType;
            private BoundingPoly boundingPoly;
            private String inferText;
            private double inferConfidence;
            private String type;
            private List<SubField> subFields;

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

            @Getter
            @Setter
            @ToString
            public static class SubField {
                private BoundingPoly boundingPoly;
                private String inferText;
                private double inferConfidence;
                private boolean lineBreak;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Title {
            private String name;
            private BoundingPoly boundingPoly;
            private String inferText;
            private double inferConfidence;
            private List<SubField> subFields;

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

            @Getter
            @Setter
            @ToString
            public static class SubField {
                private BoundingPoly boundingPoly;
                private String inferText;
                private double inferConfidence;
                private boolean lineBreak;
            }
        }
    }
}
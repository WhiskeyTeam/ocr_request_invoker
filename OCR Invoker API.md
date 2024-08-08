# OCR Invoker API

Clova OCR의 요청을 중계하는 API 서비스로 사용자는 최소한의 정보만을 제공하여 값을 반환받을 수 있으며, 반환값 또한 필요한 정보만으로 정제하여 전달됩니다.



## 버전 이력

| Version | Date       | Changes   |
| ------- | ---------- | --------- |
| v 0.1.0 | 2024.08.06 | 최초 작성 |



## 제공 서비스

1. [영수증 OCR](##영수증 OCR)
2. [사업자등록증 OCR](##사업자등록증 OCR)

### ---

## 영수증 OCR

| Method | Request URI                                                  |
| ------ | ------------------------------------------------------------ |
| POST   | web.dokalab.site:8082/ocr/receipt<br />[웹서비스 內] localhost:8082/ocr/receipt |



### 요청 Body

- Content-Type: application/json

| 구분             | Json Model               |
| ---------------- | ------------------------ |
| 이미지 인식 요청 | { "fileName": "string" } |

- `fileName`: 이미지의 파일명
  - NCP의 Object Storage에 파일 업로드 수행 후 등록된 파일명만(예: `sampleImage.png`)만 전달합니다.



### 응답 Body

| Classification            | Json Model                                                   |
| ------------------------- | ------------------------------------------------------------ |
| Image recognition results | {<br/>  "storeInfo": {<br/>    "name": {<br/>      "text": "String"<br/>    },<br/>    "bizNum": {<br/>      "text": "String"<br/>    },<br/>    "addresses": [<br/>      {<br/>        "text": "String"<br/>      }<br/>    ],<br/>    "tel": [<br/>      {<br/>        "text": "String"<br/>      }<br/>    ]<br/>  },<br/>  "paymentInfo": {<br/>    "date": {<br/>      "text": "String"<br/>    },<br/>    "time": {<br/>      "text": "String"<br/>    },<br/>    "cardInfo": {<br/>      "company": {<br/>        "text": "String"<br/>      },<br/>      "number": {<br/>        "text": "String"<br/>      }<br/>    }<br/>  },<br/>  "subResults": [<br/>    {<br/>      "items": [<br/>        {<br/>          "name": {<br/>            "text": "String"<br/>          },<br/>          "count": {<br/>            "text": "String"<br/>          },<br/>          "price": {<br/>            "price": {<br/>              "text": "String"<br/>            },<br/>            "unitPrice": {<br/>              "text": "String"<br/>            }<br/>          }<br/>        }<br/>      ]<br/>    }<br/>  ],<br/>  "totalPrice": {<br/>    "text": "String"<br/>  },<br/>  "subTotal": [<br/>    {<br/>      "taxPrice": [<br/>        {<br/>          "text": "String"<br/>        }<br/>      ]<br/>    }<br/>  ]<br/>} |

| Field Name                                | Data Type        | Description           |
| ----------------------------------------- | ---------------- | --------------------- |
| storeInfo                                 | Object           | 상점에 대한 정보      |
| storeInfo.name                            | Object           | 상점 이름 세부 정보   |
| storeInfo.name.text                       | String           | 상점 이름 텍스트      |
| storeInfo.bizNum                          | Object           | 사업자 번호 세부 정보 |
| storeInfo.bizNum.text                     | String           | 사업자 번호 텍스트    |
| storeInfo.addresses                       | Array of Objects | 상점 주소 목록        |
| storeInfo.addresses[].text                | String           | 주소 텍스트           |
| storeInfo.tel                             | Array of Objects | 상점 전화번호 목록    |
| storeInfo.tel[].text                      | String           | 전화번호 텍스트       |
| paymentInfo                               | Object           | 결제 정보             |
| paymentInfo.date                          | Object           | 결제 날짜 세부 정보   |
| paymentInfo.date.text                     | String           | 결제 날짜 텍스트      |
| paymentInfo.time                          | Object           | 결제 시간 세부 정보   |
| paymentInfo.time.text                     | String           | 결제 시간 텍스트      |
| paymentInfo.cardInfo                      | Object           | 카드 정보 세부 사항   |
| paymentInfo.cardInfo.company              | Object           | 카드 회사 세부 정보   |
| paymentInfo.cardInfo.company.text         | String           | 카드 회사 이름        |
| paymentInfo.cardInfo.number               | Object           | 카드 번호 세부 정보   |
| paymentInfo.cardInfo.number.text          | String           | 카드 번호             |
| subResults                                | Array of Objects | 하위 결과 목록        |
| subResults[].items                        | Array of Objects | 하위 결과의 항목 목록 |
| subResults[].items[].name                 | Object           | 항목 이름 세부 정보   |
| subResults[].items[].name.text            | String           | 항목 이름 텍스트      |
| subResults[].items[].count                | Object           | 항목 수량 세부 정보   |
| subResults[].items[].count.text           | String           | 항목 수량 텍스트      |
| subResults[].items[].price                | Object           | 항목 가격 세부 정보   |
| subResults[].items[].price.price          | Object           | 항목 가격             |
| subResults[].items[].price.price.text     | String           | 항목 가격 텍스트      |
| subResults[].items[].price.unitPrice      | Object           | 항목 단가 세부 정보   |
| subResults[].items[].price.unitPrice.text | String           | 항목 단가 텍스트      |
| totalPrice                                | Object           | 총 가격 세부 정보     |
| totalPrice.text                           | String           | 총 가격 텍스트        |
| subTotal                                  | Array of Objects | 소계 세부 정보 목록   |
| subTotal[].taxPrice                       | Array of Objects | 소계의 세금 가격 목록 |
| subTotal[].taxPrice[].text                | String           | 세금 가격 텍스트      |

--

## 사업자등록증 OCR



### 요청 Body

- Content-Type: application/json

| 구분             | Json Model               |
| ---------------- | ------------------------ |
| 이미지 인식 요청 | { "fileName": "string" } |

- `fileName`: 이미지의 파일명
  - NCP의 Object Storage에 파일 업로드 수행 후 등록된 파일명만(예: `sampleImage.png`)만 전달합니다.



### 응답 Body

| Classification   | Json Model                                                   |
| ---------------- | ------------------------------------------------------------ |
| 이미지 인식 요청 | {<br/>  "registrationNumber": "String",<br/>  "businessName": "String",<br/>  "ownerName": "String",<br/>  "businessAddress": "String",<br/>  "subFields": [<br/>    {<br/>      "inferText": "String",<br/>      "inferConfidence": "Number"<br/>    }<br/>  ]<br/>} |

| Field Name                  | Data Type        | Description    |
| --------------------------- | ---------------- | -------------- |
| registrationNumber          | String           | 등록 번호      |
| businessName                | String           | 상호명         |
| ownerName                   | String           | 대표자명       |
| businessAddress             | String           | 주소           |
| subFields                   | Array of Objects | 하위 필드 목록 |
| subFields[].inferText       | String           | 추론 텍스트    |
| subFields[].inferConfidence | Number           | 추론 신뢰도    |
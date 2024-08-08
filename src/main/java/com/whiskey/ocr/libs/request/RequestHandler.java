package com.whiskey.ocr.libs.request;

import com.whiskey.libs.rest.request.RestInvoker;
import com.whiskey.ocr.libs.model.*;
import com.whiskey.ocr.libs.model.filtered.*;
import com.whiskey.ocr.libs.model.migration.*;
import com.whiskey.ocr.libs.base64.ImageStream;
import com.whiskey.ocr.libs.request.form.RequestForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ocr")
public class RequestHandler {
    @Value("${ ocr.business.url }")
    private String businessUrl;

    @Value("${ ocr.business.secret }")
    private String businessSecret;

    @Value("${ ocr.receipt.url }")
    private String receiptUrl;

    @Value("${ ocr.receipt.secret }")
    private String receiptSecret;

    @PostMapping("/business")
    public FilteredBusiness responseBusinessOcrResult(@RequestBody RequestForm business) throws Exception {
        System.out.println(business);

        RestInvoker<ResponseHeadForBusiness> invoker = RestInvoker.create(businessUrl, ResponseHeadForBusiness.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", businessSecret
        );

        var image = new ImageHeadForBusiness();
        image.setFormat(business.getFileName().substring(business.getFileName().lastIndexOf(".") + 1));
        image.setUrl("https://kr.object.ncloudstorage.com/whiskey-file/" + business.getFileName());
        image.setData(null);
        image.setName(business.getFileName());
        image.setTemplateIds(new int[]{ 31316, 31315, 31313, 31304 });

        var body = new RequestPayloadForBusiness();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.setLang("ko");
        body.setEnableTableDetection(true);
        body.getImages().add(image);

        ResponseHeadForBusiness response = invoker.request(body, RequestPayloadForBusiness.class, head, com.whiskey.libs.rest.request.RequestMethod.POST);

        return FilteredBusinessMapper.map(response);
    }

    @PostMapping("/receipt")
    public FilteredReceipt responseReceiptOcrResult(@RequestBody RequestForm receipt) throws Exception {
        System.out.println(receipt);

        String url = "https://y9pm2s135x.apigw.ntruss.com/custom/v1/33193/3bf678201ff62e291fb5267cb9a6bb12dce9f30926dcdc65177280aca81531da/document/receipt";
        RestInvoker<ResponseHeadForReceipt> invoker = RestInvoker.create(receiptUrl, ResponseHeadForReceipt.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", receiptSecret
        );

        ImageStream imageStream = new ImageStream();
        byte[] imageAsByteArray = imageStream.downloadImageAsByteArray("https://kr.object.ncloudstorage.com/whiskey-file/" + receipt.getFileName());
        String base64Image = java.util.Base64.getEncoder().encodeToString(imageAsByteArray);

        var image = new ImageHeadForReceipt();

        // setformat은 위와 동일하게 확장자만 추출
        image.setFormat(receipt.getFileName().substring(receipt.getFileName().lastIndexOf(".") + 1));
        image.setData(base64Image);
        image.setName(receipt.getFileName());

        var body = new RequestPayloadForReceipt();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.getImages().add(image);

        ResponseHeadForReceipt response = invoker.request(body, RequestPayloadForReceipt.class, head, com.whiskey.libs.rest.request.RequestMethod.POST);

        return FilteredReceiptMapper.map(response);
    }
}

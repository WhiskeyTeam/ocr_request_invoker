package com.whiskey.ocr.libs.request;

import com.whiskey.ocr.libs.model.*;
import com.whiskey.ocr.libs.model.filtered.*;
import com.whiskey.ocr.libs.model.migration.*;
import com.whiskey.ocr.libs.request.base64.ImageStream;
import com.whiskey.ocr.libs.request.business.RequestForm;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ocr")
public class RequestHandler {
    @PostMapping("/business")
    public FilteredBusiness postReceipt(@RequestBody RequestForm business) throws Exception {
        System.out.println(business);

        String url = "https://mf00jmkwbp.apigw.ntruss.com/custom/v1/32965/ae43fa0909815a39a619576aff03b94bee51207695cf6066e9bf13e609f28081/infer";
        var invoker = new RestInvoker<>(url, ResponseHeadForBusinessV2.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", "cVhReUVIT0l1aWx4cEdmdnB1ZXZGVnFYaW5laVVNek0="
        );
        var image = new ImageHead();

        image.setFormat(business.getFileName().substring(business.getFileName().lastIndexOf(".") + 1));
        image.setUrl("https://kr.object.ncloudstorage.com/whiskey-file/" + business.getFileName());
        image.setData(null);
        image.setName(business.getFileName());
        image.setTemplateIds(new int[]{ 31316, 31315, 31313, 31304 });

        var body = new Payload();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.setLang("ko");
        body.setEnableTableDetection(true);
        body.getImages().add(image);

        ResponseHeadForBusinessV2 response = invoker.request(body, Payload.class, head, RequestMethod.POST);

        return FilteredBusinessMapper.map(response);
    }

    @PostMapping("/receipt")
    public FilteredReceipt postBusinessRegistration(@RequestBody RequestForm receipt) throws Exception {
        System.out.println(receipt);

        String url = "https://y9pm2s135x.apigw.ntruss.com/custom/v1/33193/3bf678201ff62e291fb5267cb9a6bb12dce9f30926dcdc65177280aca81531da/document/receipt";
        // T2l4aU90dUVzUVJ3aGVodVZqVWNwUWNsU2VSV2ZoUnE=

        var invoker = new RestInvoker<>(url, ResponseHeadForReceiptV2.class);

        Map<String, String> head = Map.of(
                "Content-Type", "application/json",
                "X-OCR-SECRET", "Tk5SZE9iaGVFdWZOV2hhb1NkZGlEZGdRU3V0WldUSG8="
        );

        ImageStream imageStream = new ImageStream();
        byte[] imageAsByteArray = imageStream.downloadImageAsByteArray("https://kr.object.ncloudstorage.com/whiskey-file/" + receipt.getFileName());
        String base64Image = java.util.Base64.getEncoder().encodeToString(imageAsByteArray);

        var image = new ImageHeadForReceipt();

        // setformat은 위와 동일하게 확장자만 추출
        image.setFormat(receipt.getFileName().substring(receipt.getFileName().lastIndexOf(".") + 1));
        image.setData(base64Image);
        image.setName(receipt.getFileName());

        var body = new ReceiptPayload();
        body.setVersion("V2");
        body.setRequestId("string");
        body.setTimestamp(1722477710525L);
        body.getImages().add(image);

        ResponseHeadForReceiptV2 response = invoker.request(body, ReceiptPayload.class, head, RequestMethod.POST);

        System.out.println(response);

        return FilteredReceiptMapper.map(response);
    }
}

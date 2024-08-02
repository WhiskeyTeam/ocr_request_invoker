package com.whiskey.ocr;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocr")
public class RequestHandler {
    @PostMapping("/receipt")
    public void postReceipt(@RequestBody String receipt) {
        System.out.println(receipt);

        // TODO. RETURN SERIALIZED JSON STRING
    }

    @PostMapping("/business")
    public void postBusinessRegistration(@RequestBody String businessRegistration) {
        System.out.println(businessRegistration);

        // TODO. RETURN SERIALIZED JSON STRING
    }
}

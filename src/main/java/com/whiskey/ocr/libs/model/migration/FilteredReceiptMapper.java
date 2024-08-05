package com.whiskey.ocr.libs.model.migration;

import com.whiskey.ocr.libs.model.ResponseHeadForReceiptV2;
import com.whiskey.ocr.libs.model.filtered.FilteredReceipt;

import java.util.stream.Collectors;

public class FilteredReceiptMapper {
    public static FilteredReceipt map(ResponseHeadForReceiptV2 response) {
        FilteredReceipt filteredReceipt = new FilteredReceipt();

        // Map StoreInfo
        ResponseHeadForReceiptV2.Image.Receipt.Result.StoreInfo sourceStoreInfo = response.getImages().get(0).getReceipt().getResult().getStoreInfo();
        FilteredReceipt.StoreInfo storeInfo = new FilteredReceipt().new StoreInfo();
        storeInfo.setName(new FilteredReceipt().new StoreInfo().new Name());
        storeInfo.getName().setText(sourceStoreInfo.getName().getText());

        storeInfo.setBizNum(new FilteredReceipt().new StoreInfo().new BizNum());
        storeInfo.getBizNum().setText(sourceStoreInfo.getBizNum().getText());

        storeInfo.setAddresses(sourceStoreInfo.getAddresses().stream().map(addr -> {
            FilteredReceipt.StoreInfo.Address address = new FilteredReceipt().new StoreInfo().new Address();
            address.setText(addr.getText());
            return address;
        }).collect(Collectors.toList()));

        storeInfo.setTel(sourceStoreInfo.getTel().stream().map(tel -> {
            FilteredReceipt.StoreInfo.Tel telInfo = new FilteredReceipt().new StoreInfo().new Tel();
            telInfo.setText(tel.getText());
            return telInfo;
        }).collect(Collectors.toList()));

        filteredReceipt.setStoreInfo(storeInfo);

        // Map PaymentInfo
        ResponseHeadForReceiptV2.Image.Receipt.Result.PaymentInfo sourcePaymentInfo = response.getImages().get(0).getReceipt().getResult().getPaymentInfo();
        FilteredReceipt.PaymentInfo paymentInfo = new FilteredReceipt().new PaymentInfo();

        paymentInfo.setDate(new FilteredReceipt().new PaymentInfo().new Date());
        paymentInfo.getDate().setText(sourcePaymentInfo.getDate().getText());

        paymentInfo.setTime(new FilteredReceipt().new PaymentInfo().new Time());
        paymentInfo.getTime().setText(sourcePaymentInfo.getTime().getText());

        paymentInfo.setCardInfo(new FilteredReceipt().new PaymentInfo().new CardInfo());
        paymentInfo.getCardInfo().setCompany(new FilteredReceipt().new PaymentInfo().new CardInfo().new Company());
        paymentInfo.getCardInfo().getCompany().setText(sourcePaymentInfo.getCardInfo().getCompany().getText());

        paymentInfo.getCardInfo().setNumber(new FilteredReceipt().new PaymentInfo().new CardInfo().new Number());
        paymentInfo.getCardInfo().getNumber().setText(sourcePaymentInfo.getCardInfo().getNumber().getText());

        filteredReceipt.setPaymentInfo(paymentInfo);

        // Map SubResults
        filteredReceipt.setSubResults(response.getImages().get(0).getReceipt().getResult().getSubResults().stream().map(subResult -> {
            FilteredReceipt.SubResult filteredSubResult = new FilteredReceipt().new SubResult();
            filteredSubResult.setItems(subResult.getItems().stream().map(item -> {
                FilteredReceipt.SubResult.Item filteredItem = new FilteredReceipt().new SubResult().new Item();

                filteredItem.setName(new FilteredReceipt().new SubResult().new Item().new Name());
                filteredItem.getName().setText(item.getName().getText());

                filteredItem.setCount(new FilteredReceipt().new SubResult().new Item().new Count());
                filteredItem.getCount().setText(item.getCount().getText());

                filteredItem.setPrice(new FilteredReceipt().new SubResult().new Item().new Price());

                filteredItem.getPrice().setPrice(new FilteredReceipt().new SubResult().new Item().new Price().new PriceDetail());
                filteredItem.getPrice().getPrice().setText(item.getPrice().getPrice().getText());

                filteredItem.getPrice().setUnitPrice(new FilteredReceipt().new SubResult().new Item().new Price().new UnitPrice());
                filteredItem.getPrice().getUnitPrice().setText(item.getPrice().getUnitPrice().getText());

                return filteredItem;
            }).collect(Collectors.toList()));

            return filteredSubResult;
        }).collect(Collectors.toList()));

        // Map TotalPrice
        FilteredReceipt.Price filteredTotalPrice = new FilteredReceipt().new Price();
        filteredTotalPrice.setText(response.getImages().get(0).getReceipt().getResult().getTotalPrice().getPrice().getText());
        filteredReceipt.setTotalPrice(filteredTotalPrice);

        // Map SubTotal
        filteredReceipt.setSubTotal(response.getImages().get(0).getReceipt().getResult().getSubTotal().stream().map(subTotal -> {
            FilteredReceipt.SubTotal filteredSubTotal = new FilteredReceipt().new SubTotal();
            filteredSubTotal.setTaxPrice(subTotal.getTaxPrice().stream().map(taxPrice -> {
                FilteredReceipt.SubTotal.TaxPrice filteredTaxPrice = new FilteredReceipt().new SubTotal().new TaxPrice();
                filteredTaxPrice.setText(taxPrice.getText());
                return filteredTaxPrice;
            }).collect(Collectors.toList()));

            return filteredSubTotal;
        }).collect(Collectors.toList()));

        return filteredReceipt;
    }
}

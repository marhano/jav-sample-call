package com.bastionpaymentsystem.app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    private static final String API_URL = "http://beta-webopicollectpayment.paybps.ovpn/apiv2";

    private static final String MERCHANT_ID = "OM25030C00008888888";
    private static final String TERMINAL_ID = "VPHP001";
    private static final String PARTNER_ID = "TESTDEV";
    private static final String PAYMENT_BRAND = "BANCNET";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{\"Partnerid\":\"" + PARTNER_ID + "\",\"Merchantid\":\"" + MERCHANT_ID + "\",\"Terminalid\":\"" + TERMINAL_ID + "\",\"AuditObj\":{\"Userid\":\"C.r001.OBiz.masteradmin\",\"Eventdatetime\":\"08/28/2025 11:50:16\",\"Applicationid\":161,\"Profileid\":\"" + PARTNER_ID + "\",\"Brandid\":\"OPIBIZ\",\"Merchantid\":\"" + MERCHANT_ID + "\",\"Terminalid\":\"" + TERMINAL_ID + "\",\"Eventcategory\":\"API-TEST\",\"Eventsubcategory\":\"Generic\",\"Eventtype\":\"REQUEST\",\"Eventsubtype\":\"Invoke\",\"Eventdetailsjson\":\"\",\"Eventdetails\":\"API method invoked from Bastion API Executor, SCD Web (testing)\",\"Devicename\":\"Chrome\",\"Deviceip\":\"203.33.196.9\"},\"MsgSecurityObj\":{\"MessageFormat\":0,\"MessageHMAC\":\"hmac\",\"MobileUUID\":\"3fa925d8-812c-4c85-bb44-8754a24edbba\",\"EncryptedData\":\"\",\"TranDateTime\":\"08/28/2025 11:50:16\"},\"PaymentBrand\":\"" + PAYMENT_BRAND + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/OPIBusiness/GetVCNForCollection"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    

}

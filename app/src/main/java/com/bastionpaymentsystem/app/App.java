package com.bastionpaymentsystem.app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
public class App {
    private static final String API_URL = "https://opibizcollectpayment.omni.money/apiv2";

    private static final String MERCHANT_ID = "OM25030C00008888888";
    private static final String TERMINAL_ID = "VPHP001";
    private static final String PARTNER_ID = "TESTDEV";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        JSONObject auditObj = new JSONObject()
            .put("Userid", "C.r001.OBiz.masteradmin")
            .put("Eventdatetime", "08/28/2025 15:55:58")
            .put("Applicationid", 190)
            .put("Profileid", "TESTDEV")
            .put("Brandid", "OPIBIZ")
            .put("Merchantid", MERCHANT_ID)
            .put("Terminalid", TERMINAL_ID)
            .put("Eventcategory", "API-TEST")
            .put("Eventsubcategory", "Generic")
            .put("Eventtype", "REQUEST")
            .put("Eventsubtype", "Invoke")
            .put("Eventdetailsjson", "")
            .put("Eventdetails", "API method invoked from Bastion API Executor, SCD Mobile (testing)")
            .put("Devicename", "Chrome")
            .put("Deviceip", "203.33.196.9");

        JSONObject msgSecurityObj = new JSONObject()
            .put("MessageFormat", 0)
            .put("MessageHMAC", "hmac")
            .put("MobileUUID", "3fa925d8-812c-4c85-bb44-8754a24edbba")
            .put("EncryptedData", "")
            .put("TranDateTime", "08/28/2025 15:55:58");

        JSONObject qrParam1 = new JSONObject()
            .put("Key", "[TRANAMOUNT]")
            .put("Value", "11.75");
        JSONObject qrParam2 = new JSONObject()
            .put("Key", "[TRANID]")
            .put("Value", "TREF90008832");
        JSONObject qrParam3 = new JSONObject()
            .put("Key", "[MOBILE]")
            .put("Value", "+63-9991234567");
        JSONObject qrParam4 = new JSONObject()
            .put("Key", "[STORELABEL]")
            .put("Value", "MYSTORELABEL");

        JSONArray qrParameters = new JSONArray()
            .put(qrParam1)
            .put(qrParam2)
            .put(qrParam3)
            .put(qrParam4);

        JSONObject json = new JSONObject()
            .put("Partnerid", PARTNER_ID)
            .put("Merchantid", MERCHANT_ID)
            .put("Terminalid", TERMINAL_ID)
            .put("AuditObj", auditObj)
            .put("MsgSecurityObj", msgSecurityObj)
            .put("PaymentBrand", "BANCNET")
            .put("QRParameters", qrParameters);

        String requestBody = json.toString();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "/OPIBusiness/GetVCNForCollection"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    

}

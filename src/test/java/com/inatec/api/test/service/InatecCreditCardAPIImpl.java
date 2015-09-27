package com.inatec.api.test.service;

import com.inatec.api.test.model.*;
import com.inatec.api.test.utils.CommonServices;
import org.springframework.http.HttpEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anatoly Chernysh
 */
public class InatecCreditCardAPIImpl implements InatecCreditCardAPI {

    public static final String SIGNATURE_PARAM = "signature";

    public static final String SECRET_KEY = "b185";

    public static final String BASE_URL = "https://www.taurus21.com/pay";

    private String createRequestBody(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer requestBody = new StringBuffer();

        for (String key : params.keySet()) {
            String value = params.get(key);
            if (StringUtils.hasText(value)) {
                requestBody.append(key).append("=").append(URLEncoder.encode(value.toString(), "UTF-8")).append('&');
            }
        }
        if (requestBody.charAt(requestBody.length() - 1) == '&') {
            requestBody = requestBody.deleteCharAt(requestBody.length() - 1);
        }

        return requestBody.toString();
    }

    private Map<String, String> convertRequestToMap(Object request) throws IllegalAccessException {
        Map<String, String> params = new HashMap<>();
        for (Field field : request.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(request);
            params.put(field.getName(), value == null ? "" : value.toString());
        }

        return params;
    }

    private Map<String, String> signRequest(Map<String, String> params) {
        params.put(SIGNATURE_PARAM, CommonServices.calculateSignature(SECRET_KEY, params));
        return params;
    }

    private Response createResponse(String rawResponse) throws NoSuchFieldException, IllegalAccessException {
        Response response = new Response();

        String []params = rawResponse.split("&");
        for (String param : params) {
            String []keyValue = param.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];

                Field field = response.getClass().getDeclaredField(key);
                ReflectionUtils.makeAccessible(field);
                if (field != null) {
                    if (field.getType().isAssignableFrom(BigDecimal.class)) {
                        field.set(response, new BigDecimal(value));
                    }
                    else if (field.getType().isAssignableFrom(Integer.class)) {
                        field.set(response, new Integer(value));
                    }
                    else if (field.getType().isAssignableFrom(Long.class)) {
                        field.set(response, new Long(value));
                    }
                    else {
                        field.set(response, value);
                    }
                }
            }
        }

        return response;
    }

    private Response sendRequest(String url, Object request) throws IllegalAccessException, UnsupportedEncodingException, NoSuchFieldException {
        String requestBody = createRequestBody(signRequest(convertRequestToMap(request)));
        HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, requestEntity, String.class);
        System.out.println(response);

        return createResponse(response);
    }

    public Response authorize(AuthorizeRequest request) {
        String url = "/backoffice/payment_authorize";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Response preAuthorize(AuthorizeRequest request) {
        String url = "/backoffice/payment_preauthorize";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Response capture(CaptureRequest request) {
        String url = "/backoffice/payment_capture";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Response partialCapture(PartialCaptureRequest request) {
        String url = "/backoffice/payment_partialcapture";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Response refund(RefundRequest request) {
        String url = "/backoffice/payment_refund";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Response reversal(ReversalRequest request) {
        String url = "/backoffice/payment_reversal";
        try {
            return sendRequest(BASE_URL + url, request);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
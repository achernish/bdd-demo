package com.inatec.api.test.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Anatoly Chernysh
 */
public class CommonServices {

    public static String calculateSignature(String secretKey, Map<String, String> params) {
        String []keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuffer values = new StringBuffer();

        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.hasText(value)) {
                values.append(value);
            }
        }
        values.append(secretKey);

        return DigestUtils.sha1Hex(values.toString());
    }
}
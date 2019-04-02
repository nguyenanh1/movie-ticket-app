package vn.anhnguyen.ticketmovie.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonVls {
    public static final String BASE_URL = "http://3.1.38.169:55891/";
    public static final String BASE_URL_CDN = "http://103.56.158.146:8889/";

    //Lý do đến LoginActivity
    public static final String REASON_FOR_LOGIN = "reason for login";

    public static final int RESPONE_500 = 500;
    public static final int RESPONE_400 = 400;
    public static final int RESPONE_499 = 499;

    public static final int RESPONE_SUCCESS = 200;
    public static final int LOGIN_ON_OTHER_DEVICE = 409;
    public static final int RESPONE_DENIED_ACCESS = 405;
    public static final int NOT_FOUND = 404;
    public static final int TOKEN_TIME_OUT = 406;
    public static final int SESSION_TIME_OUT = 401;

    public static String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
            while (result.length() < 32) {
                result = "0" + result;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}

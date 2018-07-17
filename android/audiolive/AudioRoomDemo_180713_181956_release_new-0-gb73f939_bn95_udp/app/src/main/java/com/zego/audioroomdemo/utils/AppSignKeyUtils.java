package com.zego.audioroomdemo.utils;

import android.content.Context;
import android.content.res.Resources;

import com.zego.audioroomdemo.R;

/**
 * App's Id and key utils.
 *
 * <p>Copyright © 2017 Zego. All rights reserved.</p>
 *
 * @author realuei on 2017/7/11.
 */

public class AppSignKeyUtils {
    /**
     * 请开发者联系 ZEGO support 获取各自业务的 AppID 与 signKey
     * Demo 默认使用 UDP 模式，请填充该模式下的 AppID 与 signKey
     * AppID 填写样式示例：1234567890L
     * signKey 填写样式示例：new byte[] { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09,  (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09,  (byte) 0x00, (byte) 0x01};
    **/
    @SuppressWarnings("unused")
    static final private long RTMP_APP_ID = 0L;

    static final public long UDP_APP_ID =  ;

    static final public long INTERNATIONAL_APP_ID = 100L;

    @SuppressWarnings("unused")
    final static private byte[] signData_rtmp = new byte[] {
            (byte)0x00
    };

    final static private byte[] signData_udp = ;

    final static private byte[] signData_international = new byte[] {
            (byte)0x00
    };


    static public boolean isInternationalProduct(long appId) {
        return appId == INTERNATIONAL_APP_ID;
    }

    static public boolean isUdpProduct(long appId) {
        return appId == UDP_APP_ID;
    }

    static public byte[] requestSignKey(long appId) {
        if (appId == UDP_APP_ID) {
            return signData_udp;
        } else if (appId == INTERNATIONAL_APP_ID) {
            return signData_international;
        } else if (appId == RTMP_APP_ID) {
            return signData_rtmp;
        }
        return null;
    }
    
    /**
     * Please contact ZEGO support to get the AppID and signKey
     * 请开发者联系 ZEGO support 获取 APPID 与 signKey
    **/
    static public String getAppTitle(long appId, Context context) {
        String appTitle;
        Resources resources = context.getResources();
        if (appId == ) {   // UDP 模式下的 AppID
            appTitle = resources.getString(R.string.zg_app_title, resources.getString(R.string.zg_text_app_flavor_china));
        } else if (appId == 100L) {   // International
            appTitle = resources.getString(R.string.zg_app_title, resources.getString(R.string.zg_text_app_flavor_intl));
        } else {    // Custom
            appTitle = resources.getString(R.string.zg_app_title, resources.getString(R.string.zg_text_app_flavor_customize));
        }
        return appTitle;
    }


    static public String convertSignKey2String(byte[] signKey) {
        StringBuilder buffer = new StringBuilder();
        for (int b : signKey) {
            buffer.append("0x").append(Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6)).append(",");
        }
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    static public byte[] parseSignKeyFromString(String strSignKey) throws NumberFormatException {
        String[] keys = strSignKey.split(",");
        if (keys.length != 32) {
            throw new NumberFormatException("App Sign Key Illegal");
        }
        byte[] byteSignKey = new byte[32];
        for (int i = 0; i < 32; i++) {
            int data = Integer.valueOf(keys[i].trim().replace("0x", ""), 16);
            byteSignKey[i] = (byte) data;
        }
        return byteSignKey;
    }
}

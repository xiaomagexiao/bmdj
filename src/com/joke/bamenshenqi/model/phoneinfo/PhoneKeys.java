package com.joke.bamenshenqi.model.phoneinfo;


import java.util.ArrayList;

public class PhoneKeys {
    public class PhoneAddtionalKeys {
        public static final String ANDROIDID = "androidid";
        public static final String BASSID = "bassid";
        public static final String DEVICEID = "deviceid";
        public static final String MACADDRESS = "macaddress";
        public static final String SUBSCRIBERID = "subscriberid";

        public PhoneAddtionalKeys() {
            super();
        }
    }

    public class PhoneBasicKeys {
        public static final String BOARD = "board";
        public static final String BOOTLOADER = "bootloader";
        public static final String BRAND = "brand";
        public static final String CODENAME = "codename";
        public static final String DEVICE = "device";
        public static final String DISPLAY = "display";
        public static final String INCREMENTAL = "incremental";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MODEL = "model";
        public static final String PRODUCT = "product";
        public static final String RELEAZE = "releaze";
        public static final String SDK = "sdk";
        public static final String SDKINT = "sdkint";
        public static final String SERIAL = "serial";

        public PhoneBasicKeys() {
            super();
        }
    }

    public class phoneNetKeys {
        public static final String NETWORKCOUNTRYISO = "networkcountryiso";
        public static final String NETWORKOPERATOR = "networkoperator";
        public static final String NETWORKOPERATORNAME = "networkoperatorname";
        public static final String NETWORKTYPE = "networktype";
        public static final String SIMCOUNTRYISO = "simcountryiso";
        public static final String SIMOPERATOR = "simoperator";
        public static final String SIMOPERATORNAME = "simoperatorname";
        public static final String SIMSERIALNUMBER = "simserialnumber";
        public static final String SIMSTATE = "simstate";

        public phoneNetKeys() {
            super();
        }
    }

    static String[] allKeys;

    public PhoneKeys() {
        super();
    }

    public static String[] getAllKeys() {
        if(PhoneKeys.allKeys == null || PhoneKeys.allKeys.length == 0) {
            ArrayList v0 = new ArrayList();
            v0.add("manufacturer");
            v0.add("model");
            v0.add("device");
            v0.add("product");
            v0.add("display");
            v0.add("brand");
            v0.add("board");
            v0.add("bootloader");
            v0.add("serial");
            v0.add("releaze");
            v0.add("incremental");
            v0.add("sdk");
            v0.add("sdkint");
            v0.add("codename");
            v0.add("macaddress");
            v0.add("bassid");
            v0.add("androidid");
            v0.add("simoperator");
            v0.add("simcountryiso");
            v0.add("simserialnumber");
            v0.add("simstate");
            v0.add("simoperatorname");
            v0.add("networktype");
            v0.add("networkoperator");
            v0.add("networkoperatorname");
            v0.add("networkcountryiso");
            v0.add("deviceid");
            v0.add("subscriberid");
            PhoneKeys.allKeys = new String[v0.size()];
            v0.toArray(PhoneKeys.allKeys);
        }

        return PhoneKeys.allKeys;
    }
}


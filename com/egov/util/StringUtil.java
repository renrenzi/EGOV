package com.egov.util;

/**
 * @author JJ
 * @date 2021/5/11  - {TIME}
 */
public class StringUtil {
    public static String getCty(String Cty){
        String centry = "";
        if (Cty != null && Cty == "000"){
            centry = "中国";
        }
        else if (Cty != null && Cty == "001"){
            centry = "美国";
        }
        else if (Cty != null && Cty == "002"){
            centry = "英国";
        }
        else if (Cty != null && Cty == "003"){
            centry = "日本";
        }
        return centry;
    }
    public static String getRegcry(String cry){
        String centry = "";
        if (cry != null && cry == "0"){
            centry = "人民币";
        }
        else if (cry != null && cry == "1"){
            centry = "美元";
        }
        else if (cry != null && cry == "2"){
            centry = "英镑";
        }
        else if (cry != null && cry == "3"){
            centry = "日文";
        }
        return centry;
    }
}


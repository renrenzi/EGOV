package com.egov.util;

import org.omg.CORBA.FieldNameHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @author JJ
 * @date 2021/5/9  - {TIME}
 */
public class WebUtil {
    public static void makeRequestToObject(HttpServletRequest request,Object obj)  {
        Class c = obj.getClass();
        Enumeration <String> fieldNames = request.getParameterNames();
        while (fieldNames.hasMoreElements()){
            String fieldName = fieldNames.nextElement();
            String methodName = "set" + fieldName.toUpperCase().charAt(0) +fieldName.substring(1);
            try {
                Method setMethod = c.getMethod(methodName,String.class);
                setMethod.invoke(obj,request.getParameter(fieldName));
            } catch (Exception e) {
               // e.printStackTrace();
            }
        }


    }
}

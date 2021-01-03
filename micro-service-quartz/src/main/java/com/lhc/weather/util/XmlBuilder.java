package com.lhc.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * xml转对象工具类
 * @author lhc
 */
public class XmlBuilder {

    public static Object xmlStrToObject(Class<?> clz, String xmlStr) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(clz);
        // 将xml转成对象的核心接口
        Unmarshaller unmarshaller = context.createUnmarshaller();

        StringReader reader = new StringReader(xmlStr);
        Object xmlObject = unmarshaller.unmarshal(reader);
        reader.close();
        return xmlObject;
    }

}

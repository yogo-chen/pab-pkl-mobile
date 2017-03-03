package com.cendra.prayogo.pklmobile.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.UnknownHostException;

class PklWsdlUtils {
    private static final String NAMESPACE = "http://schemas.xmlsoap.org/wsdl/";
    private static final String URL = "http://webtest.unpar.ac.id/pklws/pkl.php?wsdl";

    static String registerPkl(String email, String birthday, String name, String address, String phone, String featuredProduct) {
        String methodName = "regpkl";
        String soapAction = NAMESPACE + methodName;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("user", email);
        request.addProperty("nama", name);
        request.addProperty("alamat", address);
        request.addProperty("nohp", phone);
        request.addProperty("tgllahir", birthday);
        request.addProperty("produkunggulan", featuredProduct);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        try {
            httpTransport.call(soapAction, envelope);
            return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
        } catch (UnknownHostException e) {
            return "CONNECTION_ERROR";
        } catch (IOException e) {
            return "SERVER_ERROR";
        } catch (XmlPullParserException e) {
            return "PARSE_ERROR";
        }
    }

    static String getPkl(String sid) {
        String methodName = "getpkl";
        String soapAction = NAMESPACE + methodName;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("sid", sid);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        try {
            httpTransport.call(soapAction, envelope);
            return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
        } catch (UnknownHostException e) {
            return "CONNECTION_ERROR";
        } catch (IOException e) {
            return "SERVER_ERROR";
        } catch (XmlPullParserException e) {
            return "PARSE_ERROR";
        }
    }

    static String login(String email, String birthday) {
        String methodName = "login";
        String soapAction = NAMESPACE + methodName;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("user", email);
        request.addProperty("password", birthday);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        try {
            httpTransport.call(soapAction, envelope);
            return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
        } catch (UnknownHostException e) {
            return "CONNECTION_ERROR";
        } catch (IOException e) {
            return "SERVER_ERROR";
        } catch (XmlPullParserException e) {
            return "PARSE_ERROR";
        }
    }

    static String logout(String sid) {
        String methodName = "logout";
        String soapAction = NAMESPACE + methodName;
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty("sid", sid);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        try {
            httpTransport.call(soapAction, envelope);
            return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
        } catch (UnknownHostException e) {
            return "CONNECTION_ERROR";
        } catch (IOException e) {
            return "SERVER_ERROR";
        } catch (XmlPullParserException e) {
            return "PARSE_ERROR";
        }
    }
}

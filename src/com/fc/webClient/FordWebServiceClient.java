
package com.fc.webClient;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;

public class FordWebServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;
    private XmlSaveUtil util=new XmlSaveUtil();

    public FordWebServiceClient() {
        create0();
        Endpoint FordWebServiceSoapEP = service0 .addEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoap"), new QName("http://tempuri.org/", "FordWebServiceSoap"), util.WEB_SERVICE_URL);
        endpoints.put(new QName("http://tempuri.org/", "FordWebServiceSoap"), FordWebServiceSoapEP);
        Endpoint FordWebServiceSoapLocalEndpointEP = service0 .addEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoapLocalEndpoint"), new QName("http://tempuri.org/", "FordWebServiceSoapLocalBinding"), "xfire.local://FordWebService");
        endpoints.put(new QName("http://tempuri.org/", "FordWebServiceSoapLocalEndpoint"), FordWebServiceSoapLocalEndpointEP);
    }
    
    //20160126 xuw
    public FordWebServiceClient(String url) {
        create0();
        Endpoint FordWebServiceSoapEP = service0 .addEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoap"), new QName("http://tempuri.org/", "FordWebServiceSoap"), url);
        endpoints.put(new QName("http://tempuri.org/", "FordWebServiceSoap"), FordWebServiceSoapEP);
        Endpoint FordWebServiceSoapLocalEndpointEP = service0 .addEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoapLocalEndpoint"), new QName("http://tempuri.org/", "FordWebServiceSoapLocalBinding"), "xfire.local://FordWebService");
        endpoints.put(new QName("http://tempuri.org/", "FordWebServiceSoapLocalEndpoint"), FordWebServiceSoapLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.fc.webClient.FordWebServiceSoap.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://tempuri.org/", "FordWebServiceSoapLocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://tempuri.org/", "FordWebServiceSoap"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public FordWebServiceSoap getFordWebServiceSoap() {
        return ((FordWebServiceSoap)(this).getEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoap")));
    }

    public FordWebServiceSoap getFordWebServiceSoap(String url) {
        FordWebServiceSoap var = getFordWebServiceSoap();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public FordWebServiceSoap getFordWebServiceSoapLocalEndpoint() {
        return ((FordWebServiceSoap)(this).getEndpoint(new QName("http://tempuri.org/", "FordWebServiceSoapLocalEndpoint")));
    }

    public FordWebServiceSoap getFordWebServiceSoapLocalEndpoint(String url) {
        FordWebServiceSoap var = getFordWebServiceSoapLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

//    public static void main(String[] args) {
//        
//
//        FordWebServiceClient client = new FordWebServiceClient();
//        
//		//create a default service endpoint
//        FordWebServiceSoap service = client.getFordWebServiceSoap();
//        
//        try {
//			DateFormat formaty = new SimpleDateFormat("yyyyMMdd");	
//			String mm="FC"+formaty.format(new Date());
//			
////			System.out.println(MD5(mm));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
//    }

}


package com.fc.webClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "FordWebServiceSoap", targetNamespace = "http://tempuri.org/")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface FordWebServiceSoap {


    @WebMethod(operationName = "ReturnWebCustCompData", action = "http://tempuri.org/ReturnWebCustCompData")
    @WebResult(name = "ReturnWebCustCompDataResult", targetNamespace = "http://tempuri.org/")
    public String returnWebCustCompData(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "IsWebCustCompDataExist", action = "http://tempuri.org/IsWebCustCompDataExist")
    @WebResult(name = "IsWebCustCompDataExistResult", targetNamespace = "http://tempuri.org/")
    public int isWebCustCompDataExist(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "ReturnODPCustCompData", action = "http://tempuri.org/ReturnODPCustCompData")
    @WebResult(name = "ReturnODPCustCompDataResult", targetNamespace = "http://tempuri.org/")
    public String returnODPCustCompData(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "ReturnWebCustErrData", action = "http://tempuri.org/ReturnWebCustErrData")
    @WebResult(name = "ReturnWebCustErrDataResult", targetNamespace = "http://tempuri.org/")
    public String returnWebCustErrData(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "IsWebCustErrDataExist", action = "http://tempuri.org/IsWebCustErrDataExist")
    @WebResult(name = "IsWebCustErrDataExistResult", targetNamespace = "http://tempuri.org/")
    public int isWebCustErrDataExist(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "ReturnODPCustErrData", action = "http://tempuri.org/ReturnODPCustErrData")
    @WebResult(name = "ReturnODPCustErrDataResult", targetNamespace = "http://tempuri.org/")
    public String returnODPCustErrData(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "IsODPCustCompDataExist", action = "http://tempuri.org/IsODPCustCompDataExist")
    @WebResult(name = "IsODPCustCompDataExistResult", targetNamespace = "http://tempuri.org/")
    public int isODPCustCompDataExist(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

    @WebMethod(operationName = "IsODPCustErrDataExist", action = "http://tempuri.org/IsODPCustErrDataExist")
    @WebResult(name = "IsODPCustErrDataExistResult", targetNamespace = "http://tempuri.org/")
    public int isODPCustErrDataExist(
        @WebParam(name = "password", targetNamespace = "http://tempuri.org/")
        String password);

}

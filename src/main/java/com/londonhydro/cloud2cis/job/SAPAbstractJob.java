package com.londonhydro.cloud2cis.job;

import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.londonhydro.exception.CustomWebApplicationException;
import com.londonhydro.jaxb.JAXBHandler;
import com.londonhydro.sap.model.ServiceQueue;
import com.londonhydro.sap.rfc.ZMA_PROCESS_UPDATEStub;
import com.londonhydro.sap.rfc.ZMA_PROCESS_UPDATEStub.ZMA_PROCESS_RFC;
import com.londonhydro.sap.rfc.ZMA_PROCESS_UPDATEStub.ZMA_PROCESS_RFCResponse;

/**
 * Middle-class definition for every SAP scheduled Job.
 * 
 * @author Daniel I. Khan Ramiro (dikhan@affsys.com)
 */
public abstract class SAPAbstractJob {

	protected int SAP_SUCCESS_STATUS_CODE = 0;
	protected int SAP_XMLCORRUPT_STATUS_CODE = 3;
	protected int SAP_INVALIDBUSINESS_PROCESSTYPE_STATUS_CODE = 4;
	protected int SAP_DUPLICATE_PROCESSID_STATUS_CODE = 5;

	protected static final Log logger = LogFactory.getLog(SAPAbstractJob.class);

	@Inject
	@Named("com.londonhydro.sap.wsdl.endPoint")
	String sapWSEndPoint;

	@Inject
	@Named("com.londonhydro.broker.jobs.queues.transactionLimit")
	int transactionLimit;

	@Inject
	@Named("com.londonhydro.broker.jobs.queues.transactionSeed")
	long transactionSeed;

	private static AtomicLong atomicTransactionSeed;

	@Inject
	@Named("com.londonhydro.sap.wsdl.username")
	String sapUsername;

	@Inject
	@Named("com.londonhydro.sap.wsdl.password")
	String sapPwd;

	protected Integer sendServiceQueue(ServiceQueue sq) throws CustomWebApplicationException, XMLStreamException {
		try {
			
			int conTimeOutInMiliSec = 5000;
			int socketTimeOutInMiliSec = 10000;
			StringBuilder serverQueueXML = new StringBuilder(JAXBHandler.marshal(sq, JAXBHandler.CONTENT_TYPE.XML));
			
			serverQueueXML = new StringBuilder(serverQueueXML.toString().replace(" xsi:type=\"xs:string\"",""));
			serverQueueXML = new StringBuilder(serverQueueXML.toString().replace(" xsi:type=\"xs:int\"",""));
			serverQueueXML = new StringBuilder(serverQueueXML.toString().replace(" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"",""));
			serverQueueXML = new StringBuilder(serverQueueXML.toString().replace(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",""));
			serverQueueXML = new StringBuilder(serverQueueXML.toString().replace(" xsi:type=\"xs:dateTime\"",""));
			//serverQueueXML = new StringBuilder(serverQueueXML.toString().replace("<LastName>lName lName</LastName><DateOfBirth>08-14-1981</DateOfBirth>","<LastName>lName lName</LastName><DateOfBirth>1981-08-14</DateOfBirth>"));
			//serverQueueXML = new StringBuilder(serverQueueXML.toString().replace("<BusinessProcess8 Type=\"MoveIn\" ProcessID=\"1000179442\"","<BusinessProcess8 Type=\"MoveIn\" SubType=\"New\" ProcessID=\"1000179442\""));

			ZMA_PROCESS_UPDATEStub.String zmaString = new com.londonhydro.sap.rfc.ZMA_PROCESS_UPDATEStub.String();
			
			//serverQueueXML = new StringBuilder("<ServiceQueue><BusinessProcess8 Type=\"MoveIn\" SubType=\"New\" ProcessID=\"1000179444\" DateTime=\"01-11-2016 12:06:00\"><MasterData><CustomerNumber></CustomerNumber><BillingAccountNumber></BillingAccountNumber><ResuseAccountNumber>YES</ResuseAccountNumber><CustomerData><BPGeneralData><CustomerType>2</CustomerType><PersonalData><FirstName>birst Name</FirstName><MiddleName>bIMO Middle</MiddleName><LastName>bast Name</LastName><DateOfBirth>1957-08-13</DateOfBirth><EmployerName>Raytech</EmployerName><EmployerAddress>1222, Sheppard Ave, Toronto</EmployerAddress><School>Jack Chambers</School><ParentName>Nair Parent</ParentName><ParentAddress>Kaluvathra, Thalavady</ParentAddress><ParentPhoneNumber>9447411130</ParentPhoneNumber></PersonalData><BusinessData><BusinessType>Incorparated</BusinessType><CompanyName>SSG</CompanyName><TradingName>SSG Canada</TradingName><Website>www.ssg.ca</Website><OwnerInfo><OwnerName>Ajay Nair</OwnerName><Position>CEO</Position><PhoneNumber>5197015592</PhoneNumber><EmailAddress>ajay@ssg.ca</EmailAddress><DriverLicenceNumber>V12345688</DriverLicenceNumber><DateOfBirth>10-05-1979</DateOfBirth></OwnerInfo><OwnerInfo><OwnerName>Jayesh</OwnerName><Position>CIO</Position><PhoneNumber>5197019559</PhoneNumber><EmailAddress>jayesh@ssg.ca</EmailAddress><DriverLicenceNumber>N123456987</DriverLicenceNumber><DateOfBirth>10-11-1978</DateOfBirth></OwnerInfo><ContactInfo><ContactName>Ajay Contact</ContactName><PhoneNumber>5197015592</PhoneNumber><EmailAddress>ajay@ssg.ca</EmailAddress></ContactInfo><ContactInfo><ContactName>Manjith Contact</ContactName><PhoneNumber>9854626369</PhoneNumber><EmailAddress>Manjith@ssg.ca</EmailAddress></ContactInfo></BusinessData></BPGeneralData><PhoneNumber><EffectiveDate>01-22-2016</EffectiveDate><CountryCode>CA</CountryCode><PhoneNumber>5197017012</PhoneNumber><Extension>4620</Extension><Remarks>RESIDENTIAL</Remarks></PhoneNumber><PhoneNumber><EffectiveDate>01-25-2016</EffectiveDate><CountryCode>CA</CountryCode><PhoneNumber>5197017013</PhoneNumber><Extension>5620</Extension><Remarks>NOTIFICATION</Remarks></PhoneNumber><EmailAddress><EffectiveDate>01-22-2016</EffectiveDate><EmailAddress>a@ssg.ca</EmailAddress><Remarks>PRIMARY EMAIL</Remarks></EmailAddress><EmailAddress><EffectiveDate>01-25-2016</EffectiveDate><EmailAddress>b@ssg.ca</EmailAddress><Remarks>NOTIFICATION EMAIL</Remarks></EmailAddress><Identification><IDType>DRV_LC</IDType><IdentificationNumber>V42730150790510</IdentificationNumber><EffectiveDate>01-22-2016</EffectiveDate></Identification><Identification><IDType>VISA</IDType><IdentificationNumber>458536987522</IdentificationNumber><EffectiveDate>01-22-2016</EffectiveDate></Identification></CustomerData></MasterData><MoveIn><MoveInDate>01-22-2016</MoveInDate><Premise>187754</Premise><MailingAddressControl>2</MailingAddressControl><BuyingPremise>Y</BuyingPremise><LawyerName>Lawyer Name</LawyerName><LawyerPhoneNumber>LawyerPhoneNumber</LawyerPhoneNumber><OwnerName>OwnerName</OwnerName><OwnerPhoneNumber>OwnerPhoneNumber</OwnerPhoneNumber><MailingAddress><HouseNumber1>101</HouseNumber1><StreetName>Skyharbour</StreetName><Unit>12</Unit><City>Brampton</City><Province>ON</Province><PostalCode>N5X0B3</PostalCode><Country>CA</Country><Non-CivicInformation1>Non-CivicInformation1</Non-CivicInformation1><Non-CivicInformation2>Non-CivicInformation2</Non-CivicInformation2><COName>COName</COName></MailingAddress></MoveIn></BusinessProcess8></ServiceQueue>");
			
			zmaString.setString(serverQueueXML.toString());

			logger.debug(String.format("SAP XML Generated [%s]", serverQueueXML.toString()));
			
			System.out.println("serverQueueXML :: "+serverQueueXML.toString());
			
			ZMA_PROCESS_RFC rfc = new ZMA_PROCESS_RFC();
			rfc.setXML_IN(zmaString);

			ZMA_PROCESS_UPDATEStub stub = new ZMA_PROCESS_UPDATEStub(sapWSEndPoint);
			ServiceClient serviceClient = stub._getServiceClient();

			Options options = serviceClient.getOptions();
			HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			auth.setUsername(sapUsername);
			auth.setPassword(sapPwd);
			options.setProperty(HTTPConstants.AUTHENTICATE, auth);
			options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, conTimeOutInMiliSec);
			options.setProperty(HTTPConstants.SO_TIMEOUT, socketTimeOutInMiliSec);

			ZMA_PROCESS_RFCResponse rfcReponse = stub.zMA_PROCESS_RFC(rfc);

			return rfcReponse.getSTATUS();
		} catch (JAXBException e) {
			logger.error(String.format("JAXBException. Exception: %s", Throwables.getStackTraceAsString(e)));
			throw new CustomWebApplicationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.format(
					"Failed when marshalling the Service Queue. Exception: %s", Throwables.getStackTraceAsString(e)));
		} catch (AxisFault e) {
			logger.error(String.format("AxisFault. Exception: %s", Throwables.getStackTraceAsString(e)));
			throw new CustomWebApplicationException(HttpStatus.SC_INTERNAL_SERVER_ERROR,
					String.format("Failed due to Axis. Exception: %s", Throwables.getStackTraceAsString(e)));
		} catch (RemoteException e) {
			logger.error(String.format("RemoteException. Exception: %s", Throwables.getStackTraceAsString(e)));
			throw new CustomWebApplicationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, String
					.format("Failed when remotting connection. Exception: %s", Throwables.getStackTraceAsString(e)));
		} 
	}

	protected int getTransactionLimit() {
		return transactionLimit;
	}

	protected long nextTransactionSeed(long transactionId) {
		if (atomicTransactionSeed == null)
			atomicTransactionSeed = new AtomicLong(transactionSeed);
		try {
			return atomicTransactionSeed.addAndGet(transactionId);
		} finally {
			logger.debug(String.format("New transaction seed = %s", atomicTransactionSeed));
		}
	}

	/**
	 * 
	 * @param country
	 *            Country name
	 * @return CA if empty, or 2 character code (e.g. United States=US)
	 */
	protected static String countryCode(String country) {
		return country == null || country.length() < 2 ? "CA" : country.substring(0, 2).toUpperCase();
	}

	/**
	 * 
	 * @param province
	 *            Province name
	 * @return ON if empty, or 2 character code (e.g. Ontario=ON)
	 */
	protected static String provinceCode(String province) {
		/*
		 * return province == null || province.length() < 2 ? "ON" :
		 * province.substring(0, 2).toUpperCase();
		 */
		return province == null || province.length() < 2 ? "ON"
				: province.length() == 2 ? province.toUpperCase() : province;
	}
}

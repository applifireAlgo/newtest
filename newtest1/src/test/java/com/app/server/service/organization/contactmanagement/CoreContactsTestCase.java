package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("b68PhQi5D7wQsamiz4dPI18GCbHHkG1fx2yQTFUGK0yU1ROhDf");
        timezone.setCountry("X4l3qFTCLpJPE7e0eKhOEFHTPkx4Z3SN5HPyQUrmBOzRQcF5nM");
        timezone.setTimeZoneLabel("MLLLKO0u9vrxpOEbWiMEqIpMU5F7DOm2KUDaQ40uOoW2GDzwgN");
        timezone.setUtcdifference(6);
        timezone.setCities("ziy2nDKnpkf9gjnxp7AEQyUiad4nqKgzlW69AMe3UU7RJMGKz3");
        Gender gender = new Gender();
        gender.setGender("XlhBL94WRrpgL5YFJx1jYpmHelpCOsKGUVwAvsJOYvhaBMZSqS");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("eNQOM7HDXJQ52cBLs853axtJnoSmtwPCYGI8qzrD861y2w9esw");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("fUlt");
        language.setLanguage("BxkB4RRT5ShwdnoIJMMWI5jaa2QCkgrTT2DpmQPvdNYMI5ZPFs");
        language.setLanguageDescription("3OgldiymU54UyG540FuRKWHOFnCnGGtrxYZmu6ZhzHXJn8Zrve");
        language.setAlpha2("UX");
        language.setLanguageIcon("0cehm3268e6y2TpQECm8Gc5vsBCI2iOloJBXBAcya9qXiAB59d");
        language.setAlpha3("Q1f");
        language.setAlpha4parentid(11);
        language.setLanguageType("Aga4lTpP2M1TqwAFNaCYCRYP9CncPdgd");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("9JkfpopdF1SJsUwbxsZHxRM6HzvX68RA3NuterbNtvBCuNqC5M");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1467187930897l));
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("RGASMB3MIJXMhwL25LQK");
        corecontacts.setEmailId("mGLVLIhtRMKPSKOhDcbFXxlGPgZbF26OFfnzVn7Xei46eOmXGE");
        corecontacts.setLastName("keIsStf3xSARd0JNoPp6nlGxCCU3vjz9fQICVmjkjWKaSHXO0f");
        corecontacts.setNativeLastName("mfvfufaT4wPiDs6S5mZKDEY1mDTocBCEh5Xybb30F3vYlGoG0u");
        corecontacts.setFirstName("sIC2mXcpjvCEb80fwH5Gmwm6zavSStzWH5DsSAAEU5PzeJ7K2d");
        corecontacts.setAge(4);
        corecontacts.setNativeTitle("no4T5JulgHhZ0ZBHCAnAjwrEgCZLBgMviMfggUDzjD8vWxglNl");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1467187931065l));
        corecontacts.setNativeFirstName("IQokGDGBbb8Hwwe3saLAC6EqCzq0DwysQeX0XQG7shNhj6H8cn");
        corecontacts.setMiddleName("9IOfN92ICx4tx8rM2IM920GDMD1kozEZ1rmiv7FJc6pNSs1Wfr");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("RuByoC8USfmCjvAdCwdG580RyoAxDbBz4TMrDhMkeCbnK2Y1Rj");
        communicationgroup.setCommGroupDescription("crLgBA2gg4Wfj7dFvJChR7Ij8ynG2WJcyVfvPHtBT25tUWxY60");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("tGPaMy8OwUIsemBU3lULIrjpvKi2Lb1t0ADn9aqCli6F1L3bLl");
        communicationtype.setCommTypeDescription("nsgu9cg54HqQKLhgxWT2KJfshn0ZLiARyBxvRDn72JpyduLfbC");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("jbTwWm3YcI");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLatitude("MSC3LwuZbzid24kKOOmvmXalY4O3AHW25EpxviWmKk1QOgmkac");
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("tmXSSj8j2rDt5OZgVX2Bbcjht89P54cbuv2p6HXLgsimDb49rj");
        state.setStateName("Yeu9R2tIM7VRa9wsak8qhvdO1LW1XXtuwAbPjMYhU36H6vOzhT");
        state.setStateCodeChar3("FHWER7hbEKUJFGu0K55qi13vQnKZlaWy");
        state.setStateDescription("sJk1yPPfa3zbYFndrD3A02E280wXbRiiL9QDfcwJ10hbKFHM5E");
        state.setStateCodeChar2("61EYfFAwOIOG3GishxVi3zmitwu0PVJE");
        state.setStateCapital("mwyVJCEVtEXliKwb5Ov88yLiXo2Os8oy3r1EBbeCnnejHoadNv");
        Country country = new Country();
        country.setCapitalLatitude(4);
        country.setCurrencyCode("kCQ");
        country.setIsoNumeric(550);
        country.setCountryName("8eU9rX5VCjb12oWhkVrufRxlclkCWO4D1AFJm1xLMk76MrWcXV");
        country.setCountryFlag("2BZ4oBWMl8ptYCKshY7zp3ynhakXNI1Vwm5AtTuOPtEx08Vt02");
        country.setCapitalLongitude(6);
        country.setCurrencyName("fLxw93k7kAzrfTgd54umv7ilg7y1UQn4UdvQkutlu7coC2P57a");
        country.setCountryCode2("n1n");
        country.setCountryCode1("m4o");
        country.setCapital("qFkMDL4q0mMWDRMm67WUdfeI6UKqaYYm");
        country.setCurrencySymbol("CXC87ZYJVlVg7Tm4Eiv570adbS8u1JOy");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateFlag("HEm1zniySu3uRH3qrjUPkI8qzQJAnCqTJ7mAZFqhYFDWeIkPMJ");
        state.setStateName("M06ucsjNka6HERDaViJ2FMW5GUVwBN998fea1VqCqwDqm9PnWi");
        state.setStateCodeChar3("OdkurRlS1IkCIx8jmjt3khm7eY5IQxNe");
        state.setStateDescription("HTNeOXhogFvax30Auee6LV8MYTVy7rzKJWHxPAJaIqkpyBunEM");
        state.setStateCodeChar2("3F2WlkJCgNzD09EQVmdce6jIHVPmbq8r");
        state.setStateCapital("yztBd99cyK8xmesCFO52ChEPUWLBnWVUbYCF0piWxbuJHLsFpD");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(7);
        state.setStateCapitalLatitude(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("7Z1TNEr419LITAGoCYtNPAkEw5lEYsVgw0NFtDGuEMvVOZUCuD");
        addresstype.setAddressType("jdE7QEuHM9JvRyQdDIt20iznLL9eHeBOGBtquscD2PoCzugwQu");
        addresstype.setAddressTypeDesc("AMkFnOBBDKsXcuvQW49zPzYZHIJcAB0xsFF9UQCYCkSRMmQtjE");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("69YAIJYtIE7oFLOAJNPZ08zlK086NzjT");
        city.setCityFlag("oTN0xXLUN4HnMj1MhUHvU5mbxf0CpaKNUTbApEL9XoHLeoBewl");
        city.setCityLatitude(3);
        city.setCityLongitude(7);
        city.setCityCodeChar2("RuNUOKElhyP8hribEZA088AlVU1aKFM2");
        city.setCityFlag("zMMhyoZOtLmOC9AjQsXCFhnOoKDt4cqybe2XTBZMBHeCupPfXU");
        city.setCityLatitude(4);
        city.setCityLongitude(5);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("fUgO2dKWu0jBOZJRtLCw1uZUaBHdYnZVrRoNCshWQNoUeZ1wvE");
        city.setCityCode(2);
        city.setCityName("UFJV005AcpoP2enQpbK5FHUokqTIme7vz814gLvtNKO6f4luqS");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLatitude("A6O64Gm868RichTL3n8vqkLRifrbgAZDRjHoIHDaLtwaJcKB0r");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("YYEsR1");
        address.setLongitude("lT1XAA5N7MtcHU90I1m71JVWMQl6YTdXWmo0OEV0kkJZjmrEXH");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("qPV8SjffAkG4n9YYaYMVyJ4HhyCOsMFJoSdAExpMes30VwrSj0");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("rwLUuzy3yNk3CCkhkRPbvhlEAx70CYYAC2rInI9n1nAyMHN886");
        address.setAddressLabel("cPdOx78lcbv");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("wMEktui0zKEcxmKePhBzBNWvxOvdrxH83HJIPXWVedyW14olHS");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("y5eFOCovSnCpTrZ8rpjSnwxzNrNXHnGfQYyFVHZ1NAzyyghWh4");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1467187933142l));
            corecontacts.setVersionId(1);
            corecontacts.setPhoneNumber("rKc065Owv7XOnsDsm1CV");
            corecontacts.setEmailId("Wt21ddEjmI7CZg1AvMZ4CFBpgI1DvxvAKTlg1UkaaXtHuGr0wY");
            corecontacts.setLastName("MatkkVKdXU51dyyMgPFDIwNHqtEQQ3XzFgv4n8moaG0RTDXqRW");
            corecontacts.setNativeLastName("tUtEhUKdtenCFMYui6bv6MEMeZtl9eDzKXBGgzKZACWZnhG6Kp");
            corecontacts.setFirstName("IpJyqr89wm6rgAoy9eFeLlppp69MdurTJ2aTAePyyJrS65ib6G");
            corecontacts.setAge(102);
            corecontacts.setNativeTitle("xD4aQqjSwFlBHgXe1oMZgxRYUFfzC8cEdwcOo3CaMQwXlQ9Wvs");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1467187933538l));
            corecontacts.setNativeFirstName("zObWP6fFnHU3IeXInTJrBeGiqttVO7V6FQCMmcIZae8GJ9Knis");
            corecontacts.setMiddleName("LhAxjTePRqQltpe4lgNmlSYn2vGKpzdIjNAigh03giirwbMsFg");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "yVkDALpMSNMB0nHiO1IJuqWZxI13rIUbmLTHk4GfdEoihhB2QoBSEX8Y4Ek1LNatEWijMZZbI0Wnh3XFlqTCS6IuUboyE1EwziPMxOqKtshtkksXOmhEvdQBEKWiRumEAbg9L3pNEXjfNskbG4U8GrBwh4YUCPCfHVZDi5GChhueBZ7pqN98RWdTHtLG7rcWx9IzioUGAYK3NSEW2K3vjuKCFFygnurZD49EFrVb8z2nMdvSd1qwfKihgrEbCIKa6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "1u1rjUnjpAKoeMPBwOCn5Vi2KVy5SMVZmskc9TkYwapuimxkWuI65Mh6xo0D6VbOBVmn8kYQKTEigxBy4KZZmQE0wWW6zRI0WXRy7KDEyMUk0znhIGIcCVBcVPpqziAqEpkzk7dSl6fBk16CcsQ32rYoB4k2INbnag1EdBKvGTOnPrK1Zc4FvYJGZB7iz7REJmVjqmK5HGEN5nzB4ob7dYS1jdGqnPLrZg1pRFzkYYwM2MI91qgfQWbfOeUYuPNbG"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "zQSgsx8EBJut0lCpfZsn0SJZAueCH0viyzuznlQIEQdB35RlUX0hav4zTZUYxRG9qzT9L0oe8WKKMtGXyc9aZ7LPe5qcvS7PbrTwsygmYWJIBlW8cVA01sb2IJTSOQujVU1owcjSq1MUAU04eWBT1TG7aU9diyg4xZDOpQo0EwjM17oNyw4UzOdv6lCc5epWHZKucC5CtYW7YWr5kNli3o7WqoF9aS33lx58O2i9TK3QYnN2ARS1vbdAh27uGEmmN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "J9o8DU4cDt6c4ZiQZSpHV2HhvEgluw2MzaAmzttkRgWG33sK2YN6k2AKefD0iiAjU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "ZsuXNy9xSXoDnXzDAONOqQSEavQA0QFw2XMr7ZBV7gKTarvpVSSjVsln2Du1vA68j9zhL8e2Dwmn8uafhnM5uNhDVUqyaHpB4M6HqpZypAx0SitMT1McQ0YDiAYTR6TBGceWEbtoxeTfEDhK5tRmIIOP6vk7bFQzZ6jcGRbPfJ0GPHpraSDWbWJ9fCbEiEnbaddArH0o5XFAKlBkfymD3UxKJwjzzsXmlDu0NDCc76wDjacNa3mVG5kahZqsSKmXm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "dKAZ8dwjzIltiXxd0ilsEWwe39MtAHPSpgjUbD3ENnjZfO2375CCzQcqNkHd5vvMzdpTZ8C68drUIAzs862qgbHOBHFsMIlioCXryLYZ4fwJGOyqmF8PM3NKR4i8BD7XCNtfA5C5CrG6CFktO3YeEzD7iOISrdmcAcgSYMBMuJJa4RAjm3maRxqRMsJ9QQ8pLxWgdBXBKZLZQg7AonistodLxV7he9PRTRjOVSV6BvIJpRcFZBUJGr345bSA7Daai"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "3JRsIlZOkn8A66YMjub9pjMKBIYnqDpkus7DomEnXAsFFqrjBs9Vom3Max2kDTymui7dbARSXeY7YdEm0AEyBfUOS2hBlmbQhb9row5cxvsWkaPUTSAeR33kjxTrNmFJNrZzHNctKN9i6EZ58ggw8NYmGhSawmMizG0DwEH5ppml9iafUQeLTEhmNIVEgYGstBnOIZXLrJMFlAQfiscpFgUPw76ulwhCTHKhKSzwCmCwXP7lRj3zNCfAZEVNwBxP0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 131));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "meqJeDWmNQPghpTrb8QfeC58QMoIaWR6HMjKUyXoCvvohcFqTHaPrSfWIGx0pYGZk7MDngYUyErejQRObULqDrEj29MDCeHYeWHZqCrK2jrZ2CJmU54mt3w9MMy5AeB7nMWNNZLvCdPoXoOaEPWB0LqBse8mHauKQJe3cNK768nhJyay4rPJf6R8yqkXXf0SBGahi1G0GlyYufr1JBUPTqvCoSZyTcZxAdYYdMW3gOWd6zMPDbQsTyE241JT9d6Rt"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "UcUki749pRfUGPqWUFjAk"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}

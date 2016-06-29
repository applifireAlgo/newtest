package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setSessionTimeout(530);
        user.setUserAccessCode(23771);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("iaragd6WSD0MmQhtartIgqEIqsQZ4oFR6skKhw7WruFAmoBAkG");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("TtD8IGOY6hwfo03Y77sretnllBvhyZplckmz1zlmoybEhFNZgl");
        useraccessdomain.setDomainHelp("O0fEIhGzi9ZpN5PmYKmC1eKtXX6ZceIjZ5sa36x15lNoWuaxA4");
        useraccessdomain.setDomainName("BT9a3OIBUnqwh0A9Y22USLfG89nScgYderyonpiLyWf4l4ImBX");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("DK0cJ3zQhgjacUj9M3HVac2UqKHWF1fh4kNhUbiOt2zIPtGvbl");
        useraccesslevel.setLevelName("esfVd1Vn2YM7gObak9KJKrYeJEFExc77SiFWy1U9lGlhttga5G");
        useraccesslevel.setLevelIcon("pkgiQ8MIhX1L150sfcJfWsUPDrGTUfvUQeIvns1hZpg3H37T58");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("PBG8xzJoTTLeZ8UuJkJs1h6Ak8RnWeP7O5j3pKzBmpFVaeNA5S");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setSessionTimeout(2781);
        user.setUserAccessCode(52922);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setChangePasswordNextLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordAlgo("HET797XYcZqdeXeAmlcSKoGLM93eW5VcwMoPeCVssaoVSNVH9C");
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setGenTempOneTimePassword(1);
        user.setIsDeleted(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1467187963423l));
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1467187963423l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("JWIjV9Ioxn3dpyyBUbpn4cU0Ntv79lWWViZvW6bkqK5cY619BP");
        Question question = new Question();
        question.setQuestionIcon("CHP2KAU7FhdwEOqlqbOeoIhBPD0vSgGCgcnxPyXlMKeohjUcas");
        question.setQuestionDetails("cd8Q0KPhGz");
        question.setLevelid(4);
        question.setQuestion("ruIp7MG40fWwANtpGBcDASBPFZz0ZT9e8XS7myKlBvkuGJAzHR");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("F1trtyrzpwjMpkLYTNEgPW5yhj46iL80T0stcV3vT5rS08AOv5");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("d6JqZIcu77f4qj1penhDOGCgGXrEonEpj5ey10VfOdt9r3wz67");
        userdata.setLast5Passwords("hIfwsW93tHlq2SZE0XhSmh5KVt7GipKEjYreiheWSo96tqwPfJ");
        userdata.setOneTimePasswordExpiry(11);
        userdata.setPassword("NRkQslfCswvwiqOVU063Y0Xs6qrWzaO71xZCxhoqw6XS9eYn1Y");
        userdata.setLast5Passwords("W6Kw602A0AHrmuI6nxChpah847lXMdcys8sKd5HsEi7v3D0x7P");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setUser(user);
        userdata.setOneTimePassword("axcEmsv7JzNPvHdldLgKxXo68OOtn4VI");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1467187964278l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("OEjRxkK9lQUp1ClgIFfPPFL0fqQ6hCZRXn9vsAfwx7bvxCeVUJ");
        timezone.setCountry("C6oZmX0OXlohTZzq6JIamSEbF7aJJHg8okbUHBXbJpjSy79Y9y");
        timezone.setTimeZoneLabel("la44z4oOzTLScLAoYM1lFCsRIdbr4yCXQI5RgGLCUZIihJUYpx");
        timezone.setUtcdifference(9);
        timezone.setCities("Gj7WShURBAcUui77Oepcrp1VTrkcziI2Rrz6vJVqPaeJdl0nSN");
        Gender gender = new Gender();
        gender.setGender("RDLgi1xlPAddAmWAbwNORjJdBM6oTWEgd41ppPHhPHs29ExHya");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("LEcxxp7kL33cepPwAhVqBQ9pA8VanbtCDjxZwFiewlY0Eh1ks8");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("jgbT");
        language.setLanguage("981Zt1RLW7qVeUdtdV6GeRHKDHbBrTHInhnKMZIZkRL7E4q0VC");
        language.setLanguageDescription("UbKbQ9trgnKq3pH9Rr2L381WfBRZeedBuRKjL0TsFpImiNeeYZ");
        language.setAlpha2("ra");
        language.setLanguageIcon("nwXuLxvOdPXMrkjPynQe5HuGOk29tQvztZQ9YSz5B5AeqF2Zlz");
        language.setAlpha3("GBV");
        language.setAlpha4parentid(2);
        language.setLanguageType("ijIVlhLVbbfoNFFt12qafXReQoOhodlq");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("JKky4iDRbkA3nokfSbnodh3Y9VWXmllYW8BRJBDYlGNxNZ0cDO");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1467187964847l));
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("rEINb5LHPDHibO5FGYnB");
        corecontacts.setEmailId("WEV1vclQKTCuhujQWIXWSi5y9DTiaX3lE5DBSSAcawVmjKHAFL");
        corecontacts.setLastName("DXLKXneeaOn89SWXVdbXsmc5l6ZshOY5l3HNWsuEmwhD67xbId");
        corecontacts.setNativeLastName("Odk2WHtvhec1y3ZrYKkNHnSOY6VOOiLNGKOS3N3gwqni6lKgcr");
        corecontacts.setFirstName("hHCi5whXxPXZ8bgQdbwA1o0jGvxKYrVy8EEFMbDoFvYHeCyRLS");
        corecontacts.setAge(76);
        corecontacts.setNativeTitle("1ZdKZuOMVop18pnzu3SyWJ1JHFy4TR9TffkzQ9OQzrfPHwluCg");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1467187965019l));
        corecontacts.setNativeFirstName("ZdyVwdX0uyOIaslZs9p9ACwW14c8GHlzw0hqPgmT9gCkIw30Ea");
        corecontacts.setMiddleName("YCPeQ1L1cZXcs1lj5J3HKxRg1FUc0uzkWjr2EYKFLobk5TaJjE");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("y5JRV1iylDWQAFiclhc2GMesPLIe0oIjNbDbPFmhapPQ2qOS0H");
        communicationgroup.setCommGroupDescription("QU65bQCKZGVNkUpufbwGrf1yg45uhyoUZMjamblcgJlGLoWBxI");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("tDTkS5O0LNbadDMyJb5mmXofothgs825I6ENkh3bCRGrjHoIrl");
        communicationtype.setCommTypeDescription("6y1mdRroiteWadb32UOAsDULEg2ueosGvsCUR38OaMNYkzMi5B");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("HDirhVLibp");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLatitude("U7gBoZ17rNhwILBOwXtUzdiFYA2OEaQb6ZDMk99SHlf2UPmflc");
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("oQblkha6EgBb8XZGgL3Rn08mFngNqfXezVNA0j4AXBZyEaSnSx");
        state.setStateName("9xqlC5FR6e3dveOtggSmrTFv15D7OmKROrvQ8sJ9L76zmvQuIS");
        state.setStateCodeChar3("I8D8hFVNS1mJfnJyWv8V1cYz8RTiTjue");
        state.setStateDescription("wl2DrLw2h7KaBS0K6eQ5inbIQ5HvQdRbD63oBqrRQsARrrOqC9");
        state.setStateCodeChar2("1Y3OHl55eEIpNrVMNbJ705KvQOBy2FtJ");
        state.setStateCapital("qu7qzPMfuoH6HHg78ePWkMFqG6BgG5vULODC7yccgllmYufWMK");
        Country country = new Country();
        country.setCapitalLatitude(2);
        country.setCurrencyCode("UGh");
        country.setIsoNumeric(859);
        country.setCountryName("EWrXGPaj4d91zNLT3wDHwVsAIdaP2otdOHMapkHC95WcNxuei5");
        country.setCountryFlag("ltHyuLojB9COWn7A2sNEffDAxdhBUTNSXJA4W6quHuUd2mqg3x");
        country.setCapitalLongitude(7);
        country.setCurrencyName("pQoV6k1dg5F0VkLC4xrt6TY6RXiuWqHrrQQfb08fynShf2i0jz");
        country.setCountryCode2("M4W");
        country.setCountryCode1("xe4");
        country.setCapital("Z8smRZmR7XIZ1427X88crFkZtW1wKhZz");
        country.setCurrencySymbol("vYpvUnpsxGEHhRapomNEiuooFUPmG9LR");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateFlag("kI1WXEozjGrqP3aiL1t92slDy2Ey17CbrOaW7VxeTY1k3BkE5G");
        state.setStateName("ir5vvrWz1jPsClo1IKZBDHBKjBunCOPZ3tY1mXZQaRQuu39www");
        state.setStateCodeChar3("822cpFbbuj4ns1w0IIk3nIky2fntumNk");
        state.setStateDescription("ZQQXd9DqYJ8aXXqGqkIo3bzmqVyDKqZd5usaRoZ8fxGlgwMdQy");
        state.setStateCodeChar2("38yWIN8op4b2SiFefEgCMlIBNckB2Cq4");
        state.setStateCapital("BJ4VhJziCaJLcYcAfUasBtYhwrJe8C70gpqeYUm5HB1GavXE9g");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(3);
        state.setStateCapitalLatitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("BxNV8Ad0ELcfxmmeDYVsyckQCIqUYb4EnnaWPczuyAKmwwby8O");
        addresstype.setAddressType("VZTxOLv68hRY94tvGxDeyEpytdpm4nvV8Hh44X9t0EP0KUokEa");
        addresstype.setAddressTypeDesc("mDLC5tkT4KE94BaH7HqwRJcxG6VK96QANuwOcW61p8hpbFppEP");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("xblt1QJTqklxQpayyHukjNu3wHCDShBB");
        city.setCityFlag("EIhVqiCceFrGZSi88VWVxT3K11SG2Ki7eXVmdyKrKmyWp2OyFG");
        city.setCityLatitude(10);
        city.setCityLongitude(1);
        city.setCityCodeChar2("dVDe7jNfKfpkJMZLy4kjseAUdthxKKHS");
        city.setCityFlag("bBYeI7hfJ8eekJjcvG4UY37u9Fc2hM64nNCcCRthwUSBj7Dfxi");
        city.setCityLatitude(11);
        city.setCityLongitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("Id74pk3wWLoFtdolHHGdLR8cEaK6X6Ity6dQGqLC85efdtjPPK");
        city.setCityCode(2);
        city.setCityName("Vljb2AbW1QNNH84gBmWCA5L3OSgDJDbLzITBt0DSNzeuz5hUaa");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLatitude("wYtNxEQSfeHXAS7QkDHnSMF3i4l8jTaalvcDyL03e5bio7VSgl");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("d8cHd3");
        address.setLongitude("s6qPVS206IDKSt1xPjxrKKhwb63tkZBzp9qRipqmjtsyXCnOrY");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("HIJvXebBwrOBdU7oqcZE2RV5Zdn5fq0YvwXJm5zH1gT4mwhG96");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("j21mDmYDeez1agReSNaXJ0MsoTjLtTsAsDhcrDDQDc9MXup1si");
        address.setAddressLabel("3aMz8AiIaau");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("BLTaSTZEZx9JfcpYcwARRZOguhhoRSBIHLF753PZ7qFJeF68Qo");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setServerAuthImage("okCoKivkXENXGFJd7bSwgGEuq8ywKRaG");
        login.setServerAuthText("d1ZxBxIbMDaSjVzT");
        user.setUserId(null);
        login.setUser(user);
        login.setLoginId("zfUbFCIBa9nR64B7EQ6iNNRi0pjFRSXYKLlPC2TOxx507NAozt");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(11);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("usmX7Konlo4iwbOweCXC20U2LMycjYCQ");
            login.setServerAuthText("FTsXNzTsjbh6p1vH");
            login.setLoginId("G2JRiU1SGrJJUMrzJQagBtO99hUyMIoar4hvhE6I9CLbJZEhxk");
            login.setFailedLoginAttempts(5);
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "eAAjlw7ydO0ol3FC0LWAE84TuMIAY27vxND0HpIqM2gDrKvUebO3bOPWJtaBnNqtR2u85Alxe5tW3k1QZAniSCgs1HpzoL7pGk7YMbm095qGybnnVi1nHNzASacTTKVdKrnRJhLAgfhFRVVidLdXNB9DBZsAJx3hLi6rYqfqZMLmSMqkRhABOyIt8BrKIWcQzuzRpwTun"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "yQ3lAtzGEdfPiEaRbux5GJgoEfsZZz4QK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "5hBKS4Q9SWsJAy8CV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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

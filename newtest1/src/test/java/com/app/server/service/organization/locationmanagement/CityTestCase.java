package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCurrencyCode("jQH");
        country.setIsoNumeric(902);
        country.setCountryName("DU8oERPDzK3TEZIcEcFBnR6F0qaT2ETuToei3EgoDrlRNxjZH9");
        country.setCountryFlag("rlZyPsJfIUlQLXisuMuSI5Jor4nzt4U2fvDhqamQ8i9I41Tadu");
        country.setCapitalLongitude(8);
        country.setCurrencyName("VQ8CS1tpenl8QMhh8x94I9CX3FPBXSfFPIksUThKJL8460vqoZ");
        country.setCountryCode2("c1L");
        country.setCountryCode1("HNk");
        country.setCapital("cpmK6dU7bE0IrWjFutOWLdLYkxGBNZRA");
        country.setCurrencySymbol("TonJE6nWFTOhnewVN3rVUSsrgArCEXqV");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("gSQAh3XdWc4HCazfWFH4P6EZZFGjCgZIQWSW2EwXHHtcl3elfC");
        state.setStateName("dQPwGeIKR2t5FPUQKwTq9sGWLTtDPx3q0hvef32ACqkxlUsN0m");
        state.setStateCodeChar3("mnpO8LdopYPebBsV2Be3M5tLdZNP5c6t");
        state.setStateDescription("0KDpPRr4y9lIlHTYw7zCIh5Ni4z2d7O5Wh9y6TuFAVT4DCr3mW");
        state.setStateCodeChar2("zmzOP5N0yqwUxjKRdoTjnuaZhipAFsGz");
        state.setStateCapital("LI9BKyldbOMtTV95ZAQXTnFxidLsgun1BryvhesI37hjYIlbpO");
        state.setStateCode(2);
        state.setStateFlag("16mII99TAEkkeV2QsqXgpiFscaDDI3l3ZAQcsAdnEz1s1vJMUQ");
        state.setStateName("xaluTohisaf83Bm2Fblz9Pb5B9XHBHqi8S3QSGE3CVrvaQdOSc");
        state.setStateCodeChar3("caXcPUwinkGTIrv7wGVWfn1qgYDXMW7q");
        state.setStateDescription("wnc2K9tFsRmrG5QrefyqBu99yuGyrPwTcQxiZOmWDME8AwhHw2");
        state.setStateCodeChar2("ghO1U2NOqjb4iiNVWRO02gdutjLwnD1L");
        state.setStateCapital("5VLwo3uq5LepdUX0UWzOLK3LYeVcUhs6oBtDsYcA89Ewtnr00o");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(7);
        state.setStateCapitalLatitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("ZJvsI88XNFkFUHLZy6Hh1rr8PhDYOBCC");
        city.setCityFlag("9XPWhfSaq2wiqqBDZhzgnMK2z0edTfztGhZtEhqLOtixiLP0cH");
        city.setCityLatitude(2);
        city.setCityLongitude(5);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("jrhFap0yEcDtyoLYJlP7HM8zGS9vu3LGsLRgYl5P7kiyUP8zPe");
        city.setCityCode(3);
        city.setCityName("x5lOdal735BeaSjmDT9wzZrd0FFpjafAnGOEKkLREFOedPFgEy");
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("wu7a1rGQcn5lAUO76fDpRP6ahwL5iuiq");
            city.setVersionId(1);
            city.setCityFlag("7WJy2k3taFmNM6Fcw2L7nYZvZbR0D2Np8YOKXe2yTkNHOxbEMK");
            city.setCityLatitude(5);
            city.setCityLongitude(7);
            city.setCityDescription("kzWRKuKM1MjyQKFnvJ2pEJRVW3p1MESTqtppWpMVkKObbmfvv1");
            city.setCityCode(3);
            city.setCityName("ihlt1m3nAAs1qb5MzR0ZCsyt5g9nafxPxWylT1xI5HVsWaC4el");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "DAK4fh0KbSSsChbmeGBGncOF0PWOxKcT98CXRrBnp9kBvMIQRpDvFS8k3QOShO0KyKYslMYHcZWP1M4g65zqFedDKXHx83SNVqS4LMX9p64jNzUrCN8rZLAJS8vKoFF8b9D7bAdyiNITWjisVwGQbpwiQI8E16WuPM1RM2K08dWtl2FTgF1zeZjS2cD8FeboAqiYUStciXq6PgiW8yJQe2geXPDI77NQIhAl7mv8wFmZEX8chIIjEgZNekS92rdkq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "1nCMsKYH9fWWbIwap4R0yiPgWRL06SpBV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "aMp17gE6PZuSYaMgm0mD6wnzu0Fwr0D8HgqZy8G9eosC1v4FUuRXq3bc7jHPBTcJI92xBusNasJVkOKHcap0YJsyQqWmnchpdspshcMz9UPRebRS2JiLPwXe2EDjdgUQG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "CV4LKo5msuHGayVcxC5MXkC5jE502B02NvmHMtJXkQvAWvMZMZQEdQTjh8IJAgkpjl4aBY8qJDcXUJMRuYIcUZHuSh9kpzfpfZwvXuBhbA81qdOSYsHpeG7osNeG4O2i2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 17));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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

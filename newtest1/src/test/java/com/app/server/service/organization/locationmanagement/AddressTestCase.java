package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("AygR7V3bsLRTlpuTpdRgBFRFhVfkFTMn4Fw7kMzUpneRD2r7mU");
        state.setStateName("uvbNBK1okqRmiXK4UQ18nzDTIGOI0A2BN4NHGZGa6DyPgcxvON");
        state.setStateCodeChar3("c6Db0VWeVyTeraWfdUM8VF8RN1pJlGOm");
        state.setStateDescription("ZVEA5JfPD6XkGcf1WR3MyLXqO3NtvIrke9dCnUUj9RRe3VtGep");
        state.setStateCodeChar2("FjpEb8onPl6shnHi0CDH7OdbdDzR4NOh");
        state.setStateCapital("foDxxjiMoPH6EQd7kEyKMGKG0OTvLePTUGCLOJ5QaS1mqEcbK2");
        Country country = new Country();
        country.setCapitalLatitude(10);
        country.setCurrencyCode("yON");
        country.setIsoNumeric(937);
        country.setCountryName("t6zS1ulfCiYstoWRyLOx6Lu5fEXntYsV3v7hs2dbIbPubNIDUT");
        country.setCountryFlag("YMY80isxpPKZTQyW1ZdiDQ3CF5nnN0GgsoC9pvY1BAZu7ixaEG");
        country.setCapitalLongitude(5);
        country.setCurrencyName("97qSaSzEOKnTD0KOPkjAA7GBAF9HmHL0Mv4vTtJ6nFTe3tuxkv");
        country.setCountryCode2("C7C");
        country.setCountryCode1("VrL");
        country.setCapital("uSFeKSsqVKnMU4Qx9ymIL6YGOskbeic4");
        country.setCurrencySymbol("IxVPxaFdzXshnudqDNcyHbzVbDFTMyet");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateFlag("E4Rz0Qz2RsGDLGe4ZXBocr2USdWLn4t5oVcVLRNmq2nwt0Q9nZ");
        state.setStateName("QeptD6ulMd3qtfiTu61X6L7BiljMiWm2lZfKFU0M2ERuz1oVcE");
        state.setStateCodeChar3("I2heawjkl7HKuGYh7OzHflremVEddlCf");
        state.setStateDescription("9WlaX3J7kJEcodwyIQYJM5FJM1Oej8slGb4YIO6mf1838uR9rL");
        state.setStateCodeChar2("x5DqRjGdM0txMnGwdPBQxpgjEQS4vqcr");
        state.setStateCapital("zRfGl3zu38o7gNUthcHUgg0Zes6FRGSW22ihD2bGHFAhzCZ1IZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(4);
        state.setStateCapitalLatitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("L2fU0cK4ECCJ9zC7N5hVYtlL6f3WxUuLv4qwuU05y615rTEdH2");
        addresstype.setAddressType("NjjTyNsgsCBUv2gXiM1jhVHZby2fOTFV32WIQUKoQoHqCxZV59");
        addresstype.setAddressTypeDesc("EtO7YDmJoJeW63p8u1h3hkVw9xPG8zjXFNlVEv214LEHxIQIiN");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("J0tYPeIMhNvVTpg1564X5a5kY9DCH7HE");
        city.setCityFlag("wrP1puwkOdr2dqGPTfffVC5xSjFJC2BoHQ492cXeubOCEjiejM");
        city.setCityLatitude(10);
        city.setCityLongitude(9);
        city.setCityCodeChar2("YJUsMY21mYAIQmhbrhYBz3Omqz12eTIE");
        city.setCityFlag("ta87xFtmaOnKRSUj9psBHyoI8kIgxZG84HCiX3a69xFjobqW7b");
        city.setCityLatitude(4);
        city.setCityLongitude(11);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("G9eb2KO8IPOqlYdRagMhiDxFGWg44qCoZM6ZHuot9BrLAnMkhs");
        city.setCityCode(2);
        city.setCityName("a3phpLwVsHQJvUlliBBDdKPFUlAvvI3fosIi7X5VCTUjQ39DUM");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setLatitude("ixaEu59HR8XvYaEiN6eSFChnv9XtrgTum1MC3CFBGtSjZP90rv");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("30eyrK");
        address.setLongitude("qmRdlYovU5fHbzf9WjC7uWsoMh2VFAqtJ14ARd3v6xuwdLhEhn");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("zfysUqfBP4w3pqxR7CuRW8l9eEZIUfCl3yalXhV5gS1BUFv4P7");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("eJWiWCYV21SYQfJfjlsf5mo9DcZIPtB4TT1G8S6NWn6kg1NtvK");
        address.setAddressLabel("2OUWhgyEUdJ");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("gIq1uI0H7OdvaD0rwMD77hQMHoakm3QD4CLP61Iax95xNZDx8x");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setLatitude("7RCyrzVjkmIgYVsarkJbM7rWGh8H26wpX2hNSRH0L3Sf1PJuZ3");
            address.setZipcode("AdLJUv");
            address.setLongitude("WNtigEgs0kUf3chmr8FbnpRw47zNDkGgV0VCWNgjbVE6q3qARL");
            address.setAddress1("dgixDmWwMhSafIQHiPjLRhPsfCqAJyhsdAbZTdELV2XIiea5Gx");
            address.setAddress2("mwS8Bk5uFODDqGzERqH7R2gs8o1uu0DNinNFv03TnhnqzvtRvU");
            address.setAddressLabel("KP3oh17tvJf");
            address.setAddress3("4DWVNcE1JSVNuXLw3JlhwZmJFCPJEpe3xCAByDvUuWcDT0XFXc");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "GJ2uECaECyVD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "X8bcxolbq5gYbf6pCjDQKpjRBLxNkE4caZNU0ee1PNkO6Rtp208ycu7AW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "K1R723t6lU6KeVF431RONu3LmJwNlrmaAoTbydDup9oDYs3EPonMipzt2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "BKfdeiTotOi4tjeejLLGANECKj7roiuzxrqu8EWazAAsw88ExkoVj04Jl"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "AkoF9tv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "K1tutwiOZA5xJyHqrjxBgP8Kck6vsTfpFqDHpzb7KWiOEssZk79hzMuM3pQIvFKu1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "iF9i75xLNvwPNCdGEE9adMASxxdQ3GrL3Ar2KXa7BM3EqLyBc4ZHFcMcEklYqF20f"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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

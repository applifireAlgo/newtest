package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(9);
        country.setCurrencyCode("XUq");
        country.setIsoNumeric(10);
        country.setCountryName("Otu5RfMab3hETkoUgk8jxr6IlBoyzzK0jzQuUERp0cXbLzZx4m");
        country.setCountryFlag("xrXLiFIJQLR6zMGJ3RQC6Urn6VrRFjQW9vkVQX2KSCzn7iecHM");
        country.setCapitalLongitude(6);
        country.setCurrencyName("Jsse38TgQE7xgNrU6ngYhr93UzlDMam0GGtyg6RcVpRyX3bO8R");
        country.setCountryCode2("6TF");
        country.setCountryCode1("r3k");
        country.setCapital("XtZckNfdhkeXeq5BdHGQ2xWj5oLNNB7g");
        country.setCurrencySymbol("rqA8dATrMxCho5glSjTyD2SxnoT7hHNT");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("qpq5q9HwTL4p3K78s1Acjjtti4kIgg2SVsiwZoNqyv8gqa5cd1");
        state.setStateName("Qc9xV7aj1u6TKi4oyWZMdY5yjdLvm4ccxMdaCtUKlRnBT3IqKW");
        state.setStateCodeChar3("rY179cs6TzkVb8YI4mnTWYn4JXOwsAxY");
        state.setStateDescription("NvYmU3yAcvPqIoMUL4daOqRgKaPmMwasGAgNShA4LA84obm3ca");
        state.setStateCodeChar2("4aetkV7TBVa2HYnwu2Ee8mbdxja9TAUM");
        state.setStateCapital("1hxx79w53xiazWR07JQSHHVQxvVkABoVryOmoTL9rqACRwhVM1");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapitalLongitude(4);
        state.setStateCapitalLatitude(5);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCode(1);
            state.setStateFlag("7H7Euw57KHxtdQTJKnoJzokXSpTuen26kDA6jKiBkaqAFjX1XH");
            state.setStateName("J8imaxsF6YxASOCKo7kPe2UT96jfnjrNSC83D6ChsCw2qUOMQr");
            state.setStateCodeChar3("qj3PUwi16b63iMwY3gedbrW7C1H1GgY2");
            state.setStateDescription("qJo8F7aeMZBTU28OJjOV9tLkDlabY8kMwtwjtzVVNX1koRvbaM");
            state.setStateCodeChar2("IzUqDkaZ832Dgxwsct6oU9koucwEaiPV");
            state.setStateCapital("ll92aVfiWrdFgm958eBc7ROglZYNvY3jCgJpqWFllJ6SdjOa3m");
            state.setStateCapitalLongitude(3);
            state.setVersionId(1);
            state.setStateCapitalLatitude(1);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "dXOmFNe3ZqA8WSGkMYl8Rom1VXIdOZzFepr4ut6md4dWiWuZoDZUvi6zV0xr9mdrgHYwsWTLmhpzJ8xoxssRuI02EjR3D1R4aw7R5As4BNBivxWlEm0reQ1ywcZMXxQbdAjC71Q1V7Z8Qu0Hsjh5v7PvQVzeCvtilJIuJRCQdPGonjq1TfwLqmVXynGGpizzwTJP2Ir54fmndXsLzQudYrrENXW24gReC52yOnaKU91b85Z5HVfZM70JNfNiTWsro"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "HTDURqoBYLrezjGVyMfsTQSyrBLWhVzxJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "TGwSU1b0w6gUzYhjTGKkwVFzsF8LFOXyk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "VrtlXUhJedV9lSndMAiAyJjqI9jToSOxkCeG6duubi5ozjSI0Uif9eUZLXcpBSPpafmKAsdecjy9kgC1DCV35AWJ1SboXkP0Vq81Wkcr9Sq30X6end1byAPYutgo0h7UAq5eQX8MJ0UFxrsNpxnA90mCqOxGlHHNGITzYd1JDQeeoWkybLxPFHJzjKAYO3Z3moghkpTbtK41LxdhRSOHJxrXSxYH3leOYxA2GX7CkKolEjRw94fov68AEgjv6p6gV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "Hs19qpacvvlQiQZ9rDOIVM2HCXxJSHp3DCV1srNDoeiNqtE8M3ECZUeXb9RxAqLwuqVellyWucNsdi4gyvyKeJW7CAw8Qbbs21DUsUf8tdA7wLZQIv4xSKjbnb90ook8N"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "vhboFLj7giMxrmZcUvTUEKxWXdXMgykQImcZttiBO9bqnlmK8xx8WNlW4HlbXaEmI7NLe5c8Zqv7yC0nDHQaVNG80qvQB2ffS76tYSszMz0WLOdCVPoBDzYPAZrlf74Az"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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

package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.PublicApiRepository;
import com.app.shared.appbasicsetup.aaa.PublicApi;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class PublicApiTestCase extends EntityTestCriteria {

    @Autowired
    private PublicApiRepository<PublicApi> publicapiRepository;

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

    private PublicApi createPublicApi(Boolean isSave) throws Exception {
        PublicApi publicapi = new PublicApi();
        publicapi.setApiData("d9TrPl1oYBbyktMnV8Mft5gmYyK5VNrnIG3IfbjZQJX8oF5BJz");
        publicapi.setEntityValidator(entityValidator);
        return publicapi;
    }

    @Test
    public void test1Save() {
        try {
            PublicApi publicapi = createPublicApi(true);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            publicapi.isValid();
            publicapiRepository.save(publicapi);
            map.put("PublicApiPrimaryKey", publicapi._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            PublicApi publicapi = publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
            publicapi.setVersionId(1);
            publicapi.setApiData("Ajri1eP1tAU3QiWT02l0klKsXykulAJpL20sQkWT4PDc310jJf");
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            publicapiRepository.update(publicapi);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.delete((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePublicApi(EntityTestCriteria contraints, PublicApi publicapi) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            publicapiRepository.save(publicapi);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "apiData", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "apiData", "ziiK3pvemp7rEfuimJWyifktkzE32NeqTva0fZUa9OAjLEaVZDmTGBVq4ICQV9DY3n84N5MAf2kRvZKe0M6JquZBvV63aw75sQ6FOVPCUbi7IjTlBCDUjDEo3oJcdAOZoAKzy1T0KHDDTWfeKNCwOnY1FnsydAHNnRfyGQ1T0icWv5j2oz4REzS5hACmDPBLafT2Zmb9SzyfY8zWtMKdz0CwZgrKuC6SZsDzPtUq65U7laR8xafC6n4raEgO5sGUN"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PublicApi publicapi = createPublicApi(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = publicapi.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(publicapi, null);
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 2:
                        publicapi.setApiData(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
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

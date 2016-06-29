package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("3iRNSiD8VKuC88dLXfKNxdIlBh8SpgagvTqllf2wPJEIjLDrrC");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("audLvSMWNTVrXyFDQ4i33nTcjvfHl6bxThCRLxJPtilWdFL8aw");
        useraccessdomain.setDomainHelp("51immGhrCaBp5iBKegVieNcpN1DSTFrRDlshjz69b4xbGCJyeP");
        useraccessdomain.setDomainName("nv7QQvkXQZWCzWsC257TCVdvqdwc9BQsm4nG6zk6WqDteXrMco");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainDescription("qw4DguntarxT5y9XWXPYAmQSl57gUXz9sm3Q1HXCNbyxijeQAB");
            useraccessdomain.setUserAccessDomain(50072);
            useraccessdomain.setDomainIcon("vSIxPqgEIkwF7ZcDfkz4EuEjlDlD55oQQjb4z5tvn1a6FxtBsN");
            useraccessdomain.setDomainHelp("OqsE6DGrl5DmCr0hp5xCRZdp0N2aNHlOVavV1rnNdYtRF8U9Hd");
            useraccessdomain.setDomainName("XR17TvAIuU8tTnUWQlVbGtf66GClpjamlNLcxPAMog5HWcduIX");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 113001));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "pt4uPX098cnP8cxTRkPn4IoXZwScaTYqZGMDbjDpcNU4UMPhWQINYgqFR3vBXFNJvSiULvq7nj5SbCB81Wu0SvjOdqoxgmrgKDMTWMkbCeotJt8ftApp4CIXCQMSDrCE81rUvspWGIjYEn6EVaAViyD2eRpLscGsEKKf8hJGcxuRwx2aYOesTqdhtw543sSojjzTupyorbT49QfYPF3NdRRgULFt8N530uK3A7WnIoucOm1SoNvJPmhVXX37hwAeF"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "tdkku9u8tNvqeJXnyJsl5M8k7p2yQdXki1SdyePUUzzVde49ozw8V8v4hn4xho5c91JzLD36Rh4OIStmhmXwT4xsFpIqedT8qXpcMbdXDWnKIVLlq1L7LlJPk7UAugedQLrBZxNZXVGdsYbeTIbG4WQmr1OJgV6yHqk8x72WFVXS6crw8d83SMRKW9bcNN9bvsHyrS73y65tv5CxBQq9WSyW6vZq8EH4S5kRLJd0cZSsmKt6G68ASrNE7ZIPaZjIG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "GrM6ohWROJw6O8ievxBzPeb7XQ0NNonOVe7AMTuabGZz2bPcSqAU8Czo2jfG973vioBfUTsw0pZcabJkJGUpd2RSJ5hLe0VdqFbdHoa9oeSDcHOPU4gzCOu8ru3UpAv5yO2lkZqiDFlm36ePfeQlabpqKiKHnrsHv7LUqDKexkrkETg2bDfPzVLiWF8atUe8eVYDT90ZWJago8IHyNUdjw7X9XU1xCGOmIaKCD9hsCbPuF7PK6fkbnZjV1pGpgtx0"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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

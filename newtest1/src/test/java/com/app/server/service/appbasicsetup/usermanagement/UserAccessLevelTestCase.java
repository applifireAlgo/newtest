package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("2re0rZc8MxHn514N5fOiYbgfzKEmhroZm3m9wWW15VhgX7rdac");
        useraccesslevel.setLevelName("79CFGLYiAJWGXkBvz6sxGdqiaKeo5eanCHVQa1SMOyfnR402tm");
        useraccesslevel.setLevelIcon("2iU0bgRpUtbu0XOQMzxxMP86YnK5qnF1VAl75jOGmbNUPGnyx4");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("1HElZZ26DjPkNM5H2u9UmUzMqYP8ic7nlWlkSLosLlgDWWimhh");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("tLk8LDRBtXtWDXPYr89VB8eZtEGyAw4U1rqVGHNWY6z5dMG7O9");
            useraccesslevel.setLevelName("wVvxeVKLs7Ork9r0AOCweTZV8L6cymLCpoi2u38AY1ve68koCJ");
            useraccesslevel.setLevelIcon("9p3Q3my6Ol0QpHkTXuZTll8LASpuxZ6VnfYi9OtpA6jHgJ3kf1");
            useraccesslevel.setUserAccessLevel(29028);
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("s0JH6kYmVTyPo1p5qKanMwomOAw6KCjkhzx2CnbyJ3XlcyIx2e");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 121788));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "rri6d4sj0Erb5qb2kpoJ5zjAZoZhyuP7Bj5z8MKbWW6zffWTsuKygEUmTyC6jTorkVdoNTTQmLs4mdky77Sd7xEovYPw34flrJxMbUzJBsaoZhEoeamhXBaspDBEQoX1YOs32xGbKmMWMtixWh4HXh9CTQjfyPkybUQOmlbKZ0rwl5iYvRmYHTaoGJp8oKT7d5y4k7e4a515XfbDJPIbTuguf4qEtYL2H9lw9IxIdO0qDLemaldlFlcc8bQnLOBvH"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "hlVggDJJ0TMe9iehxX8piFptFpgKl195lNG7PaHNWEK4JI1fl95RmSNlRpIgpwk2J1R6FJTtVOyPR5IqZNJeQQMwESRXqtaEUIjy8jDdijrgayXYAIGA5KqkIu3DbXZhk4Rk3huSAdcyWRA9yWH2qfXSKh1zecXgP8SPT7EJtEIb5HdKYxX9b3Cn5r9nlMCyGovB6xu4dwUHYCLqclBDkbZrrC4KakpJ78K2HQvpxXa86FWVMIGPw3KaWuBhkjszs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "3gWFm7HoI8yzt2B1gTAaorlHgbiXNCDaNE86afZ4RhvD2VzQgBD7nYe1yUNIbyepqheUBPui789uXQyX0tAV1o3cZsnpSkDdmdOzmnLN1DcIVsqn6PBgadmlnwrPPP2rcT3CBjPjRdZ0tr8CXRGLqVOy1IWTkq91CWulAKU3KXpYFYG4MgIDxP9GuTej2bKtGagOR4bxDN7WuGonJDkZzMNjGC6SdwLHJEYq0vFkvOQrvILYuhJm5EdGepNAYo158"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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

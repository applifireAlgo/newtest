package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("xSkYgJ3LzWbSiY0ogEUFJgGqjdTTkyFAbyXQNBIZ960znc3sTx");
        roles.setRoleDescription("MLUW5gi21sSOtszCuju2JVBK7DLqb39PtGb1576vZ5gYHggRML");
        roles.setRoleIcon("CzAlLWWSmDT7gqvNfPRmpCHZbImmoJbhjTqWyl46KCslecvvUl");
        roles.setRoleName("wN3LU3MrghUTyDVvxg80EuJe5Bq03t9e2GGwPcTJcaILggfxc7");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsRead(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuDisplay(true);
        appmenus.setMenuIcon("BB6yXZybTov2Qdb9o40XuqmlqFdAeqmsyiSbMxiLZEdaGMA8ZZ");
        appmenus.setMenuTreeId("3GMdjgTPGf4niVt7kqGzNTg1SKuQVEWbCiVp866DsKGIx9TIe1");
        appmenus.setMenuCommands("jvC8EBunGyGVd5NAOGsuvknZVCYqrQ0pVXNOk8SSnyk9275fUl");
        appmenus.setMenuHead(true);
        appmenus.setMenuAction("8wWBZNv4SXvn8Qj1xhruekj5RI0XxwRnvdsYKCqASZBXNAraYH");
        appmenus.setAppId("RBevnU0KBNX5EVUQbfWJDhLWDdEPejNS6SdC790KBOsVhhKD2u");
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(7);
        appmenus.setUiType("51D");
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("iIZ8LjhHtWM5Y9Kz5yk6dbdwnyZrZFayJkRarL72q0jWrVUVvM");
        appmenus.setMenuLabel("S2xs42NIiI3RfXPVoN6yLCXuUtXJxydeoBokJGsdbqCeZop065");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsRead(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsExecute(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setVersionId(1);
            roles.setRoleHelp("ubOxfP38SjebcNAlY943qjbr1q3z3FuGTIF3geuhI3bnhVUnFd");
            roles.setRoleDescription("6E31NMEewkCpp8ASJMTiKKE0dJkYDyIpb0ex7kLJOzh4dWDTds");
            roles.setRoleIcon("dSBvtAVfw2XZ853SpmEvJBkO65R2Epr3GFcodUK2yne1mMuJm8");
            roles.setRoleName("SD1t0NmMeEYYIENgPVYnjQcaOtiAOfnOzYSJI2iW0J5A9crGio");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "Oz8aJP5TSdGv5UZh1VeXnbeoLbJK56hlPfghIMkaSjqSVVEgDxgDt4Ajl5Lm7ITpyaKJU8B8gYvUuE1zT7pKtKXYC0jSDXsaF0np5nqpdxc5iukQmBLXYwEt38UmPNtMmDOGiaLqouZ4kC7Rn3rpmp85zXxbGtPiexkUirkNtQ1wkMQ86fmWANR5fe7QSjxr3wfrVxg9MJssVUJmPVHNAooVIVtWdQfjDIaLEE32RBwmIF91yNryhPB9T19oIfG8j"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "zKlozRawdBSfPmjJPk56IiKaseZqJKYJMMznvcNEJ3bMqEDeVxSgBps5NPqWfHqnWSpgzEfzShxzdTKHerPgvyvKCVWiC7zo1p3WwmfNRdZOQlw5qj0c0cPb2vftpWJa6XMZNxyp2ouBk8D28Y09TDENCvuIZmwFLao6Vl13kVCkxR7I0sEW8q8pkgQxTCdY46saFcK68bgHog9ujIu5POXg82zTUEB1yIg242wKEFsUtfw4LHOqfCdPqlLD12qKR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "YlUOuaPSb3WTy2Ih6qo2wiLy71Km1CEGDGdIc3T2UWBHfxroE4TQZuXcW4ECRZpvR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "R4KBG8E59NwdwDCLsBkzbV8fZM5BUHGzZzHhqb7neBxAIu7EtEbuuaI22FCjmm1qWzvgqhw7M2d6R3H2MYImX9KOFJVyj5CkLD7LDAFVWAJ5BeQeNP6OQQr0NybfrKlwaXlHmhRKueoHERgFu3loMRbeTlCEpN4Jp6o2Z99xXdxWZPhzNWXtwOHZxS8UuDP4otOfThBdEeSr3oWvw4LTawp3lbeoVKbkko8UkBwpBys8EE24VhLe2DAmWWgqHoQlB"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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

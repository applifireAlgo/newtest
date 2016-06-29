package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuDisplay(true);
        appmenus.setMenuIcon("OrcFqhPhX1RVVf8MCiWFm4jMOS99UBRpLJ3mMG6WBwD3WcWKYD");
        appmenus.setMenuTreeId("WmR9LaPYeLmfR0A8lEjEwrI6MlaikpULtMze3A4WICo2z1r0Ld");
        appmenus.setMenuCommands("WfrglsKHx5QIxC0zr1VLgjFwAm4fppMJjwcAsi3VLha1vpIHYI");
        appmenus.setMenuHead(true);
        appmenus.setMenuAction("QDu0IgK0EI3Id37ycPMx2shJi43TOlzw6Wq8Zc1aK2rViJ7PjS");
        appmenus.setAppId("gJJLpP1scn2wUhczZYZ7pthdgFGXeOEC43A2hg9fN0h4tItvyF");
        appmenus.setAppType(1);
        appmenus.setMenuAccessRights(8);
        appmenus.setUiType("S0m");
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("L7bhxzZ6PDtfFNvbs6WmwjBPKxdOUWdrSIU7GzJXKeIWiwl6xd");
        appmenus.setMenuLabel("Wp9waP1xhooqwgQZtSOA61oLRkm39ddTFWu8P78v5A1QZ6nmkQ");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuIcon("9MVy7KFFgt9xYYezxygEgxUq0Exlv0a6aiFtgsTnFmXUKwxlbe");
            appmenus.setMenuTreeId("wPrk9HHeNq8SgISC33Zfemqez2YJFVKxqblTt4jGliRQUUGsXI");
            appmenus.setMenuCommands("YyZHcNmpcqrFhIGIKUyuCixgk9zo3EVC94ES79RkT2V6qswoAk");
            appmenus.setMenuAction("o1TuLa41xXr2wF6ujn7nmwHJLyM0z9GUge1F0ezMSuqLWvuroD");
            appmenus.setAppId("775agZYIbSPExXKo5XX9vvOELJrZvjkLN9fsZ17tNzJRrkRDRe");
            appmenus.setAppType(1);
            appmenus.setMenuAccessRights(8);
            appmenus.setUiType("oFx");
            appmenus.setVersionId(1);
            appmenus.setRefObjectId("hdP3i0XElDaLdHaohosF3veVGwYvbxoRM7WdWCPXAkBQ3ISspg");
            appmenus.setMenuLabel("iCSlYKjTayD7ByXjGNJJSEOkEeATISpVJXF6XaSZ4TC5uLzYzu");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "Jp2wyBiM6jtYEv9dg1p2SgENZLtc7twUheW5EBQWjAeCWSZmIWw2SPuj8TqZKSwBT3oyLtqzlLC4S7Aa4BvDlFfRF6fsZfAA9tgsXE0JEqNyb35qr9LTBaHJ7QtlibiXhaUNYOJpLrPKGrhtgCRtlQG6crzaMes2nYsKwoJ4CGYKRc2IJhtPUCgaLmzfGVK4OWlc4lULPcdGhmZtvMjzp3Gipsj69hH064INmtmzoqNOQurhsfvJPjTy71lvKiFTx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "w5TfAPPBSRuOyWcYVynOh6ZOTFDZlcyn6aGAZN0shHigr1JLBXNtbpp0b8JtuRhwCXQ2Ly0SRfDl2lkntbZMUzheL9PTkVTsWeQzAShNS9b7f4ynNqCjvpAoXnsWhjsu2KfMhNEbzEbn5TYvukQg4btcSaANt7jeFZVCs1JAW4Xg6nBnuleha4pO72TPOUVEcA1KzoYuSPclIe8pXBS3dBxFqnk83JBZWfQbOq4qfsytvlafqvSsZ327DuhX2CzKq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "2OSShH2XFfZXT26KQJxZsRers7zh6qpHtwSwH0wnOLTiET0d8VVUM10LmwZukFrzKKNTZC0E9CqDPo9f3X03B9pke2WOLDK7aOoi3YKujEV3vndJHejWgktVaVKuhccd4skPlg2933tT6Hk5CoRtrNQ52bKfeO67KUd1KkxOmvdXVAeLnlrHKN3k2O3bnEEgOa8u7rzdIAHb5VcwapytqAs0VsgscHl42icL5aib58UXyMi3v2dIiTjVaygBtGUgO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "uExu2peHHDgxcUOVsfAdnnHroJPOIE0VgrClidUlxbpPthvUR8RYhIio3Ot0jUcCq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "OxDWYU6DlZnIIb2JRupXdi7RnB85sxtw4O48Nl5HFn2xRsuYRvmpTY2GQXTPZYzeFz9Z41SAUputb3ufPDuuQ0uJrS9cbvlmua5Il2lylTbRudMZw96meCJUDPr5yyw2mfrWd1mGPRSkMJ9cmPDh0Zr88Y0Wa48bSyfhsQtw9GaNSmDZg8c8uoLoaLSu6rdPXqLUPtta6FmbipEVvOLcIlPo5N6ioGzFFK463J1wejHhTbr048njm6zQR5X9vbFX1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "mjo6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "PM70unNUJMxUPeA0PqUDfB1KmnqICdkUHMspUatBSXanJbfYfw2Vsd9acrMXSjGE7jIE6XEM0BfAqzVTNJ4WtSZMGahLY9HmSVCydOGiMgCqrsLnxOQRG7UGcu10OSCIOVSBw5ljMSjGZssejfs5vent2vSfL12y32uI52MTkHCBO1y6KMC3D0RPvyMNIGwKNhkyWkZgzOxtLfCfhyZDjaDOXPdhZwra2kBwe8XkShu9S0FwMxq2X6f4xqJyIAilf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "TMXwNx7tiT8MEcLJTHOIpAs1KKIdkl9FwxwoRmOCWaTGR78Q15hLDAYLnpCO7MSb4lRM1xBmPjlMVmz27EtodqEcXB0hU4wWtAL3Iz8vp7Xr4ECU3PwzqVMo20gvrpvY3Ohcud9obYlps0hgjATN9iYYdZgWtsgRjerW0GijhZaMJ1zMGui7pBDbeNGVvRzadOuXPXCWTYdD8sWhuRBFq55XL2PToT0rR5edjZZoucZdLbyjtDY4lRcAPRbGombQR"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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

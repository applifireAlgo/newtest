package com.app.server.repository.appbasicsetup.aaa;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appbasicsetup.aaa.JwtConfig;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for JwtConfig Master table Entity", complexity = Complexity.LOW)
public class JwtConfigRepositoryImpl extends SearchInterfaceImpl implements JwtConfigRepository<JwtConfig> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<JwtConfig> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appbasicsetup.aaa.JwtConfig> query = emanager.createQuery("select u from JwtConfig u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public JwtConfig save(JwtConfig entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<JwtConfig> save(List<JwtConfig> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appbasicsetup.aaa.JwtConfig obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appbasicsetup.aaa.JwtConfig s = emanager.find(com.app.shared.appbasicsetup.aaa.JwtConfig.class, id);
        emanager.remove(s);
        Log.out.println("ABSAA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(JwtConfig entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<JwtConfig> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appbasicsetup.aaa.JwtConfig obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public JwtConfig findById(String configId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("JwtConfig.findById");
        query.setParameter("configId", configId);
        com.app.shared.appbasicsetup.aaa.JwtConfig listOfJwtConfig = (com.app.shared.appbasicsetup.aaa.JwtConfig) query.getSingleResult();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "JwtConfigRepositoryImpl", "findById", "Total Records Fetched = " + listOfJwtConfig);
        return listOfJwtConfig;
    }
}

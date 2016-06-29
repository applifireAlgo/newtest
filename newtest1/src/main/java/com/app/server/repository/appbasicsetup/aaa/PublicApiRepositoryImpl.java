package com.app.server.repository.appbasicsetup.aaa;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appbasicsetup.aaa.PublicApi;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for PublicApi Master table Entity", complexity = Complexity.LOW)
public class PublicApiRepositoryImpl extends SearchInterfaceImpl implements PublicApiRepository<PublicApi> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<PublicApi> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appbasicsetup.aaa.PublicApi> query = emanager.createQuery("select u from PublicApi u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public PublicApi save(PublicApi entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<PublicApi> save(List<PublicApi> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appbasicsetup.aaa.PublicApi obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appbasicsetup.aaa.PublicApi s = emanager.find(com.app.shared.appbasicsetup.aaa.PublicApi.class, id);
        emanager.remove(s);
        Log.out.println("ABSAA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(PublicApi entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<PublicApi> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appbasicsetup.aaa.PublicApi obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public PublicApi findById(String apiId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("PublicApi.findById");
        query.setParameter("apiId", apiId);
        com.app.shared.appbasicsetup.aaa.PublicApi listOfPublicApi = (com.app.shared.appbasicsetup.aaa.PublicApi) query.getSingleResult();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "findById", "Total Records Fetched = " + listOfPublicApi);
        return listOfPublicApi;
    }
}

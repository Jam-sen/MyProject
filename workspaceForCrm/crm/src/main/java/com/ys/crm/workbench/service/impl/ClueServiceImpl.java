package com.ys.crm.workbench.service.impl;

import com.github.pagehelper.PageHelper;
import com.ys.crm.exception.ConvertException;
import com.ys.crm.util.DateTimeUtil;
import com.ys.crm.util.JacksonUtil;
import com.ys.crm.util.UUIDUtil;
import com.ys.crm.workbench.dao.*;
import com.ys.crm.workbench.domain.*;
import com.ys.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueDao clueDao;
    @Autowired
    private ClueActivityRelationDao clueActivityRelationDao;
    @Autowired
    private ClueRemarkDao clueRemarkDao;
    @Autowired
    private ContactsDao contactsDao;
    @Autowired
    private ContactsRemarkDao contactsRemarkDao;
    @Autowired
    private ContactsActivityRelationDao contactsActivityRelationDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerRemarkDao customerRemarkDao;
    @Autowired
    private TranDao tranDao;
    @Autowired
    private TranHistoryDao tranHistoryDao;

    @Override
    public String saveClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        int count = clueDao.saveClue(clue);
        if (count == 1) {
            return JacksonUtil.flagJson(true);
        }
        return JacksonUtil.flagJson(false);
    }

    @Override
    public Clue getDetailById(String id) {
        return clueDao.getDetailById(id);
    }

    @Override
    public String unBund(String id) {
        int conut = clueActivityRelationDao.unBund(id);
        if (conut == 1) {
            return JacksonUtil.flagJson(true);
        }
        return JacksonUtil.flagJson(false);
    }

    @Override
    @Transactional
    public String bund(String ids, String clueId) {

        String[] idArray = ids.split("&");
        int count = 0;
        for (String id :
                idArray) {
            ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setActivityId(id);
            count += clueDao.bund(clueActivityRelation);
        }
        if (count == idArray.length) {
            return JacksonUtil.flagJson(true);
        }
        return JacksonUtil.flagJson(false);
    }

    @Override
    @Transactional
    public void convert(String clueId, Tran tran, String isCreateTran, String userName) throws ConvertException {
        Clue clue = clueDao.getClueById(clueId);
        Customer customer = customerDao.getCustomerByName(clue.getCompany());
        if (customer == null) {
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(clue.getOwner());
            customer.setName(clue.getCompany());
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setCreateBy(userName);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setContactSummary(clue.getContactSummary());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setDescription(clue.getDescription());
            customer.setAddress(clue.getAddress());
            int count = customerDao.createByClue(customer);
            if (count != 1) {
                throw new ConvertException("转换失败1");
            }
        }
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setOwner(clue.getOwner());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(userName);
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setDescription(clue.getDescription());
        contacts.setAddress(clue.getAddress());
        int count = contactsDao.createByClue(contacts);
        if (count != 1) {
            throw new ConvertException("转换失败2");
        }

        List<ClueRemark> list = clueRemarkDao.getListByClueId(clueId);
        if (list != null && list.size() != 0) {
            for (ClueRemark clueRemark : list) {
                CustomerRemark customerRemark = new CustomerRemark();
                customerRemark.setId(UUIDUtil.getUUID());
                customerRemark.setCreateBy(userName);
                customerRemark.setCreateTime(DateTimeUtil.getSysTime());
                customerRemark.setEditFlag("0");
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                int count1 = customerRemarkDao.createRemark(customerRemark);
                if (count1 != 1) {
                    throw new ConvertException("转换失败3");
                }
                ContactsRemark contactsRemark = new ContactsRemark();
                contactsRemark.setId(UUIDUtil.getUUID());
                contactsRemark.setCreateBy(userName);
                contactsRemark.setCreateTime(DateTimeUtil.getSysTime());
                contactsRemark.setEditFlag("0");
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                int count2 = contactsRemarkDao.createRemark(contactsRemark);
                if (count2 !=1) {
                    throw new ConvertException("转换失败4");
                }
            }
        }

        List<ClueActivityRelation> list1 = clueActivityRelationDao.getListByClueId(clueId);
        for (ClueActivityRelation clueActivityRelation:list1) {
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
            contactsActivityRelation.setContactsId(contacts.getId());
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            int count3 = contactsActivityRelationDao.create(contactsActivityRelation);
            if (count3 != 1) {
                throw new ConvertException("转换失败5");
            }
        }

        if ("true".equals(isCreateTran)) {
            tran.setOwner(userName);
            tran.setId(UUIDUtil.getUUID());
            tran.setContactsId(contacts.getId());
            tran.setCustomerId(customer.getId());
            tran.setCreateBy(userName);
            tran.setCreateTime(DateTimeUtil.getSysTime());
            int count4 = tranDao.createByClue(tran);
            if (count4 != 1) {
                throw new ConvertException("转换失败6");
            } else {
                TranHistory tranHistory = new TranHistory();
                tranHistory.setCreateBy(userName);
                tranHistory.setCreateTime(DateTimeUtil.getSysTime());
                tranHistory.setExpectedDate(tran.getExpectedDate());
                tranHistory.setStage(tran.getStage());
                tranHistory.setMoney(tran.getMoney());
                tranHistory.setTranId(tran.getId());
                tranHistory.setId(UUIDUtil.getUUID());
                int count5 = tranHistoryDao.create(tranHistory);
                if (count5 != 1) {
                    throw new ConvertException("转换失败7");
                }
            }
        }
        int count6 = clueRemarkDao.deleteByClueId(clueId);
        if (count6 != list.size()) {
            throw new ConvertException("转换失败8");
        }
        int count7 = clueActivityRelationDao.deleteByClueId(clueId);
        if (count7 != list1.size()) {
            throw new ConvertException("转换失败9");
        }
        int count8 = clueDao.deleteById(clueId);
        if (count8 != 1) {
            throw new ConvertException("转换失败10");
        }
    }

    @Override
    @Transactional
    public Map<String, Object> pageList(String pageNum, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Clue> list = clueDao.pageList();
        int total = clueDao.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list);
        map.put("total", total);
        return map;
    }
}
package com.erp.dao;

import com.erp.pojo.Requisition;
import com.erp.pojo.StoreIssue;
import com.erp.pojo.StoreIssueDetail;
import com.erp.pojo.DailyStockRecord;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Feb 28, 2012
 * Time: 12:32:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreIssueDao {
    public List getRequisition();

    public boolean SaveStoreIssue(StoreIssue storeissue, List<StoreIssueDetail> storeissuedetail);

    public StoreIssue findById(long id);

    public List requisitionIds();

    public List issuedids();

    public StoreIssue latestStoreIssue();

    public void update(StoreIssue storeissue,List<StoreIssueDetail> storeissuedetailarray);

    public List<Long> allStoreIssue() ;

    public List<StoreIssue> searchByName(Long  n);

    public Double getDailyStockVal(Double itemcode);

    public List<DailyStockRecord> getDailyStockDate();

    public List<DailyStockRecord> getDailyStockDate(String s);

    public List<StoreIssue> searchBySiDate(String date);

    List<DailyStockRecord> getDailyStockMonth(int month, int year);

    List<DailyStockRecord> getDailyStockYear(int year);

    List getDailyStockByItemCode(String itemcodetxt,int month, int year);

    List<DailyStockRecord> getDailyStockByFromTo(String fromdate, String todate);

    public List<StoreIssue> searchBySiId(Long n);

    public List<StoreIssue> searchBySiRequisitionId(Long n);



    List getDailyStockByItemCode(String itemcodetxt);




}

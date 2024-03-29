package com.erp.dao;


import com.erp.pojo.Grn;
import com.erp.pojo.GrnDetail;
import com.erp.pojo.Item;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 21, 2012
 * Time: 11:08:15 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GrnDao {
    public boolean SaveGrn(Grn grn, List<GrnDetail> grndetail);

    public List getGrn();

    public List getTodayGrn();

    public void update(Grn grn,List<GrnDetail> grndetail);

    public Grn findById(Long id);

    public List<Long> allGrnno();

    public List<Grn> searchByName(Long n);

    public List<Grn> unverifiedGrnList();

    public List<Grn> verifiedGrnList();

    public void setVerifiedToY(Long gn);

    public List<String> getGrnPoNumberLst();

    public List<String> getGrnVendorNameLst();

    public List<String> getGrnVendorIdLst();

    public void setPaymentStatusSuccess(Long id);

    public List<Grn> searchByGrnNumber(Long n);

    public List<Grn> searchByGrnPONumber(String ponumber);

    public List<Grn> searchByGrnVendorName(String vendorname);

    public List<Grn> searchByGrnVendorId(String vendorcode);

    public List<Grn> searchByGrnDate(String sdate);

    public List<Grn> searchByGrnOrderDate(String sdate);

    public List<Grn> searchByGrnFromTo(String fromdate, String todate);
}


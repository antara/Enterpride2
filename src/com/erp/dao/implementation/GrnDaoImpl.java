package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.GrnDao;
import com.erp.pojo.*;
import com.erp.utils.BaseUtils;
import com.wideplay.warp.persist.Transactional;

import java.util.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import org.joda.time.Period;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 21, 2012
 * Time: 11:08:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrnDaoImpl extends BaseDao implements GrnDao{
    Date today=new Date();
    @Transactional
    public void setPaymentStatusSuccess(Long id) {
        int result = getSession().createQuery("UPDATE Grn SET paymentStatus='Success' WHERE id="+id+"").executeUpdate();
    }
    @Transactional
    public void setVerifiedToY(Long gn) {
        int result = getSession().createQuery("UPDATE Grn SET verified='Y' WHERE id="+gn+"").executeUpdate();
    }
    @Transactional
    public boolean SaveGrn(Grn grn, List<GrnDetail> grndetail)
    {
        try {
            GrnDetail gr=null;
            for(Iterator<GrnDetail> i=grndetail.iterator();i.hasNext();){
                gr=(GrnDetail)i.next();
                if(gr==null){
                    i.remove();
                    continue;
                }else if(gr.getAcceptedQty()==null){
                    i.remove();
                    continue;
                }
                Query strQuery = getSession().createSQLQuery("Select id from daily_stock where item_id='"+gr.getItem().getId()+"'");
                List<?> lst = strQuery.list();
                if (lst != null && lst.size() <= 0) {
                    DailyStockRecord dailystock=new DailyStockRecord();
                    dailystock.setOpenQuantity(0);
                    dailystock.setReceivedQuantity(gr.getAcceptedQty());
                    dailystock.setIssuedQuantity(0);
                    dailystock.setItem(gr.getItem());
                    dailystock.setClosingQuantity(gr.getAcceptedQty());
                    if (dailystock.getDate() == null) dailystock.setDate(new Date());
                    getSession().save(dailystock);
                }  else{
                    Query strQuerydb = getSession().createSQLQuery("Select id,closing_quantity,issued_quantity,open_quantity,received_quantity,create_date from daily_stock where item_id='"+gr.getItem().getId()+"'");
                    List<?> lst1 = strQuerydb.list();
                    Iterator idb=lst1.iterator();
                    BigInteger id=null;
                    double closing_quantity=0,issued_quantity=0,open_quantity=0,received_quantity=0;
                    String sst = null,datedb=null;
                    Date   datewithtime=null;
                    while (idb.hasNext()){
                        Object[] rowData=(Object[])idb.next();
                        id=(BigInteger)rowData[0];
                        closing_quantity=(Double)rowData[1];
                        issued_quantity=(Double)rowData[2];
                        open_quantity=(Double)rowData[3];
                        received_quantity=(Double)rowData[4];
                        datedb = (rowData[5].toString()).substring(0,10);
                        datewithtime=(Date)rowData[5] ;
                    }
                    if(BaseUtils.getCurrentTimestamp().equals(datedb)){
                        String idstg=id.toString();
                        long id1ong=Long.parseLong(idstg);
                        DailyStockRecord dailystock = (DailyStockRecord) getSession().get(DailyStockRecord.class, id1ong);
                        dailystock.setOpenQuantity(open_quantity);
                        dailystock.setReceivedQuantity(received_quantity+gr.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(gr.getItem());
                        dailystock.setClosingQuantity(open_quantity+received_quantity+gr.getAcceptedQty());
                        if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().update(dailystock);
                    }
                    else{
                        Date convertedDate = new Date();
                        Calendar cal1 =GregorianCalendar.getInstance();
                        cal1.setTime(convertedDate);
                        Calendar cal2 = GregorianCalendar.getInstance();
                        cal2.setTime(datewithtime);
                        int r=daysBetween(cal2.getTime(),cal1.getTime());
                        System.out.println("diff "+r);
                        for(int e=1;e<r;e++){
                            DailyStockRecord dailystock=new DailyStockRecord();
                            dailystock.setOpenQuantity(closing_quantity);
                            dailystock.setReceivedQuantity(0);
                            dailystock.setIssuedQuantity(0);
                            dailystock.setItem(gr.getItem());
                            dailystock.setClosingQuantity(closing_quantity);
                            cal2.add(Calendar.DATE, 1);
                            System.out.println("Daysaf= "+cal2);
                            dailystock.setDate(cal2.getTime());
                            getSession().save(dailystock);
                        }
                        DailyStockRecord dailystock=new DailyStockRecord();
                        dailystock.setOpenQuantity(closing_quantity);
                        dailystock.setReceivedQuantity(gr.getAcceptedQty());
                        dailystock.setIssuedQuantity(0);
                        dailystock.setItem(gr.getItem());
                        dailystock.setClosingQuantity(closing_quantity+gr.getAcceptedQty());
                        if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);
                    }
                }
            }
            if(grn!=null){
                if (grn.getCreateDate() == null) grn.setCreateDate(new Date());
                Long q=(Long)getSession().createQuery("select max(id) from Grn").uniqueResult();
                if(q==null)
                {
                    grn.setId(1000L);
                }
                else
                {
                    grn.setId(q+1);
                }
                grn.setGrndetailarray(grndetail);
                getSession().save(grn);
                if(grn.getStatus().equals("close")){
                    grn.setVerified("N");
                    PurchaseOrder purchase = (PurchaseOrder) getSession().get(PurchaseOrder.class, grn.getPurchaseOrder().getId());
                    purchase.setStatus("close");
                    getSession().update(purchase);
                }
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
//get all list

    public List getGrn(){
        return getSession().createQuery("From Grn").list();
    }

    public List getTodayGrn()  {
        return getSession().createQuery("from Grn where createDate like '"+ BaseUtils.getCurrentTimestamp()+"%'").list();
    }

    public Grn findById(Long id){
        return (Grn)getSession().createCriteria(Grn.class).add(Restrictions.eq("id",id)).uniqueResult();
    }
    @Transactional
    public void update(Grn grn,List<GrnDetail> grndetail)  {
        try{
            for(Iterator<GrnDetail> i=grndetail.iterator();i.hasNext();){
                GrnDetail g=(GrnDetail)i.next();
                if(g==null){
                    i.remove();
                    continue;
                }else if(g.getAcceptedQty()==null){
                    i.remove();
                    continue;
                }
                Query strQuery = getSession().createSQLQuery("Select id from daily_stock where item_id='"+g.getItem().getId()+"'");
                List<?> lst = strQuery.list();
                if (lst != null && lst.size() <= 0) {
                    DailyStockRecord dailystock=new DailyStockRecord();
                    dailystock.setOpenQuantity(0);
                    dailystock.setReceivedQuantity(g.getAcceptedQty());
                    dailystock.setIssuedQuantity(0);
                    dailystock.setItem(g.getItem());
                    dailystock.setClosingQuantity(g.getAcceptedQty());
                    if (dailystock.getDate() == null) dailystock.setDate(new Date());
                    getSession().save(dailystock);
                }  else{
                    Query strQuerydb = getSession().createSQLQuery("Select id,closing_quantity,issued_quantity,open_quantity,received_quantity,create_date from daily_stock where item_id='"+g.getItem().getId()+"'");
                    List<?> lst1 = strQuerydb.list();
                    Iterator idb=lst1.iterator();
                    BigInteger id=null;
                    double closing_quantity=0,issued_quantity=0,open_quantity=0,received_quantity=0;
                    String sst = null,datedb=null;
                    while (idb.hasNext()){
                        Object[] rowData=(Object[])idb.next();
                        id=(BigInteger)rowData[0];
                        closing_quantity=(Double)rowData[1];
                        issued_quantity=(Double)rowData[2];
                        open_quantity=(Double)rowData[3];
                        received_quantity=(Double)rowData[4];
                        datedb = (rowData[5].toString()).substring(0,10);
                    }
                    if(BaseUtils.getCurrentTimestamp().equals(datedb)){
                        Grn strQuerydb1 =(Grn) getSession().createQuery("from Grn where id='"+grn.getId()+"'").uniqueResult();
                        GrnDetail valuefrmdb=null;
                        for(Iterator<GrnDetail> iterate=strQuerydb1.getGrndetailarray().iterator();iterate.hasNext();){
                            valuefrmdb=(GrnDetail)iterate.next();
                        }
                        String idstg=id.toString();
                        long id1ong=Long.parseLong(idstg);
                        DailyStockRecord dailystock = (DailyStockRecord) getSession().get(DailyStockRecord.class, id1ong);
                        dailystock.setOpenQuantity(open_quantity);
                        dailystock.setReceivedQuantity((received_quantity+g.getAcceptedQty())-valuefrmdb.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(g.getItem());
                        dailystock.setClosingQuantity((open_quantity+received_quantity+g.getAcceptedQty())-valuefrmdb.getAcceptedQty());
                        if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().update(dailystock);
                    }else{
                        DailyStockRecord dailystock=new DailyStockRecord();
                        dailystock.setOpenQuantity(closing_quantity);
                        dailystock.setReceivedQuantity(g.getAcceptedQty());
                        dailystock.setIssuedQuantity(issued_quantity);
                        dailystock.setItem(g.getItem());
                        dailystock.setClosingQuantity(closing_quantity+g.getAcceptedQty());
                        if (dailystock.getDate() == null) dailystock.setDate(new Date());
                        getSession().save(dailystock);
                    }
                }
            }
            if (grn.getCreateDate() == null) grn.setCreateDate(new Date());
            grn.setGrndetailarray(grndetail);
            getSession().merge(grn);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public List<Long> allGrnno() {
        return getSession().createQuery("Select id from Grn").list();
    }

    public List<Grn> searchByName(Long n) {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE id='"+n+"'").list();
    }


    public List<Grn> unverifiedGrnList() {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE verified='N' and status='close'").list();
    }


    public List<Grn> verifiedGrnList() {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE verified='Y'  and paymentStatus='Requested'").list();
    }


    public List<Grn> searchByGrnFromTo(String fromdate, String todate)
    {
        fromdate=fromdate.replace("/","-");
        todate=todate.replace("/","-");
        try{
            DateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fdate = (Date)formatter.parse(fromdate);
            Date tdate = (Date)formatter.parse(todate);
            fromdate = formatter.format(fdate);
            todate = formatter.format(tdate);
        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }
        return (List<Grn>)getSession().createQuery("from Grn WHERE createDate BETWEEN '"+fromdate+"%' AND '"+todate+"%'").list();
    }

    public List<Grn> searchByGrnOrderDate(String sdate){
        sdate=sdate.replace("/","-");
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date)formatter.parse(sdate);
            sdate = formatter.format(date);
        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }
        return getSession().createQuery("FROM Grn g WHERE g.orderDate LIKE '"+sdate+"%'").list();
    }

    public List<Grn> searchByGrnDate(String sdate){
        sdate=sdate.replace("/","-");
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date)formatter.parse(sdate);
            sdate = formatter.format(date);
        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }
        return getSession().createQuery("FROM Grn g WHERE g.createDate LIKE '"+sdate+"%'").list();
    }

    public List<Grn> searchByGrnVendorName(String vendorname){
        return getSession().createQuery("FROM Grn g WHERE g.purchaseOrder.vendor.name='"+vendorname+"'").list();
    }

    public List<Grn> searchByGrnVendorId(String vendorcode){
        return getSession().createQuery("FROM Grn g WHERE g.purchaseOrder.vendor.vendorCode='"+vendorcode+"'").list();
    }

    public List<Grn> searchByGrnPONumber(String ponumber) {
        return getSession().createQuery("FROM Grn g WHERE g.purchaseOrder.purchaseOrderNo='"+ponumber+"'").list();
    }

    public List<String> getGrnPoNumberLst()
    {
        return getSession().createQuery("Select DISTINCT g.purchaseOrder.purchaseOrderNo from Grn g where deleted='1' ").list();
    }
    public List<String> getGrnVendorNameLst() {
        return getSession().createQuery("Select DISTINCT g.purchaseOrder.vendor.name from Grn g where deleted='1' ").list();
    }

    public List<String> getGrnVendorIdLst() {
        return getSession().createQuery("Select DISTINCT g.purchaseOrder.vendor.vendorCode from Grn g where deleted='1' ").list();
    }
    public List<Grn> searchByGrnNumber(Long n) {
        return (List<Grn>)getSession().createQuery("FROM Grn WHERE id='"+n+"'").list();
    }
    public int daysBetween(Date d1, Date d2){
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }


}

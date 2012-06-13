package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.PurchaseOrderDao;
import com.erp.pojo.PurchaseOrder;
import com.erp.pojo.PurchaseOrderDetail;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.hibernate.Query;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: Mar 1, 2012
 * Time: 12:18:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrderDaoImpl extends BaseDao implements PurchaseOrderDao  {

    @Transactional
    public boolean SavePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchasedetail){
        try {
            PurchaseOrderDetail g=null;
            for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
                g=(PurchaseOrderDetail)i.next();
                if(g==null){
                    i.remove();
                    continue;
                }else if(g.getOrderedQty()==null){
                    i.remove();
                    continue;
                }

            }
            if(purchaseOrder!=null){
                if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
                Query q=getSession().createQuery("select max(id) from PurchaseOrder");
                List list=q.list();
                String a=null;
                if(list.contains(null))
                {
                    a="po1000";
                }
                else
                {
                    PurchaseOrder ponumber=(PurchaseOrder)getSession().createQuery("from PurchaseOrder where id=(select  max(id) from PurchaseOrder)").uniqueResult();
                    String orderno=ponumber.getPurchaseOrderNo();
                    int i=Integer.parseInt(orderno.substring(2));
                    i++;
                    a="po"+i;
                }
                purchaseOrder.setGeneratedPO(0);
                purchaseOrder.setPurchaseOrderNo(a);
                purchaseOrder.setPurchasedetailarray(purchasedetail);
                getSession().save(purchaseOrder);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<PurchaseOrder> getPurchaseOrder(){
        return (List<PurchaseOrder>)getSession().createQuery("from PurchaseOrder where generatedPO=0").list();
    }
    @Transactional
    public void update(PurchaseOrder purchaseOrder,List<PurchaseOrderDetail> purchasedetail){
        PurchaseOrderDetail g=null;
        try{
            for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
                g =(PurchaseOrderDetail)i.next();
                if(g==null){
                    i.remove();

                }else if(g.getOrderedQty()==null){
                    i.remove();
                }
            }
            purchaseOrder.setGeneratedPO(0);
            if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
            purchaseOrder.setPurchasedetailarray(purchasedetail);
            getSession().merge(purchaseOrder);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public PurchaseOrder findById(Long id){
        return (PurchaseOrder)getSession().createQuery("from PurchaseOrder where id='"+id+"'").uniqueResult();
    }
    @Transactional
    public void deletePO( PurchaseOrder purchaseOrder)  {
        try{
            purchaseOrder.setGeneratedPO(1);
            getSession().update(purchaseOrder);

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public PurchaseOrder latestPurchaseOrder() {
        return (PurchaseOrder)getSession().createQuery("from PurchaseOrder where id=(select max(id) from PurchaseOrder)").uniqueResult();

    }
    @Transactional
    public void generatePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchasedetail) {
        try{
            for(Iterator<PurchaseOrderDetail> i=purchasedetail.iterator();i.hasNext();){
                PurchaseOrderDetail g=(PurchaseOrderDetail)i.next();

                if(g==null){
                    i.remove();

                }else if(g.getOrderedQty()==null){
                    i.remove();
                }
            }
            purchaseOrder.setGeneratedPO(1);
            if (purchaseOrder.getCreateDate() == null) purchaseOrder.setCreateDate(new Date());
            purchaseOrder.setPurchasedetailarray(purchasedetail);
            getSession().update(purchaseOrder);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public List<String> getAllPurchaseOrderNo() {
        return getSession().createSQLQuery("Select purchase_order_no from purchase_order").list();
    }
    public PurchaseOrder findByLastUpdate() {
        return (PurchaseOrder)getSession().createQuery("from PurchaseOrder order by id desc").uniqueResult();
    }

    public List<PurchaseOrder> getPoByStatus(){
        return (List<PurchaseOrder>)getSession().createQuery("from PurchaseOrder where status='open' and generatedPO='1' and deleted='1'").list();
    }

    public List<String> allPOnoForSearch() {
        return getSession().createQuery("Select id from PurchaseOrder").list();
    }
    public List<PurchaseOrder> searchByName(String  n){
        return getSession().createQuery("FROM PurchaseOrder WHERE purchaseOrderNo='"+n+"'").list();
    }

    public List<PurchaseOrder> searchByPoVendorName(String vendorname){
        return getSession().createQuery("FROM PurchaseOrder p WHERE p.vendor.name='"+vendorname+"'").list();
    }
    public List<PurchaseOrder> searchByPoVendorId(String vendorcode){
        return getSession().createQuery("FROM PurchaseOrder p WHERE p.vendor.vendorCode='"+vendorcode+"'").list();
    }

    public List<PurchaseOrder> searchByPoDate(String sdate){
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
        return getSession().createQuery("FROM PurchaseOrder p WHERE p.createDate LIKE '"+sdate+"%'").list();
    }

    public List<PurchaseOrder> searchByFromTo(String fromdate, String todate)
    {
        fromdate=fromdate.replace("/","-");
        todate=todate.replace("/","-");
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fdate = (Date)formatter.parse(fromdate);
            Date tdate = (Date)formatter.parse(todate);
            fromdate = formatter.format(fdate);
            todate = formatter.format(tdate);

        }
        catch (ParseException e)
        {
            System.out.println("Exception :"+e);
        }

        return (List<PurchaseOrder>)getSession().createQuery("from PurchaseOrder WHERE createDate BETWEEN '"+fromdate+"%' AND '"+todate+"%'").list();
    }

    public List<String> getPoVendorNameLst() {
        return getSession().createQuery("Select DISTINCT p.vendor.name from PurchaseOrder p where deleted='1' ").list();
    }
    public List<String> getPoVendorIdLst() {
        return getSession().createQuery("Select DISTINCT p.vendor.vendorCode from PurchaseOrder p where deleted='1' ").list();
    }
}

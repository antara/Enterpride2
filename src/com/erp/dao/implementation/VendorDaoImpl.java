package com.erp.dao.implementation;

import org.hibernate.Transaction;

import com.erp.pojo.Vendor;
import com.erp.dao.BaseDao;
import com.erp.dao.VendorDao;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 17, 2012
 * Time: 3:38:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class VendorDaoImpl extends BaseDao implements VendorDao{
    @Transactional
    public boolean SaveVendor(Vendor vendor)
    {
        try {
            if(vendor!=null){
                if (vendor.getCreateDate() == null) vendor.setCreateDate(new Date());
                String subname="V";
                String sname= ((String) getSession().createQuery("SELECT max(vendorCode) FROM Vendor WHERE vendorCode like '"+subname+"%'").uniqueResult());
                if(sname==null)
                    vendor.setVendorCode(subname+1000);
                else{
                    long l=Long.parseLong(sname.substring(1));
                    System.out.println(l);
                    l=l+1;
                    vendor.setVendorCode(subname+l);
                }
                getSession().save(vendor);

                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    //delete
    @Transactional
    public void delete(Vendor vendor)  {
        try{

            vendor.setDeleted(0);
            getSession().update(vendor);



        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  getSession().close();
        }
    }
    public Vendor findById(Long id){
        return (Vendor)getSession().createQuery("from Vendor where id='"+id+"'").uniqueResult();
    }

    public List getVendor(){
        return getSession().createQuery("from Vendor where deleted='1'").list();
    }
    //update
    @Transactional
    public void update(Vendor vendor)  {
        try{
            getSession().update(vendor);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Vendor> findByName(String fname) {
        return (List<Vendor>)getSession().createQuery("from Vendor where name='"+fname+"'").list();
    }

    public List<String> allVendor() {
        return getSession().createQuery("Select name from Vendor where deleted='1'").list();
    }

    public List<Vendor> searchByName(String name) {
        return (List<Vendor>)getSession().createQuery("FROM Vendor WHERE name='"+name+"'").list();
    }


    public List<Vendor> searchByVendorId(String vendorid) {
        return (List<Vendor>)getSession().createQuery("FROM Vendor WHERE vendorCode='"+vendorid+"'").list();
    }

    public List<Vendor> searchByVendorProduct(String vendorproduct) {
        return (List<Vendor>)getSession().createQuery("FROM Vendor WHERE productSupplied='"+vendorproduct+"'").list();
    }

    public List<String> vendorIdLst() {
        return getSession().createQuery("select vendorCode from Vendor where deleted='1'").list();
    }
    public List<String> vendorProductlst() {
        return getSession().createQuery("select productSupplied from Vendor where deleted='1'").list();
    }

}



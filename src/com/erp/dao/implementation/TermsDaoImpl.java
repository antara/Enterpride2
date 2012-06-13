package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.TermDao;
import com.erp.pojo.Terms;
import com.erp.pojo.Item;
import com.wideplay.warp.persist.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Apr 25, 2012
 * Time: 1:15:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class TermsDaoImpl extends BaseDao implements TermDao{
    public Terms findByMaxId() {
         return (Terms)getSession().createQuery("from Terms where id=(select max(id) from Terms)").uniqueResult();
    }
     public Terms findById(Long id) {
         return (Terms)getSession().createQuery("from Terms where purchaseId='"+id+"'").uniqueResult();
    }
     @Transactional
    public boolean SaveTerm(Terms terms) {
       try {
            if(terms.getPurchaseId()!=null){

                getSession().save(terms);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

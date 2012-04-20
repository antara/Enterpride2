package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.GrnDetailDao;
import com.erp.pojo.GrnDetail;
import com.wideplay.warp.persist.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 21, 2012
 * Time: 11:12:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrnDetailDaoImpl extends BaseDao implements GrnDetailDao{

    @Transactional
    public boolean SaveGrnDetail(GrnDetail grndetail){
      try {

            
            if(grndetail!=null){
               getSession().save(grndetail);
                
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
          //  getSession().close();
        }   

    }
}

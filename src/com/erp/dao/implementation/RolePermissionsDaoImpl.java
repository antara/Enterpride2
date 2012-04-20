package com.erp.dao.implementation;

import com.erp.dao.BaseDao;
import com.erp.dao.RolePermissionsDao;
import com.erp.pojo.RolePermissions;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Mar 2, 2012
 * Time: 11:54:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class RolePermissionsDaoImpl extends BaseDao implements RolePermissionsDao {

    @Transactional
    public void saveRolePermissions(List<RolePermissions> rolePermissions) {
        try {
            
            for(Iterator<RolePermissions> i= rolePermissions.iterator();i.hasNext();){
                getSession().save(i.next());
            }
            

        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  s.close();
        }
    }

    //get all list
    @Transactional
    public List getRolePermissions(){
        String hql="from RolePermissions where deleted='1' and name <> 'superadmin'";
// List list=null;
        try {
            
        }catch (Exception e){
            e.printStackTrace();

        }finally{
//  s.close();
        }
        return getSession().createQuery(hql).list();
    }
}

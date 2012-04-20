package com.erp.dao;

import com.erp.pojo.RolePermissions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Mar 2, 2012
 * Time: 11:54:03 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RolePermissionsDao {
   public List getRolePermissions();
        public void saveRolePermissions(List<RolePermissions> rolePermissions);
}

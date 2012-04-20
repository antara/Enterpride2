package com.erp.action;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;

import java.util.List;
import java.util.Iterator;

import com.erp.pojo.User;
import com.erp.pojo.Role;
import com.erp.pojo.RolePermissions;
import com.erp.pojo.UserPermissions;
import com.erp.constants.PermissionConstants;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: Feb 20, 2012
 * Time: 3:33:43 PM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class UserActionBean extends BaseActionBean{
    private List<User> userlst;
      private Role role;
     private String addUserName;
    private  boolean flag;

      private List<Role> rolelst;
   private List<RolePermissions> rolePermission;
    private List<UserPermissions> userPermission;

    public List<UserPermissions> getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(List<UserPermissions> userPermission) {
        this.userPermission = userPermission;
    }

    public List<User> getUserlst() {
        return userlst;
    }
    public void setUserlst(List<User> userlst) {
           this.userlst = userlst;

       }

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Role getRole() {
        if(id!=0)
              return roledao.findById(id);
         return null;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRolelst() {
        return rolelst;
    }

    public void setRolelst(List<Role> rolelst) {
        this.rolelst = rolelst;
    }

    public List<RolePermissions> getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(List<RolePermissions> rolePermission) {
        this.rolePermission = rolePermission;
    }

    @RolesAllowed({PermissionConstants.ADD_USERS})
    @DefaultHandler
    //Redirect to add user page
    public Resolution addUserLink(){
        userlst=userDao.getUser();
                 rolelst=roledao.getRole();

        return new ForwardResolution("jsp/addUser.jsp");
   }
    @RolesAllowed({PermissionConstants.ADD_USERS})
    //Save user
     public Resolution addUser(){
         userDao.SaveUser(getUser());
          rolelst=roledao.getRole();

       return new RedirectResolution("jsp/addUser.jsp");
   }

    @RolesAllowed({PermissionConstants.UPDATE_USERS})
    //Redirect to update user page
 public Resolution updateUserLink(){
     userlst=userDao.getUser();
        return new ForwardResolution("jsp/updateUser.jsp");
   }
    //Update user
     @RolesAllowed({PermissionConstants.UPDATE_USERS})
  public Resolution updateUser()
  {
        userDao.update(getUser());
            userlst=userDao.getUser();
        return new RedirectResolution("jsp/updateUser.jsp");

  }
    //get user list by id
     public Resolution userlist(){

         user= userDao.findById(id);
          rolelst=roledao.getRole();
         userlst=userDao.getUser();
         return new ForwardResolution("jsp/updateUser.jsp");
    }

    @RolesAllowed({PermissionConstants.DELETE_USERS})
    //redirect to delete page
     public Resolution deleteUserLink(){
            userlst=userDao.getUser();

      return new ForwardResolution("jsp/deleteUser.jsp");
   }
    @RolesAllowed({PermissionConstants.DELETE_USERS})
    //delete user
    public Resolution delete(){

        userDao.delete(getUser());
                 userlst=userDao.getUser();
        return new ForwardResolution("jsp/deleteUser.jsp");
    }

    @RolesAllowed({PermissionConstants.ROLE_PERMISSIONS})
    //Redirect to user permission page
     public Resolution rolePermissionLink(){
         rolelst=roledao.getRole();
          return new ForwardResolution("jsp/rolePermission.jsp");
     }

    @RolesAllowed({PermissionConstants.USER_PERMISSIONS})
    //Redirect to user permission page
     public Resolution userPermissionLink(){
         userlst=userDao.getUser();
          return new ForwardResolution("jsp/userPermission.jsp");
     }
     @RolesAllowed({PermissionConstants.ROLE_PERMISSIONS})
    public Resolution grantRolePermission(){
/*
         for(Iterator<RolePermissions> revoke=rolePermission.iterator();revoke.hasNext();){
             RolePermissions temp=revoke.next();
             if(temp.getAdd()==null && temp.getUpdate()==null && temp.getDelete()==null){
                 revoke.remove();
             }

         }
*/

         
        Role temp=roledao.findById(id);
        temp.setRolePermissions(rolePermission);
        roledao.SaveRole(temp);
        rolelst=roledao.getRole();
        return new RedirectResolution("jsp/rolePermission.jsp");
    }
    @RolesAllowed({PermissionConstants.USER_PERMISSIONS})
    public Resolution grantUserPermission(){
/*
        for(Iterator<UserPermissions> revoke=userPermission.iterator();revoke.hasNext();){
            UserPermissions temp=revoke.next();
            if(temp.getAdd()==null && temp.getUpdate()==null && temp.getDelete()==null){
                revoke.remove();
            }

        }
*/

        User temp=userDao.findById(id);
        temp.setUserPermissions(userPermission);
        userDao.SaveUser(temp);
        userlst=userDao.getUser();
        return new RedirectResolution("jsp/userPermission.jsp");
    }

    
    public Resolution getRolePermissionsById(){
        rolePermission=roledao.findById(id).getRolePermissions();
        rolelst=roledao.getRole();
        return new ForwardResolution("jsp/rolePermission.jsp");
    }

    public Resolution getUserPermissionsById(){
        userPermission=userDao.findById(id).getUserPermissions();
        userlst=userDao.getUser();
        return new ForwardResolution("jsp/userPermission.jsp");
    }

    public Resolution checkUserAlreadyPresent()
       {
          
             flag=userDao.checkUserPresent(addUserName);
           return new JavaScriptResolution(flag);
       }
    

    
    
}

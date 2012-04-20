package com.erp.action;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import com.erp.pojo.Vendor;
import com.erp.constants.PermissionConstants;

import java.util.List;
import java.util.Iterator;

import org.apache.log4j.Logger;

import javax.annotation.security.RolesAllowed;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 17, 2012
 * Time: 3:37:57 PM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class VendorActionBean extends BaseActionBean{
    public static Logger logger=Logger.getLogger(VendorActionBean.class);

        List<Vendor> vendorlst=null;
    private Vendor vendor;
     public static List<String> vendorhlst=null;

    protected String search;


    private String q;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Vendor getVendor() {
         if(id != 0) {

               return vendordao.findById(id);
          }
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Vendor> getVendorlst() {
        return vendorlst;
    }

    public void setVendorlst(List<Vendor> vendorlst) {
        this.vendorlst = vendorlst;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @RolesAllowed({PermissionConstants.DELETE_VENDOR})
    @DefaultHandler
    public Resolution deleteVendorLink(){
        logger.info("vendor list");
         vendorlst=vendordao.getVendor();


        return new ForwardResolution("jsp/deleteVendor.jsp");
   }
     @RolesAllowed({PermissionConstants.DELETE_VENDOR})
    //Delete vendor
     public Resolution delete(){
        vendordao.delete(getVendor());
                 vendorlst=vendordao.getVendor();
        logger.info("deleting vendor: "+getVendor().getName());
     return new ForwardResolution("jsp/deleteVendor.jsp");
    }

    @RolesAllowed({PermissionConstants.ADD_VENDOR})
      //redirect to add vendor page
     public Resolution addVendorLink(){

     return new ForwardResolution("jsp/addVendor.jsp");
    }
     @RolesAllowed({PermissionConstants.ADD_VENDOR})
     //Add a item
     public Resolution addVendor(){

        vendordao.SaveVendor(getVendor());

       return new RedirectResolution(getContext().getSourcePage());
   }

    @RolesAllowed({PermissionConstants.UPDATE_VENDOR})
    //update vendor link
     public Resolution UpdateVendorLink(){

         vendorlst=vendordao.getVendor();

           return new ForwardResolution("jsp/updateVendorpage.jsp");
   }
     @RolesAllowed({PermissionConstants.UPDATE_VENDOR})
    public Resolution updateVendor()
    {

        vendor=vendordao.findById(getId());
          vendorlst=vendordao.getVendor();
        return new ForwardResolution("jsp/updateVendorpage.jsp");

    }
     @RolesAllowed({PermissionConstants.UPDATE_VENDOR})
    public Resolution update()
    {

        vendordao.update(getVendor());
        vendorlst=vendordao.getVendor();
        return new RedirectResolution(getContext().getSourcePage());
    }
    public Resolution historyVendorLink()
    {
        return new ForwardResolution("jsp/historyVendor_2.jsp");
    }
    public Resolution history()
    {

        vendorlst= vendordao.findByName(getVendor().getName());
        Iterator it=vendorlst.iterator();
          
       return new ForwardResolution(getContext().getSourcePage());
    }
    public Resolution autovendor()
    {
        // StringBuilder result = new StringBuilder();
             vendorhlst=vendordao.allVendor();

         return new JavaScriptResolution(vendorhlst);
    }

}

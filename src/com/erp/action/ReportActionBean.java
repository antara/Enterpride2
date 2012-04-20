package com.erp.action;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.ajax.JavaScriptResolution;
import net.sourceforge.ajaxtags.helpers.JavaScript;
import com.erp.pojo.DailyStockRecord;
import com.erp.constants.PermissionConstants;

import javax.annotation.security.RolesAllowed;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
/**
 * Created by IntelliJ IDEA.
 * User: Milind
 * Date: Mar 28, 2012
 * Time: 11:57:48 AM
 * To change this template use File | Settings | File Templates.
 */
@HttpCache(allow = false)
public class ReportActionBean extends BaseActionBean{
    private String stock;
    private String search;
    private String daily;
    private int month,year;
    private String fromdate,todate;
    private int y;
    private String itemcodetxt;
    private String sdate;
    private List<DailyStockRecord> dailystocktoday;
    private List dailystock;
    private List<String> itemcodelst;

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getItemcodetxt() {
        return itemcodetxt;
    }

    public void setItemcodetxt(String itemcodetxt) {
        this.itemcodetxt = itemcodetxt;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMonth() {
        
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public List getDailystock() {
        return dailystock;
    }

    public void setDailystock(List dailystock) {
        this.dailystock = dailystock;
    }

    public List<DailyStockRecord> getDailystocktoday() {
        return dailystocktoday;
    }

    public void setDailystocktoday(List<DailyStockRecord> dailystocktoday) {
        this.dailystocktoday = dailystocktoday;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStock() {
        System.out.println(""+stock);
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public List<String> getItemcodelst() {
        return itemcodelst;
    }

    public void setItemcodelst(List<String> itemcodelst) {
        this.itemcodelst = itemcodelst;
    }
     @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
    @DefaultHandler
    public Resolution reportLink()
    {
       
        return new ForwardResolution("jsp/report.jsp");
    }
    public Resolution getItemCodeLst()
    {
        itemcodelst=itemdao.getItemCodelst();

        return new JavaScriptResolution(itemcodelst);
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
    public Resolution byToday()
    {


        dailystocktoday=storeissuedao.getDailyStockDate();
         stock="byItem";
          search="byDaily";
        return new ForwardResolution("jsp/report.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
public Resolution bySelectDate() {
        sdate=getSdate().replace("/","-");
        try{
            DateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date)formatter.parse(sdate);
            sdate = formatter.format(date);

            }
        catch (ParseException e)
            {
                System.out.println("Exception :"+e);
            }
        dailystocktoday=storeissuedao.getDailyStockDate(sdate);
        return new ForwardResolution("jsp/report.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
    public Resolution byMonth() {

        dailystocktoday=storeissuedao.getDailyStockMonth(getMonth(),getYear());
        
         return new ForwardResolution("jsp/report.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
 public Resolution byYear() {

        dailystocktoday=storeissuedao.getDailyStockYear(getY());
         Iterator it=dailystocktoday.iterator();

        return new ForwardResolution("jsp/report.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
public Resolution byDailyLedger() {
        dailystock=storeissuedao.getDailyStockByItemCode(getItemcodetxt());

        return new ForwardResolution("jsp/report.jsp");
    }
    @RolesAllowed({PermissionConstants.ADD_REPORT,PermissionConstants.UPDATE_REPORT,PermissionConstants.DELETE_REPORT})
    public Resolution byFromTo() {

        fromdate=getFromdate().replace("/","-");
        todate=getTodate().replace("/","-");
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
        dailystocktoday=storeissuedao.getDailyStockByFromTo(fromdate,todate);

        return new ForwardResolution("jsp/report.jsp");
    }
}

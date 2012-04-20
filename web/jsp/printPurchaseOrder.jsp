<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/print.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>
 <table width="90%" border="1" cellpadding="0" cellspacing="0" align="center" class="borderstyle"> <tr><td>
<table width="100%" border="0" align="center">
  <tr>
    <td><table width="100%">
  <tr>
    <td align="center" class="pageheading" >
Enterpride
 </td>
  </tr>
  <tr>
    <td align="center" class="sub_heading" >
Purchase Order</td>
  </tr>


</table>
<s:form beanclass="com.erp.action.PurchaseOrderActionBean">
</td>
  </tr>
  <tr>
    <td><table width="100%" >
  <tr>
    <td width="9%" align="right" class="pageText">To</td>
    <td width="30%">&nbsp;</td>
    <td width="31%" align="right" class="pageText">Purchase Order No : </td>
    <td width="30%" align="left" class="pageText">${actionBean.purchaseOrder.purchaseOrderNo}</td>
  </tr>
  <tr>
    <td width="9%">&nbsp;</td>
    <td  width="30%" class="pageText">${actionBean.purchaseOrder.vendor.name}</td>
    <td  width="31%" align="right"class="pageText">Purchase Order Date : </td>
    <td  width="30%"align="left"class="pageText"><%	Date date1 = Calendar.getInstance().getTime();
						DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
					%><%= formatter1.format(date1) %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="pageText">${actionBean.purchaseOrder.vendor.address}</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</td>
  </tr>
  <tr>
    <td><table width="95%" border="1" cellpadding="0" cellspacing="0" align="center" class="borderstyle" style={border-color:#A0A0A0} >
  <tr>
    <td width="10%" height="28px" style="border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight: normal;" >Item Code</span></strong></div></td>
    <td width="15%"  style="border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px;font-weight:normal;">Item name</span></strong></div></td>
    <td width="9%"  style=" border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight:normal;">Uom</span></strong></div></td>
    <td width="21%"  style=" border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight:normal;">Description</span></strong></div></td>
    <td width="7%"  style=" border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight:normal;">Ord Qty</span></strong></div></td>
    <td width="11%"  style=" border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight:normal;">Rate</span></strong></div></td>
    <td width="14%"  style=" border-right:1px solid #000000;"><div align="center"><strong><span style="font-size:18px; font-weight:normal;">Amount</span></strong></div></td>
  </tr>
  <c:forEach items="${actionBean.purchaseOrder.purchasedetailarray}" var="purchasedetailarray" varStatus="loop" >

  <tr id="tabletr">
                                                <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                                                 <div align="left" style="margin-left:4px;">
                                                  <div align="center" class="pageText">


                                           ${purchasedetailarray.item.itemCode}



                                                  </div></div></td>
    <td style="border-top:1px solid #000000;border-right:1px solid #000000;"><div align="left" style="margin-left:4px;">
                                                 <div align="center" class="pageText">
                                                   ${purchasedetailarray.item.name}
                                                 </div></div></td>
    <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                                                 <div align="left" style="margin-left:4px;">
                                                 <div align="center" class="pageText">
                                                ${purchasedetailarray.item.uom.name}
                                                 </div></div></td>
    <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                                                 <div align="left" style="margin-left:4px;">
                                                 <div align="center" class="pageText">
                                                  ${purchasedetailarray.description}
                                                 </div></div></td>
    <td style="border-top:1px solid #000000;border-right:1px solid #000000;">
                                                 <div align="left" style="margin-left:4px;">
                                                 <div align="center" class="pageText">
                                                     ${purchasedetailarray.orderedQty}
                                                 </div></div></td>
   <td style="border-top:1px solid #000000;border-right:1px solid #000000;"><div align="left" style="margin-left:4px;">  <div align="center" class="pageText">
                                                   ${purchasedetailarray.rate}
                                                 </div></div></td>
                                                 <td style="border-top:1px solid #000000;"><div align="left" style="margin-left:4px;">  <div align="center" class="pageText">
                                                      ${purchasedetailarray.amount}
                                                </div></div></td>
                                             </tr>        </c:forEach>

    </table>
</td>


  <tr>
    <td>&nbsp;</td> </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>
    
    <tr>
    <td><table width="80%" border="0" align="center">
  <tr>
    <td align="center"><s:submit name="Print" value="Print" ></s:submit></td>
    <td align="center"><s:button name="Cancel" value="Cancel" onclick="javascript:window.close();"></s:button></td>
  </tr>
</table>
</td>

  </tr>
    <tr>
    <td>&nbsp;</td>
  </tr>

    
  </s:form>
</table></td></tr></table></body></html>

<%@ include file="/includes/_taglibInclude.jsp"%>
<%@page contentType="text/html" import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/print.css" />
    <link rel="stylesheet" type="text/css" href="/css/stylesheet.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script type="text/javascript">
        $(document).ready(function(){

            $('#printPDF').click(function(){
                window.print();
                $('#hide').css("display","none");
                $('#content').attr("value",$('#printContent').html());

            });
        });

    </script>
</head>
<body>
<s:form beanclass="com.erp.action.PurchaseOrderActionBean">
    

<table width="750px" border="0" cellspacing="0" cellpadding="0" align="center" class="borderstyle">

    
    <tr>
        <td colspan="2">
                <div align="left" ><b>Please Enter Terms And Condition:</b></div>
            </td>
      
    </tr>
    <tr>
        <td width="33" align="right" valign="top">
                <div align="right" ><b>1:</b></div>
            </td>
        <td width="717"><textarea name="term.firstTerm" style="height:40px;width:600px;resize:none;" ></textarea></td>
    </tr>
     <tr>
        <td width="33" align="right" valign="top">
                <div align="right" ><b>2:</b></div>
            </td>
        <td width="717"><textarea name="term.secondTerm" style="height:40px;width:600px;resize:none;" ></textarea></td>
    </tr>
     <tr>
        <td width="33" align="right" valign="top">
                <div align="right" ><b>3:</b></div>
            </td>
        <td width="717"><textarea name="term.thirdTerm" style="height:40px;width:600px;resize:none;" ></textarea></td>
    </tr>
     <tr>
        <td width="33" align="right" valign="top">
                <div align="right" ><b>4:</b></div>
            </td>
        <td width="717"><textarea name="term.forthTerm" style="height:40px;width:600px;resize:none;" ></textarea></td>
    </tr>
       <tr>
            <td>&nbsp; <s:hidden name="term.purchaseId" value="${actionBean.purchaseOrder.id}"></s:hidden>
            <s:hidden name="id" value="${actionBean.purchaseOrder.id}"></s:hidden>
            </td>
        <td>

        </td>
    </tr>
   
    <tr id="hide">
        <td colspan="2"><table width="80%" border="0" align="center">
            <tr >
                <td  align="center">



                    <s:submit name="saveTerm" value="View" ></s:submit> &nbsp;&nbsp;&nbsp;&nbsp;
                    <s:button name="Cancel" value="Cancel" onclick="javascript:window.close();"></s:button>
                </td>
            </tr>
        </table>
        </td>

    </tr>

    <tr>
        <td>&nbsp;</td>
    </tr>



</table>
         </div>
    </s:form>
</body></html>

/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.108
 * Generated at: 2021-03-26 11:07:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class VehicleModify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/css/style.css", Long.valueOf(1616756350859L));
    _jspx_dependants.put("/htmlTemplates/navigationBar.html", Long.valueOf(1616756045988L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<title>Modify Vehicle</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write("<div class=\"navbar\">\r\n");
      out.write("\t<div class=\"navbar-options-content\">\r\n");
      out.write("\t\t<a href=\"home.jsp\">Home</a>\r\n");
      out.write("\t\t<div class=\"dropdown\">\r\n");
      out.write("\t\t\t<button class=\"dropbtn\">\r\n");
      out.write("\t\t\t\tOptions <i class=\"fa fa-caret-down\"></i>\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div class=\"dropdown-content\">\r\n");
      out.write("\t\t\t\t<a href=\"list\">List All Vehicles</a> <a href=\"VehicleForm.jsp\">Add\r\n");
      out.write("\t\t\t\t\tnew Vehicle</a> <a href=\"removeForm\">Remove Vehicle</a> <a href=\"edit\">Modify\r\n");
      out.write("\t\t\t\t\tVehicle</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"navbar-profile-options\">\r\n");
      out.write("\t\t<div id=\"navUser\" class=\"dropdown\">\r\n");
      out.write("\t\t\t<button class=\"dropbtn\">\r\n");
      out.write("\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.account.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" <i class=\"fa fa-caret-down\"></i>\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div class=\"dropdown-content\">\r\n");
      out.write("\t\t\t\t<a href=\"Profile.jsp\">Show Profile</a> <a href=\"login.jsp\">Logout</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"box\">\r\n");
      out.write("\t\t<label for=\"licPlate\" id=\"modifyTitle\">Choose a car to modify:</label>\r\n");
      out.write("\t\t<form method=\"get\" action=\"update\">\r\n");
      out.write("\t\t\t<select name=\"licPlate\" id=\"cars\">\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<div class=\"modify\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"model\" placeholder=\"Model\"></input> <input\r\n");
      out.write("\t\t\t\t\tclass=\"input\" type=\"text\" name=\"insurance\" placeholder=\"insurance\"></input>\r\n");
      out.write("\t\t\t\t<input class=\"input\" type=\"text\" name=\"isAvailable\"\r\n");
      out.write("\t\t\t\t\tplaceholder=\"is available\"></input> <input class=\"input\"\r\n");
      out.write("\t\t\t\t\ttype=\"text\" name=\"milleage\" placeholder=\"Milleage\"></input> <input\r\n");
      out.write("\t\t\t\t\tclass=\"input\" type=\"text\" name=\"price\" placeholder=\"price\"></input>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<button type=\"submit\" class=\"button\">Modify Vehicle</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("<style>");
      out.write("@charset \"UTF-8\";\r\n");
      out.write("\r\n");
      out.write("body, html, div#root {height: 100%; }\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    margin: 0;\r\n");
      out.write("    background: rgb(134,41,41);\r\n");
      out.write("background: linear-gradient(90deg, rgba(134,41,41,1) 0%, rgba(185,82,42,1) 28%, rgba(210,194,187,1) 71%, rgba(92,92,92,1) 100%);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("* {\r\n");
      out.write("    font-family: Helvetica, sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("div#root {\r\n");
      out.write("    display: flex;\r\n");
      out.write("    flex-direction: column;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("main {\r\n");
      out.write("    height: 100%;\r\n");
      out.write("   background: rgb(134,41,41);\r\n");
      out.write("background: linear-gradient(90deg, rgba(134,41,41,1) 0%, rgba(185,82,42,1) 28%, rgba(210,194,187,1) 71%, rgba(92,92,92,1) 100%);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("header {\r\n");
      out.write("    display: flex;\r\n");
      out.write("    justify-content: space-between;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("    \r\n");
      out.write("    padding-left: 5%;\r\n");
      out.write("    padding-right: 5%;\r\n");
      out.write("\r\n");
      out.write("    height: 9%;\r\n");
      out.write("  }\r\n");
      out.write("\r\n");
      out.write("main {\r\n");
      out.write("    display: flex;\r\n");
      out.write("    justify-content: center;\r\n");
      out.write("    align-content: flex-start;\r\n");
      out.write("    flex-wrap: wrap;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("main{\r\n");
      out.write("    padding: 7px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button {\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\tpadding: 0.3em 1.2em;\r\n");
      out.write("\tmargin: 0 0.3em 0.3em 0;\r\n");
      out.write("\tborder-radius: 2em;\r\n");
      out.write("\tbox-sizing: border-box;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tfont-family: 'Roboto', sans-serif;\r\n");
      out.write("\tfont-weight: 300;\r\n");
      out.write("\tcolor: \twhite;\r\n");
      out.write("\tbackground-color: \trgb(144,144,144);\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttransition: all 0.2s;\r\n");
      out.write("\tborder-width: thick;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".header {\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".navbar {\r\n");
      out.write("\tdisplay: flex;\r\n");
      out.write("\tflex-direction: row;\r\n");
      out.write("\tjustify-content: space-between;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("\tbackground: rgb(42,11,11);\r\n");
      out.write("background: linear-gradient(90deg, rgba(42,11,11,1) 0%, rgba(36,36,36,1) 83%);\r\n");
      out.write("}\r\n");
      out.write(".navbar-profile-options {\r\n");
      out.write("\t padding-right: 80px;\r\n");
      out.write("}\r\n");
      out.write(".navbar a {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tpadding: 14px 16px;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown .dropbtn {\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\tborder: none;\r\n");
      out.write("\toutline: none;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tpadding: 14px 16px;\r\n");
      out.write("\tbackground-color: inherit;\r\n");
      out.write("\tfont-family: inherit;\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".navbar a:hover, .dropdown:hover .dropbtn {\r\n");
      out.write("\tbackground-color: red;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content {\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tbackground-color: #f9f9f9;\r\n");
      out.write("\tmin-width: 160px;\r\n");
      out.write("\tbox-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);\r\n");
      out.write("\tz-index: 1;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content a {\r\n");
      out.write("\tfloat: none;\r\n");
      out.write("\tcolor: black;\r\n");
      out.write("\tpadding: 12px 16px;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content a:hover {\r\n");
      out.write("\tbackground-color: #ddd;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown:hover .dropdown-content {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".box {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\ttop: 50%;\r\n");
      out.write("\tleft: 50%;\r\n");
      out.write("\ttransform: translate(-50%, -50%);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".box select {\r\n");
      out.write("\tbackground-color: rgb(80,80,80);\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tpadding: 12px;\r\n");
      out.write("\twidth: 250px;\r\n");
      out.write("\tborder: none;\r\n");
      out.write("\tfont-size: 20px;\r\n");
      out.write("\tbox-shadow: 0 5px 25px rgba(0, 0, 0, 0.2);\r\n");
      out.write("\t-webkit-appearance: button;\r\n");
      out.write("\tappearance: button;\r\n");
      out.write("\toutline: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".box select option {\r\n");
      out.write("\tpadding: 30px;\r\n");
      out.write("\tbackground-color: rgb(190,190,190);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".wrapper {\r\n");
      out.write("\tmargin: 0 auto;\r\n");
      out.write("\tpadding: 40px;\r\n");
      out.write("\tmax-width: 800px;\r\n");
      out.write("}\r\n");
      out.write(".table {\r\n");
      out.write("\tmargin: 0 0 40px 0;\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\tbox-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);\r\n");
      out.write("\tdisplay: table;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".row {\r\n");
      out.write("\tdisplay: table-row;\r\n");
      out.write("\tbackground: rgb(65,65,65);\r\n");
      out.write("background: linear-gradient(90deg, rgba(65,65,65,1) 0%, rgba(168,168,168,1) 50%, rgba(82,82,82,1) 100%);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(".cell {\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tmargin-bottom: 10px;\r\n");
      out.write("\tpadding: 6px 12px;\r\n");
      out.write("\tdisplay: table-cell;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".modify {\r\n");
      out.write("\tdisplay: flex;\r\n");
      out.write("    flex-direction: column;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#modifyTitle {\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("    font-size: larger;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    font-style: italic;\r\n");
      out.write("    background-color: \trgb(144,144,144);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#userInformation {\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#navUser {\r\n");
      out.write("\t\r\n");
      out.write("}");
      out.write("</style>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /VehicleModify.jsp(19,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("vehicle");
      // /VehicleModify.jsp(19,4) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/VehicleModify.jsp(19,4) '${listVehicle}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${listVehicle}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.licPlate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
            out.write("\">\r\n");
            out.write("\t\t\t\t\t\t");
            if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return true;
            out.write("\r\n");
            out.write("\t\t\t\t\t</option>\r\n");
            out.write("\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    boolean _jspx_th_c_005fout_005f0_reused = false;
    try {
      _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
      // /VehicleModify.jsp(21,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vehicle.licPlate}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
      if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      _jspx_th_c_005fout_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fout_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fout_005f0_reused);
    }
    return false;
  }
}

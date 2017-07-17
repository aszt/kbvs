import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_adminInfoadminLogin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminInfo/adminLogin.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':("")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':("")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("format-detection"),'content':("telephone=no")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'style.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'iconfont.css'))
printHtmlPart(5)
invokeTag('javascript','g',17,['src':("jquery.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',18,['src':("bootstrap.min.js")],-1)
printHtmlPart(6)
invokeTag('javascript','g',23,['src':("html5.js")],-1)
printHtmlPart(7)
invokeTag('javascript','g',25,['src':("respond.min.js")],-1)
printHtmlPart(8)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.min.css'))
printHtmlPart(9)
expressionOut.print(resource(dir: 'css',file: 'common.css'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'css',file: 'slide.css'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'css',file: 'flat-ui.min.css'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'css',file: 'jquery.nouislider.css'))
printHtmlPart(11)
})
invokeTag('captureHead','sitemesh',32,[:],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(13)
expressionOut.print(resource(dir: 'images',file: 'logo.png.png'))
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
expressionOut.print(flash.message)
printHtmlPart(16)
})
invokeTag('form','g',65,['controller':("adminInfo"),'action':("doAdminLogin"),'method':("post")],2)
printHtmlPart(17)
invokeTag('createLink','g',131,['controller':("userInfo"),'action':("verifyLogin")],-1)
printHtmlPart(18)
invokeTag('createLink','g',166,['controller':("userInfo"),'action':("startCaptcha")],-1)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',186,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1469935333803L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

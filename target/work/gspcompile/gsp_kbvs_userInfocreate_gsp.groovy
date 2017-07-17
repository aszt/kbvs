import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_userInfocreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userInfo/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',4,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',4,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.min.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'Registered_account.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'images',file: 'favicon.png'))
printHtmlPart(6)
invokeTag('javascript','g',8,['src':("jquery-1.11.3.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',9,['src':("bootstrap.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',10,['src':("main.js")],-1)
printHtmlPart(7)
invokeTag('createLink','g',13,['controller':("userInfo"),'action':("verifyCode")],-1)
printHtmlPart(8)
})
invokeTag('captureHead','sitemesh',16,[:],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',24,['action':("index"),'controller':("dbdoc")],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
invokeTag('textField','g',36,['name':("loginName"),'required':(""),'class':("form-control user"),'placeholder':("用户名"),'style':(" width:50%; margin:auto;")],-1)
printHtmlPart(15)
invokeTag('passwordField','g',39,['name':("password"),'required':(""),'class':("form-control pwd"),'placeholder':("密码设置6-20个字符"),'style':(" width:50%; margin:auto;")],-1)
printHtmlPart(15)
invokeTag('passwordField','g',42,['name':("passwords"),'required':(""),'class':("form-control pwd"),'placeholder':("请确认你的密码"),'style':(" width:50%; margin:auto;")],-1)
printHtmlPart(16)
invokeTag('textField','g',46,['name':("code"),'required':(""),'class':("form-control"),'placeholder':("请输入验证码"),'style':("width:25%; margin-left:182px; padding-left:5px;float:left; margin-right: 15px;")],-1)
printHtmlPart(17)
invokeTag('createLink','g',47,['controller':("userInfo"),'action':("verifyCode")],-1)
printHtmlPart(18)
})
invokeTag('form','g',54,['controller':("userInfo"),'action':("doRegister"),'method':("post"),'class':("form-horizontal myform")],2)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',75,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1463660906190L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import com.panda.kbvs.UserInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_userInfo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userInfo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("userInfo.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'value':(userInfoInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'loginName', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("userInfo.loginName.label"),'default':("Login Name")],-1)
printHtmlPart(5)
invokeTag('textField','g',19,['name':("loginName"),'required':(""),'value':(userInfoInstance?.loginName)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'code', 'error'))
printHtmlPart(6)
invokeTag('message','g',25,['code':("userInfo.code.label"),'default':("Code")],-1)
printHtmlPart(2)
invokeTag('textField','g',28,['name':("code"),'value':(userInfoInstance?.code)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'password', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("userInfo.password.label"),'default':("Password")],-1)
printHtmlPart(5)
invokeTag('textField','g',37,['name':("password"),'required':(""),'value':(userInfoInstance?.password)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'email', 'error'))
printHtmlPart(8)
invokeTag('message','g',43,['code':("userInfo.email.label"),'default':("Email")],-1)
printHtmlPart(2)
invokeTag('textField','g',46,['name':("email"),'value':(userInfoInstance?.email)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'mobilePhone', 'error'))
printHtmlPart(9)
invokeTag('message','g',52,['code':("userInfo.mobilePhone.label"),'default':("Mobile Phone")],-1)
printHtmlPart(2)
invokeTag('textField','g',55,['name':("mobilePhone"),'value':(userInfoInstance?.mobilePhone)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'telephone', 'error'))
printHtmlPart(10)
invokeTag('message','g',61,['code':("userInfo.telephone.label"),'default':("Telephone")],-1)
printHtmlPart(2)
invokeTag('textField','g',64,['name':("telephone"),'value':(userInfoInstance?.telephone)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'state', 'error'))
printHtmlPart(11)
invokeTag('message','g',70,['code':("userInfo.state.label"),'default':("State")],-1)
printHtmlPart(2)
invokeTag('textField','g',73,['name':("state"),'value':(userInfoInstance?.state)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'department', 'error'))
printHtmlPart(12)
invokeTag('message','g',79,['code':("userInfo.department.label"),'default':("Department")],-1)
printHtmlPart(2)
invokeTag('textField','g',82,['name':("department"),'value':(userInfoInstance?.department)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'imagePath', 'error'))
printHtmlPart(13)
invokeTag('message','g',88,['code':("userInfo.imagePath.label"),'default':("Image Path")],-1)
printHtmlPart(2)
invokeTag('textField','g',91,['name':("imagePath"),'value':(userInfoInstance?.imagePath)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: userInfoInstance, field: 'groupIds', 'error'))
printHtmlPart(14)
invokeTag('message','g',97,['code':("userInfo.groupIds.label"),'default':("Group Ids")],-1)
printHtmlPart(2)
invokeTag('textField','g',100,['name':("groupIds"),'value':(userInfoInstance?.groupIds)],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1462265092057L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

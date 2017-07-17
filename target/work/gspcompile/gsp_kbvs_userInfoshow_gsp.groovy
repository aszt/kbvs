import com.panda.kbvs.UserInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_userInfoshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userInfo/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'userInfo.label', default: 'UserInfo'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("list"),'action':("index")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',16,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',16,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',20,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (userInfoInstance?.name)) {
printHtmlPart(14)
invokeTag('message','g',28,['code':("userInfo.name.label"),'default':("Name")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',30,['bean':(userInfoInstance),'field':("name")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.loginName)) {
printHtmlPart(18)
invokeTag('message','g',37,['code':("userInfo.loginName.label"),'default':("Login Name")],-1)
printHtmlPart(19)
invokeTag('fieldValue','g',39,['bean':(userInfoInstance),'field':("loginName")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.code)) {
printHtmlPart(20)
invokeTag('message','g',46,['code':("userInfo.code.label"),'default':("Code")],-1)
printHtmlPart(21)
invokeTag('fieldValue','g',48,['bean':(userInfoInstance),'field':("code")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.password)) {
printHtmlPart(22)
invokeTag('message','g',55,['code':("userInfo.password.label"),'default':("Password")],-1)
printHtmlPart(23)
invokeTag('fieldValue','g',57,['bean':(userInfoInstance),'field':("password")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.email)) {
printHtmlPart(24)
invokeTag('message','g',64,['code':("userInfo.email.label"),'default':("Email")],-1)
printHtmlPart(25)
invokeTag('fieldValue','g',66,['bean':(userInfoInstance),'field':("email")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.mobilePhone)) {
printHtmlPart(26)
invokeTag('message','g',73,['code':("userInfo.mobilePhone.label"),'default':("Mobile Phone")],-1)
printHtmlPart(27)
invokeTag('fieldValue','g',75,['bean':(userInfoInstance),'field':("mobilePhone")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.telephone)) {
printHtmlPart(28)
invokeTag('message','g',82,['code':("userInfo.telephone.label"),'default':("Telephone")],-1)
printHtmlPart(29)
invokeTag('fieldValue','g',84,['bean':(userInfoInstance),'field':("telephone")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.state)) {
printHtmlPart(30)
invokeTag('message','g',91,['code':("userInfo.state.label"),'default':("State")],-1)
printHtmlPart(31)
invokeTag('fieldValue','g',93,['bean':(userInfoInstance),'field':("state")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.department)) {
printHtmlPart(32)
invokeTag('message','g',100,['code':("userInfo.department.label"),'default':("Department")],-1)
printHtmlPart(33)
invokeTag('fieldValue','g',102,['bean':(userInfoInstance),'field':("department")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.imagePath)) {
printHtmlPart(34)
invokeTag('message','g',109,['code':("userInfo.imagePath.label"),'default':("Image Path")],-1)
printHtmlPart(35)
invokeTag('fieldValue','g',111,['bean':(userInfoInstance),'field':("imagePath")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (userInfoInstance?.groupIds)) {
printHtmlPart(36)
invokeTag('message','g',118,['code':("userInfo.groupIds.label"),'default':("Group Ids")],-1)
printHtmlPart(37)
invokeTag('fieldValue','g',120,['bean':(userInfoInstance),'field':("groupIds")],-1)
printHtmlPart(16)
}
printHtmlPart(38)
createTagBody(2, {->
printHtmlPart(39)
createTagBody(3, {->
invokeTag('message','g',128,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',128,['class':("edit"),'action':("edit"),'resource':(userInfoInstance)],3)
printHtmlPart(40)
invokeTag('actionSubmit','g',129,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(41)
})
invokeTag('form','g',131,['url':([resource:userInfoInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(42)
})
invokeTag('captureBody','sitemesh',133,[:],1)
printHtmlPart(43)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1462265091530L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

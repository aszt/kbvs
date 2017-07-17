import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_userInfopersonal_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userInfo/personal.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'myself.css'))
printHtmlPart(4)
invokeTag('javascript','g',12,['src':("echarts.min.js")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',14,[:],2)
printHtmlPart(6)
invokeTag('createLink','g',43,['controller':("userInfo"),'action':("updateInfo")],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',61,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(10)
})
invokeTag('link','g',76,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',80,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('link','g',82,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(15)
expressionOut.print(resource(dir: '',file:session.imagePath ))
printHtmlPart(16)
expressionOut.print(session.userName)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',93,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',95,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(21)
expressionOut.print(resource(dir: '',file:session.imagePath ))
printHtmlPart(22)
expressionOut.print(UserInfoInstance.loginName)
printHtmlPart(23)
expressionOut.print(UserInfoInstance.name)
printHtmlPart(24)
expressionOut.print(UserInfoInstance.loginName)
printHtmlPart(25)
expressionOut.print(UserInfoInstance.email)
printHtmlPart(26)
expressionOut.print(UserInfoInstance.mobilePhone)
printHtmlPart(27)
expressionOut.print(UserInfoInstance.telephone)
printHtmlPart(28)
expressionOut.print(UserInfoInstance.department)
printHtmlPart(29)
createClosureForHtmlPart(30, 2)
invokeTag('form','g',185,['name':("fm")],2)
printHtmlPart(31)
expressionOut.print(Video)
printHtmlPart(32)
expressionOut.print(Word)
printHtmlPart(33)
expressionOut.print(Music)
printHtmlPart(34)
expressionOut.print(Images)
printHtmlPart(35)
expressionOut.print(Other)
printHtmlPart(36)
expressionOut.print(Count)
printHtmlPart(37)
invokeTag('javascript','g',251,['src':("jquery-1.11.3.min.js")],-1)
printHtmlPart(38)
invokeTag('javascript','g',253,['src':("swfobject.js")],-1)
printHtmlPart(38)
invokeTag('javascript','g',254,['src':("fullAvatarEditor.js")],-1)
printHtmlPart(39)
invokeTag('createLink','g',266,['controller':("userInfo"),'action':("uploadImg")],-1)
printHtmlPart(40)
invokeTag('createLink','g',297,['controller':("userInfo"),'action':("personal")],-1)
printHtmlPart(41)
})
invokeTag('captureBody','sitemesh',304,[:],1)
printHtmlPart(42)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471766192216L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

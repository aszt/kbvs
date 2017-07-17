import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvsindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'style.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'iconfont.css'))
printHtmlPart(5)
invokeTag('javascript','g',9,['src':("jquery.js")],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images',file: 'logo.png.png'))
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(flash.message)
printHtmlPart(11)
expressionOut.print(flash.registMsg)
printHtmlPart(12)
})
invokeTag('form','g',44,['controller':("userInfo"),'action':("doLogin"),'method':("post"),'id':("loginForm")],2)
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('form','g',87,['controller':("userInfo"),'action':("doRegister"),'method':("post"),'id':("registForm")],2)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('form','g',106,['controller':("userInfo"),'action':("sendResetEmail"),'method':("post")],2)
printHtmlPart(17)
invokeTag('createLink','g',214,['controller':("userInfo"),'action':("checkEmail")],-1)
printHtmlPart(18)
invokeTag('createLink','g',237,['controller':("userInfo"),'action':("checkResetEmail")],-1)
printHtmlPart(19)
invokeTag('createLink','g',259,['controller':("userInfo"),'action':("checkUserName")],-1)
printHtmlPart(20)
invokeTag('createLink','g',313,['controller':("userInfo"),'action':("verifyLogin")],-1)
printHtmlPart(21)
invokeTag('createLink','g',347,['controller':("userInfo"),'action':("startCaptcha")],-1)
printHtmlPart(22)
invokeTag('createLink','g',383,['controller':("userInfo"),'action':("checkEmail")],-1)
printHtmlPart(23)
invokeTag('createLink','g',408,['controller':("userInfo"),'action':("checkUserName")],-1)
printHtmlPart(24)
invokeTag('createLink','g',450,['controller':("userInfo"),'action':("verifyLogin")],-1)
printHtmlPart(25)
invokeTag('createLink','g',485,['controller':("userInfo"),'action':("startCaptcha")],-1)
printHtmlPart(26)
invokeTag('createLink','g',515,['controller':("userInfo"),'action':("checkResetEmail")],-1)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',527,[:],1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1469935754238L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_adminInfoshowUser_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/adminInfo/showUser.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
invokeTag('captureTitle','sitemesh',10,[:],-1)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'common.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.min.css'))
printHtmlPart(5)
invokeTag('javascript','g',13,['src':("jquery-1.11.3.min.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',14,['src':("echarts.min.js")],-1)
printHtmlPart(2)
invokeTag('javascript','g',14,['src':("bootstrap.min.js")],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',15,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(resource(dir: '',file: UserInfo.imagePath))
printHtmlPart(8)
expressionOut.print(UserInfo.name)
printHtmlPart(9)
expressionOut.print(UserInfo.loginName)
printHtmlPart(10)
expressionOut.print(UserInfo.email)
printHtmlPart(11)
expressionOut.print(UserInfo.mobilePhone)
printHtmlPart(12)
expressionOut.print(Video)
printHtmlPart(13)
expressionOut.print(Word)
printHtmlPart(14)
expressionOut.print(Music)
printHtmlPart(15)
expressionOut.print(Images)
printHtmlPart(16)
expressionOut.print(Other)
printHtmlPart(17)
expressionOut.print(Count)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',117,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471243975669L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

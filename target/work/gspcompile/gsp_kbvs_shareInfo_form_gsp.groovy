import com.panda.kbvs.ShareInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_shareInfo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/shareInfo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: shareInfoInstance, field: 'content', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("shareInfo.content.label"),'default':("Content")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("content"),'required':(""),'value':(shareInfoInstance?.content)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: shareInfoInstance, field: 'name', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("shareInfo.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',19,['name':("name"),'required':(""),'value':(shareInfoInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: shareInfoInstance, field: 'ownerId', 'error'))
printHtmlPart(5)
invokeTag('message','g',25,['code':("shareInfo.ownerId.label"),'default':("Owner Id")],-1)
printHtmlPart(2)
invokeTag('textField','g',28,['name':("ownerId"),'required':(""),'value':(shareInfoInstance?.ownerId)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: shareInfoInstance, field: 'userId', 'error'))
printHtmlPart(6)
invokeTag('message','g',34,['code':("shareInfo.userId.label"),'default':("User Id")],-1)
printHtmlPart(2)
invokeTag('textField','g',37,['name':("userId"),'required':(""),'value':(shareInfoInstance?.userId)],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1464620982380L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

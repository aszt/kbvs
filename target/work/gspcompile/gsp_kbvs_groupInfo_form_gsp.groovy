import com.panda.kbvs.GroupInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_groupInfo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupInfo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: groupInfoInstance, field: 'imgPath', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("groupInfo.imgPath.label"),'default':("Img Path")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("imgPath"),'value':(groupInfoInstance?.imgPath)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: groupInfoInstance, field: 'creatorId', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("groupInfo.creatorId.label"),'default':("Creator Id")],-1)
printHtmlPart(5)
invokeTag('textField','g',19,['name':("creatorId"),'required':(""),'value':(groupInfoInstance?.creatorId)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: groupInfoInstance, field: 'members', 'error'))
printHtmlPart(6)
invokeTag('message','g',25,['code':("groupInfo.members.label"),'default':("Members")],-1)
printHtmlPart(5)
invokeTag('textField','g',28,['name':("members"),'required':(""),'value':(groupInfoInstance?.members)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: groupInfoInstance, field: 'name', 'error'))
printHtmlPart(7)
invokeTag('message','g',34,['code':("groupInfo.name.label"),'default':("Name")],-1)
printHtmlPart(5)
invokeTag('textField','g',37,['name':("name"),'required':(""),'value':(groupInfoInstance?.name)],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1464620927898L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import com.panda.kbvs.FolderInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_folderInfo_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/folderInfo/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: folderInfoInstance, field: 'beforeOwner', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("folderInfo.beforeOwner.label"),'default':("Before Owner")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("beforeOwner"),'value':(folderInfoInstance?.beforeOwner)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: folderInfoInstance, field: 'name', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("folderInfo.name.label"),'default':("Name")],-1)
printHtmlPart(5)
invokeTag('textField','g',19,['name':("name"),'required':(""),'value':(folderInfoInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: folderInfoInstance, field: 'ownerId', 'error'))
printHtmlPart(6)
invokeTag('message','g',25,['code':("folderInfo.ownerId.label"),'default':("Owner Id")],-1)
printHtmlPart(5)
invokeTag('textField','g',28,['name':("ownerId"),'required':(""),'value':(folderInfoInstance?.ownerId)],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1462529282889L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

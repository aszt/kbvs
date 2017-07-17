import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoopen_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/open.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
invokeTag('captureTitle','sitemesh',11,[:],-1)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(3)
invokeTag('createLink','g',31,['controller':("folderInfo"),'action':("findFolder")],-1)
printHtmlPart(4)
invokeTag('createLink','g',69,['controller':("folderInfo"),'action':("findFolder")],-1)
printHtmlPart(5)
invokeTag('createLink','g',95,['controller':("folderInfo"),'action':("move")],-1)
printHtmlPart(6)
invokeTag('createLink','g',104,['controller':("resourceInfo"),'action':("findResourcesByFolder")],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',114,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(FolderInfos.id)
printHtmlPart(9)
loop:{
int i = 0
for( folderInfoInstance in (FolderInfoList) ) {
printHtmlPart(10)
expressionOut.print(folderInfoInstance.id)
printHtmlPart(11)
expressionOut.print(folderInfoInstance.id)
printHtmlPart(12)
expressionOut.print(fieldValue(bean: folderInfoInstance, field: "name"))
printHtmlPart(13)
i++
}
}
printHtmlPart(14)
expressionOut.print(FolderId)
printHtmlPart(15)
})
invokeTag('captureBody','sitemesh',159,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471943436448L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

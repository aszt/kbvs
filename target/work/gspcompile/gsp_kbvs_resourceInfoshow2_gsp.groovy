import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoshow2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/show2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1, maximum-scale=1")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("google"),'content':("notranslate")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css', file: 'viewer.css'))
printHtmlPart(4)
invokeTag('javascript','g',13,['src':("compatibility.js")],-1)
printHtmlPart(5)
expressionOut.print(resource(dir: 'locale', file: 'locale.properties'))
printHtmlPart(6)
invokeTag('javascript','g',19,['src':("l10n.js")],-1)
printHtmlPart(7)
invokeTag('javascript','g',21,['src':("build/pdf.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',22,['src':("jquery-1.11.3.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',23,['src':("debugger.js")],-1)
printHtmlPart(8)
expressionOut.print(firstUrl)
printHtmlPart(9)
expressionOut.print(resource(dir: 'js',file: 'pdf.worker.js'))
printHtmlPart(10)
invokeTag('javascript','g',32,['src':("viewer.js")],-1)
printHtmlPart(11)
})
invokeTag('captureHead','sitemesh',34,[:],1)
printHtmlPart(11)
createClosureForHtmlPart(12, 1)
invokeTag('captureBody','sitemesh',408,['tabindex':("1"),'class':("loadingInProgress")],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471954022612L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

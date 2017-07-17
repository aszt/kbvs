import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',10,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',10,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'bootstrap.min.css'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css', file: 'mains.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file: 'base.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'css',file: 'fileinput.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css'))
printHtmlPart(7)
expressionOut.print(resource(dir: 'images',file: 'favicon.png'))
printHtmlPart(8)
invokeTag('javascript','g',18,['src':("jquery-1.11.3.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',18,['src':("fileinput.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',19,['src':("fileinput_locale_zh.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',20,['src':("bootstrap.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',21,['src':("main.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',22,['src':("bootstrap-treeview.js")],-1)
printHtmlPart(9)
invokeTag('javascript','g',23,['src':("layer/layer.js")],-1)
printHtmlPart(10)
invokeTag('layoutHead','g',24,[:],-1)
printHtmlPart(11)
})
invokeTag('captureHead','sitemesh',24,[:],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('layoutBody','g',26,[:],-1)
printHtmlPart(12)
invokeTag('message','g',34,['code':("spinner.alt"),'default':("Loading&hellip;")],-1)
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',34,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471872550299L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoshowMusic_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/showMusic.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('javascript','g',8,['src':("APlayer.min.js")],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'APlayer.min.css'))
printHtmlPart(4)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',12,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',13,[:],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',15,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(resourceInfo.name)
printHtmlPart(8)
expressionOut.print(resourceInfo.artist)
printHtmlPart(9)
expressionOut.print(resourceInfo.path)
printHtmlPart(10)
expressionOut.print(resource(dir: 'images',file: 'music_94.025465230167px_1191632_easyicon.net.png'))
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',31,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471596746038L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

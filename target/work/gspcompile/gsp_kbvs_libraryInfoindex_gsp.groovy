import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_libraryInfoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/libraryInfo/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',14,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'css',file:'mobiscroll.2.13.2.css'))
printHtmlPart(6)
invokeTag('javascript','g',16,['src':("mobiscroll.2.13.2.js")],-1)
printHtmlPart(7)
invokeTag('createLink','g',24,['controller':("libraryInfo"),'action':("delete")],-1)
printHtmlPart(8)
invokeTag('createLink','g',53,['controller':("libraryInfo"),'action':("ajaxUpdate")],-1)
printHtmlPart(9)
invokeTag('createLink','g',92,['controller':("shareInfo"),'action':("shares")],-1)
printHtmlPart(10)
invokeTag('createLink','g',182,['controller':("groupInfo"),'action':("myGroup")],-1)
printHtmlPart(11)
invokeTag('createLink','g',215,['controller':("shareInfo"),'action':("shareGroup")],-1)
printHtmlPart(12)
})
invokeTag('captureHead','sitemesh',230,[:],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(16)
})
invokeTag('link','g',247,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',252,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',253,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(21)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(22)
expressionOut.print(session.userName)
printHtmlPart(23)
createClosureForHtmlPart(24, 2)
invokeTag('link','g',264,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(25)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',266,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',280,['controller':("resourceInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(30, 2)
invokeTag('link','g',281,['controller':("resourceInfo"),'action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',282,['controller':("resourceInfo"),'action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',283,['controller':("resourceInfo"),'action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',284,['controller':("resourceInfo"),'action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',285,['controller':("resourceInfo"),'action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(35, 2)
invokeTag('link','g',286,['controller':("resourceInfo"),'action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(36, 2)
invokeTag('link','g',287,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(29)
createClosureForHtmlPart(37, 2)
invokeTag('link','g',288,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(38)
loop:{
int i = 0
for( libraryInfo in (libraryInfoList) ) {
printHtmlPart(39)
expressionOut.print(libraryInfo.id)
printHtmlPart(40)
createTagBody(3, {->
printHtmlPart(41)
expressionOut.print(libraryInfo.id)
printHtmlPart(42)
expressionOut.print(libraryInfo.id)
printHtmlPart(43)
expressionOut.print(libraryInfo.name)
printHtmlPart(44)
})
invokeTag('link','g',322,['controller':("libraryInfo"),'action':("show"),'id':(libraryInfo.id)],3)
printHtmlPart(45)
i++
}
}
printHtmlPart(46)
invokeTag('createLink','g',460,['controller':("libraryInfo"),'action':("validate")],-1)
printHtmlPart(47)
invokeTag('createLink','g',476,['controller':("libraryInfo"),'action':("addLibrary")],-1)
printHtmlPart(48)
})
invokeTag('captureBody','sitemesh',596,[:],1)
printHtmlPart(49)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471969786298L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

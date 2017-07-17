import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_shareInfoindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/shareInfo/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css'))
printHtmlPart(5)
invokeTag('createLink','g',83,['controller':("shareInfo"),'action':("deletes")],-1)
printHtmlPart(6)
invokeTag('createLink','g',106,['controller':("resourceInfo"),'action':("deletes")],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',127,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(10)
})
invokeTag('link','g',137,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',142,['controller':("resourceInfo"),'action':("nextIndex")],2)
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('link','g',143,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(15)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(16)
expressionOut.print(session.userName)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',154,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',156,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',170,['controller':("resourceInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(24, 2)
invokeTag('link','g',171,['controller':("resourceInfo"),'action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(25, 2)
invokeTag('link','g',172,['controller':("resourceInfo"),'action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',173,['controller':("resourceInfo"),'action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',174,['controller':("resourceInfo"),'action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',175,['controller':("resourceInfo"),'action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',176,['controller':("resourceInfo"),'action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(30, 2)
invokeTag('link','g',177,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(23)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',178,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(32)
expressionOut.print(fillType)
printHtmlPart(33)
loop:{
int i = 0
for( folderInfoListInstance in (ShareInfoList) ) {
printHtmlPart(34)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(35)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(36)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(37)
createTagBody(3, {->
printHtmlPart(38)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: "name"))
printHtmlPart(39)
})
invokeTag('link','g',228,['controller':("shareInfo"),'action':("show"),'id':(folderInfoListInstance.id)],3)
printHtmlPart(40)
expressionOut.print(folderInfoListInstance.id)
printHtmlPart(41)
expressionOut.print(fieldValue(bean: folderInfoListInstance, field: ""))
printHtmlPart(42)
expressionOut.print(folderInfoListInstance.dateCreated.format('yyyy-MM-dd'))
printHtmlPart(43)
i++
}
}
printHtmlPart(44)
})
invokeTag('captureBody','sitemesh',264,[:],1)
printHtmlPart(45)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471953448653L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

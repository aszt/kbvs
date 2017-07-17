import com.panda.kbvs.ResourceInfo
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfovideoPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/videoPage.gsp" }
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
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(8)
})
invokeTag('link','g',20,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',25,['action':("nextIndex")],2)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',26,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(13)
expressionOut.print(resource(file: session.imagePath))
printHtmlPart(14)
expressionOut.print(session.userName)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',37,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',39,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',54,['action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',55,['action':("categoryPage"),'id':("activeFile"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(23, 2)
invokeTag('link','g',56,['action':("categoryPage"),'id':("video"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(24, 2)
invokeTag('link','g',57,['action':("categoryPage"),'id':("music"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(25, 2)
invokeTag('link','g',58,['action':("categoryPage"),'id':("word"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(26, 2)
invokeTag('link','g',59,['action':("categoryPage"),'id':("images"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',60,['action':("categoryPage"),'id':("other"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',61,['controller':("shareInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(21)
createClosureForHtmlPart(29, 2)
invokeTag('link','g',62,['controller':("libraryInfo"),'action':("index"),'style':("text-decoration: none")],2)
printHtmlPart(30)
createTagBody(2, {->
printHtmlPart(31)
expressionOut.print(search)
printHtmlPart(32)
expressionOut.print(search)
printHtmlPart(33)
expressionOut.print(fillType)
printHtmlPart(34)
})
invokeTag('form','g',82,['controller':("resourceInfo"),'action':("categorySearch"),'method':("get"),'class':("navbar-form navbar-right hidden-xs sea"),'role':("search")],2)
printHtmlPart(35)
loop:{
int i = 0
for( resourceInfoInstance in (ResourceInfoList) ) {
printHtmlPart(36)
createTagBody(3, {->
printHtmlPart(37)
expressionOut.print(resource(dir: '',file: resourceInfoInstance.preImgPath))
printHtmlPart(38)
expressionOut.print(raw(resourceInfoInstance.name))
printHtmlPart(39)
expressionOut.print(raw(resourceInfoInstance.extName))
printHtmlPart(40)
})
invokeTag('link','g',108,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfoInstance.id)],3)
printHtmlPart(41)
i++
}
}
printHtmlPart(42)
invokeTag('createLink','g',145,['controller':("resourceInfo"),'action':("pagination")],-1)
printHtmlPart(43)
})
invokeTag('captureBody','sitemesh',172,[:],1)
printHtmlPart(44)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471909173903L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

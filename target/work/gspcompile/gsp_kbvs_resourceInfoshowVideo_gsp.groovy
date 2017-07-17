import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kbvs_resourceInfoshowVideo_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/resourceInfo/showVideo.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css',file: 'flashshow.css'))
printHtmlPart(4)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',12,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',13,[:],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images',file: 'LOGOO.png'))
printHtmlPart(9)
})
invokeTag('link','g',25,['action':("nextIndex"),'class':("navbar-brand logo"),'style':("width:160px; margin-top:-15px;")],2)
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',30,['action':("nextIndex")],2)
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('link','g',31,['controller':("groupInfo"),'action':("index")],2)
printHtmlPart(14)
expressionOut.print(resource(dir: '',file: session.imagePath))
printHtmlPart(15)
expressionOut.print(session.userName)
printHtmlPart(16)
createClosureForHtmlPart(17, 2)
invokeTag('link','g',42,['controller':("userInfo"),'action':("personal")],2)
printHtmlPart(18)
createClosureForHtmlPart(19, 2)
invokeTag('link','g',44,['controller':("userInfo"),'action':("loginOut")],2)
printHtmlPart(20)
expressionOut.print(resourceInfo.name)
printHtmlPart(21)
expressionOut.print(resourceInfo.extName)
printHtmlPart(22)
loop:{
int i = 0
for( resourceInfo in (resourceInfoList) ) {
printHtmlPart(23)
createTagBody(3, {->
printHtmlPart(24)
expressionOut.print(raw(resourceInfo.preImgPath))
printHtmlPart(25)
})
invokeTag('link','g',69,['controller':("resourceInfo"),'action':("showVideo"),'id':(resourceInfo.id)],3)
printHtmlPart(26)
expressionOut.print(resourceInfo.name)
printHtmlPart(27)
expressionOut.print(resourceInfo.fileSize)
printHtmlPart(28)
i++
}
}
printHtmlPart(29)
invokeTag('javascript','g',77,['src':("../ckplayer/ckplayer.js")],-1)
printHtmlPart(30)
invokeTag('createLink','g',93,['url':("../../${firstUrl}")],-1)
printHtmlPart(31)
expressionOut.print(firstUrl)
printHtmlPart(32)
})
invokeTag('captureBody','sitemesh',99,[:],1)
printHtmlPart(33)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1471770211418L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import com.panda.kbvs.AdminInfo

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/resourceIndex"(action:'index' ,controller: 'ResourceInfo')
        "/"(view:"/index")
        "/adminLogin"(controller: 'AdminInfo',action: 'adminLogin')
        "500"(view:'/error')
	}
}

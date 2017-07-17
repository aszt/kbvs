package com.panda.kbvs
/**
 * 管理员
 */
class AdminInfo {

    String id
    String name
    String password

    static constraints = {
        name(blank:false)
        password(blank:false)
    }

    static mapping = {
        id generator: 'uuid.hex'
    }

    String toString(){
        return name
    }
}

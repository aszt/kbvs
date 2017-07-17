package com.panda.kbvs
/**
 * 用户
 */
class UserInfo {
    String id
    String name //姓名
    String loginName    //账号名
    String code //工号
    String password //密码
    String email    //电子邮件
    String mobilePhone  //手机号码
    String telephone    //办公号码
    String state    //用户状态:激活、禁用、未激活.0,1,2
    String department   //部门
    String imagePath    //用户头像
    String  groupIds    //我加入的组群Ids

    static constraints = {
        name(nullable: true)
        loginName(blank: false)
        code(nullable: true)
        password(blank: false)
        email(nullable: true)
        mobilePhone(nullable: true)
        telephone(nullable: true)
        state(nullable: true)
        department(nullable: true)
        imagePath(nullable: true)
        groupIds(nullable: true)
    }
    static mapping = {
        id generator: 'uuid.hex'
    }

    String toString() {
        return loginName
    }

}

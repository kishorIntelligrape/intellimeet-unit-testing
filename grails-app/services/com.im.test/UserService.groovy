package com.im.test

class UserService {

def grailsApplication

    def getConfig() {
        return grailsApplication.config
    }

    User save(UserCO co) {
        User user = new User(givenName: co.givenName, familyName: co.familyName, password: co.password, username: co.username)
        user.save()
        UserRole.create(user, Role.findByAuthority(config.ais.sec.role.user_role))
        return user
    }

    void updatePassword(UpdatePasswordCO co) {
        User user = User.findByUsername(co.id)
        user.password = co.newPassword
        user.save(flush: true)
    }
	
}
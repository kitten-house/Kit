
class UserStore {
    private user: User = null

    storeUser(user: User) {
        this.user = user
    }

    restoreUser(): User | null {
        return this.user
    }
}

const instance = new UserStore()

export default instance

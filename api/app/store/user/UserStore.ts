import {generateId} from "../../core/gen/Generator";

class UserStore {
    private user: User | null = null
    observers = new Map<string, (user: User | null) => void>()

    storeUser(user: User | null) {
        this.user = user
        this.observers.forEach((observer) => {
            observer(user)
        })
    }

    restoreUser(): User | null {
        return this.user
    }

    observeUser(observer: (user: User | null) => void): string {
        const id = generateId()
        this.observers.set(id, observer)
        observer(this.user)
        return id
    }

    removeObserver(id: string) {
        this.observers.delete(id)
    }
}

const instance = new UserStore()

export default instance

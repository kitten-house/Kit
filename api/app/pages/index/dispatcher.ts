import {AuthService} from "../../api/login/AuthService";
import UserStore from "../../store/user/UserStore";

export class IndexDispatcher extends BaseDispatcher {
    private authService = new AuthService()

    login(token: string) {
        this.authService.login(token)
            .subscribe(
                (response) => this.getUser(),
                (error) => UserStore.storeUser(null)
            )
    }

    getUser() {
        this.authService.getCurrentUser()
            .subscribe(
                (result) => {
                    UserStore.storeUser(result)
                },
                (error) => UserStore.storeUser(null)
            )
    }
}

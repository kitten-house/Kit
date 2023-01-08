import {sendAuthRequest, Subscribtion } from "../../core/client";

interface AuthApi {
    login(token: string): Subscribtion<LoginResponse>
    refresh(): Subscribtion<LoginResponse>
    getCurrentUser(): Subscribtion<User>
}

export class AuthService implements AuthApi {
    login(token: string): Subscribtion<LoginResponse> {
        const data: LoginRequest<LoginGoogleData> = { type: "google", data: { token: token } }

        return sendAuthRequest<LoginResponse>(
            { url: "/login", data: JSON.stringify(data) },
            (response) => {
                return response.data
            }
        );
    }

    refresh(): Subscribtion<LoginResponse> {
        return sendAuthRequest<LoginResponse>(
            { url: "/refresh" },
            (response) => {
                return response.data
            }
        );
    }

    getCurrentUser(): Subscribtion<User> {
        return sendAuthRequest<User>(
            { url: "/me",},
            (response) => {
                return response.data
            }
        )
    }
}

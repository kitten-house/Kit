interface LoginData {}

interface LoginGoogleData extends LoginData {
    token: string
}

interface LoginRequest<T extends LoginData> {
    type: "google" | "phone",
    data: T
}

interface LoginResponse {
    access_token: string
    refresh_token: string
}

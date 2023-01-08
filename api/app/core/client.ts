import axios, {
    AxiosError,
    AxiosHeaders,
    AxiosInstance,
    AxiosRequestConfig,
    AxiosResponse,
    RawAxiosRequestHeaders
} from "axios";
import {net} from "./disposial";
import createSubscribtion = net.createSubscribtion;
import Disposal = net.Disposal;

const instance: AxiosInstance = axios.create({
    withCredentials: true,
    timeout: 10000,
});

const isHandlerEnabled = (config: AxiosRequestConfig = {}): boolean => {
    if (config.headers instanceof AxiosHeaders) {
        let headers = config.headers as AxiosHeaders
        if (headers.get("X-Api-Request") !== null) {
            return false
        }
    } else {
        let headers = config.headers as RawAxiosRequestHeaders
        if (headers.common.get("X-Api-Request") !== null) {
            return false
        }
    }

    return process.env.NODE_ENV != "production"
};

const requestHandler = (request: AxiosRequestConfig) => {
    if (isHandlerEnabled(request)) {
        console.log("Request Interceptor", request);
    }
    return request;
};

const errorHandler = (error: AxiosError) => {
    if (isHandlerEnabled(error.config)) {
        console.log("Error Interceptor", error);

        if (error.response) {
            if (error.response.status === 401) {
                sendAuthRequest<LoginResponse>({ url: '/refresh' }, (r) => r.data)
                    .subscribe(
                        (as) => {  },
                        (_) => {  }
                    )
            }
        }
    }

    return Promise.reject({ ...error });
};

const successHandler = (response: AxiosResponse) => {
    if (isHandlerEnabled(response.config)) {
        console.log("Response Interceptor", response);
    }
    return response;
};

instance.interceptors.request.use(
    request => requestHandler(request),
);

instance.interceptors.response.use(
    response => successHandler(response),
    error => errorHandler(error)
);

export default instance;

export interface Subscribtion<T = any, E = any> {
    subscribe(onResult: (result: T) => void, onError: (error: E) => void): Disposal
}

export function sendAuthRequest<T = any, R = AxiosResponse<T>, D = T>(
    config: AxiosRequestConfig,
    mapper: (input: R) => D
): Subscribtion<D> {
    return sendRequest("http://localhost:8080", config, mapper)
}

export function sendApiRequest<T = any, R = AxiosResponse<T>, D = any>(
    config: AxiosRequestConfig,
    mapper: (input: R) => D
): Subscribtion<D> {
    return sendRequest("http://localhost:8080", config, mapper)
}

function sendRequest<T = any, R = AxiosResponse<T>, D = any>(
    baseUrl: string,
    config: AxiosRequestConfig,
    mapper: (input: R) => D
): Subscribtion<D>  {
    const sub = createSubscribtion()
    axios.AxiosHeaders
    const response = instance.request<T, R>(
        {
            cancelToken: sub.token,
            method: "POST",
            baseURL: baseUrl,
            ...config
        }
    )

    return {
        subscribe: function subscribe(onResult: (result: D) => void, onError: (error: any) => void): Disposal {
            response
                .then((response) => {
                    onResult(mapper(response))
                })
                .catch((error) => {
                    onError(error)
                })

            return sub
        }
    }
}

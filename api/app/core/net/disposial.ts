import axios, {CancelTokenSource} from "axios";

const controller = axios.CancelToken;

export module net {
    export type Disposal = CancelTokenSource

    export const createSubscribtion = (): CancelTokenSource => {
        return controller.source()
    }
}

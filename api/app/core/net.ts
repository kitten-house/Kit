import axios from "axios";

export let net = axios.create({
    baseURL: 'https://localhost:8080'
});

// net.defaults.headers.common['Authorization'] = AUTH_TOKEN;

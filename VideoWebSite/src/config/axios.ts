import axios from "axios";

const currentInstance = axios.create({
    // baseURL: "http://localhost:8080",
    // baseURL: "http://192.168.1.6:3333",
    withCredentials:true,
})

export default currentInstance;
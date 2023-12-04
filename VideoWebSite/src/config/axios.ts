import axios from "axios";

const currentInstance = axios.create({
    baseURL: "http://localhost:3333",
    // baseURL: "http://192.168.1.6:3333",
    withCredentials:true,
})

export default currentInstance;
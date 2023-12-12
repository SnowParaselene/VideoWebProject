import axios from "axios";

const currentInstance = axios.create({
    baseURL: 'XXXXXXXXXXXXXXXXXXXXX',
    withCredentials: true
})

export default currentInstance;
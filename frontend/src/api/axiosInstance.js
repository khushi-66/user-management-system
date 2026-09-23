import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:9090",
    headers: {
        "Content-Type": "application/json"
    }
});

// Prevent multiple session-expired events
let sessionExpiredHandled = false;


// ===============================
// REQUEST INTERCEPTOR
// ===============================
axiosInstance.interceptors.request.use(
    (config) => {

        const token = localStorage.getItem("token");

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },

    (error) => {
        return Promise.reject(error);
    }
);


// ===============================
// RESPONSE INTERCEPTOR
// ===============================
axiosInstance.interceptors.response.use(
    (response) => {
        return response;
    },

    (error) => {

        if (error.response?.status === 401) {

            console.log("401 Unauthorized → Session Expired");

            if (!sessionExpiredHandled) {

                sessionExpiredHandled = true;

                console.log("Dispatching jwt-expired event");

                window.dispatchEvent(
                    new Event("jwt-expired")
                );
            }
        }

        return Promise.reject(error);
    }
);

// Reset flag after successful login
export const resetSessionExpiredState = () => {
    sessionExpiredHandled = false;
};

export default axiosInstance;
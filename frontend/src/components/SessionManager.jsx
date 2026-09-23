
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { themeContext } from "./ThemeProvider";
import Modal from "./Modal";

export default function SessionManager() {
    const navigate = useNavigate();
    const { darkmode } = useContext(themeContext);

    const [showModal, setShowModal] = useState(false);

useEffect(
    () => { 
        const checkTokenExpiry = () => {
             const token = localStorage.getItem("token"); // No token → nothing to check 
             if (!token) { return; } 
             try { 
                // JWT payload decode
                 const payload = JSON.parse( atob(token.split(".")[1]) ); 
                 const expiryTime = payload.exp * 1000;
                  const currentTime = Date.now(); 
                  if (currentTime >= expiryTime) 
                    {
                         console.log(" JWT TOKEN EXPIRED");
                         
                         setShowModal(true);
                         } 
                        } catch (error) {
                        console.error("Invalid JWT token:", error);
                          } 
                    }; 
                    // Check immediately
                     checkTokenExpiry(); 
                     
                     // Check every 30 seconds
                      const interval = setInterval(
                        () => { checkTokenExpiry(); }, 30000);
                         return () => {
                             clearInterval(interval);
                             };
                             }, []);

    useEffect(() => {
        console.log("SESSION MANAGER MOUNTED");

        const handleSessionExpired = () => {
            console.log("SESSION EXPIRED EVENT RECEIVED");

            setShowModal(true);
        };

        window.addEventListener("jwt-expired", handleSessionExpired);

        return () => {
            window.removeEventListener("jwt-expired", handleSessionExpired);
        };
    }, []);

    const handleOk = () => {
        console.log("OK CLICKED");

        // Clear session data
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        localStorage.removeItem("notifications");

        // Close modal
        setShowModal(false);

        // Go to login
        navigate("/login", { replace: true });
    };

    return (
        <Modal
            show={showModal}
            onClose={handleOk}
            title="Session Expired"
            message="Your session has expired. Please login again."
            type="error"
            darkmode={darkmode}
        />
    );
}


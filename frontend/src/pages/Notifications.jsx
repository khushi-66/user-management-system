import React from "react";
export default function Notifications() {

    const notifications =
        JSON.parse(localStorage.getItem("notifications")) || [];

    const formatDate = (dateTime) => {
        if (!dateTime) return "";

        const date = new Date(dateTime);

        return date.toLocaleDateString("en-US", {
            weekday: "long",
            month: "long",
            day: "numeric",
            year: "numeric"
        });
    };

    const formatTime = (dateTime) => {
        if (!dateTime) return "";

        const date = new Date(dateTime);

        return date.toLocaleTimeString("en-US", {
            hour: "2-digit",
            minute: "2-digit",
            hour12: true
        });
    };

    return (
        <div className="container-fluid mt-5">

            {notifications.length === 0 ? (

                <div className="text-center">
                    <h3>No Notifications available</h3>
                </div>

            ) : (

                <div>

                    {notifications.map((notification, index) => (

                        <div
                            key={notification.notificationId || index}
                            className="card mb-3 border-0 shadow-sm"
                        >

                            <div className="card-body">

                                <div className="d-flex justify-content-between align-items-start">

                                    {/* Left Side */}
                                    <div className="pe-3">

                                        <h5 className="fw-bold text-primary mb-2">
                                            <i className="bi bi-bell-fill me-2"></i>
                                            {notification.title}
                                        </h5>

                                        <p className="card-text text-muted mb-0">
                                            {notification.message}
                                        </p>

                                    </div>

                                    {/* Right Side */}
                                    <div className="text-end flex-shrink-0">

                                        <div className="fw-semibold text-secondary">
                                            {formatDate(notification.time)}
                                        </div>

                                        <div className="small text-muted mt-1">
                                            {formatTime(notification.time)}
                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                    ))}

                </div>
            )}

        </div>
    );
}


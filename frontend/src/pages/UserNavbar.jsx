
import { Link, useNavigate } from "react-router-dom";
import { useContext, useEffect } from "react";
import darkLogo from "../assets/dark-logo.svg";
import lightLogo from "../assets/light-logo.png";
import { themeContext } from "../components/ThemeProvider";
import { ProfilePhoto } from "../components/ProfilePhoto";

export default function UserNavbar() {
    const { darkmode } = useContext(themeContext);
    const navigate = useNavigate();
const notifications = JSON.parse(
    localStorage.getItem("notifications")
) || [];
    const user = JSON.parse(localStorage.getItem("user"));
const hasUnreadNotifications = notifications.some(
    notification => notification.isRead === 'no'
);
useEffect(() => {
    const unreadNotifications = notifications.filter(n => n.isRead ==='no').length
console.log("unread notifications:", unreadNotifications);
console.log(notifications);
    console.log("Has unread notifications:", hasUnreadNotifications);
}, [notifications]);

    const handleLogout = () => {
        localStorage.removeItem("user");
        localStorage.removeItem("token");
        localStorage.removeItem("notifications");
        navigate("/login");
    };

    return (
        <nav
            className={`navbar navbar-expand-lg ${
                darkmode
                    ? "navbar-dark bg-dark"
                    : "navbar-light bg-light"
            } shadow-sm`}
        >

            <div className="container-fluid">

                {/* ================= LOGO ================= */}
                <Link to="/user" className="navbar-brand">
                    <img
                        className={
                            darkmode
                                ? "border border-secondary border-2 rounded-5"
                                : ""
                        }
                        style={{
                            height: darkmode ? "30px" : "45px"
                        }}
                        src={darkmode ? darkLogo : lightLogo}
                        alt="Logo"
                    />
                </Link>


                {/* ================= MOBILE TOGGLER ================= */}
                <button
                    className="navbar-toggler me-2"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#userNavbar"
                    aria-controls="userNavbar"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>


                {/* ================= PROFILE PHOTO ================= */}
                <div className="d-lg-none">
                    <div className="dropdown">

                        <button
                            className="btn p-0 border-0"
                            type="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        >
                            <ProfilePhoto
                                profileUrl={user?.profile?.profileUrl}
                                username={user?.name}
                                size="42px"
                            />
                        </button>

                        <ul
                            className={`dropdown-menu dropdown-menu-end shadow ${
                                darkmode ? "dropdown-menu-dark" : ""
                            }`}
                        >

                            <li>
                                <Link
                                    className="dropdown-item"
                                    to="/profile"
                                >
                                    <i className="bi bi-person me-2"></i>
                                    My Profile
                                </Link>
                            </li>

                            <li>
                                <Link
                                    className="dropdown-item"
                                    to="/change-password"
                                >
                                    <i className="bi bi-key me-2"></i>
                                    Change Password
                                </Link>
                            </li>

                            <li>
                                <hr className="dropdown-divider" />
                            </li>

                            <li>
                                <button
                                    className="dropdown-item text-danger"
                                    onClick={handleLogout}
                                >
                                    <i className="bi bi-box-arrow-right me-2"></i>
                                    Logout
                                </button>
                            </li>

                            <li>
                                <hr className="dropdown-divider" />
                            </li>

                            <li>
                                <Link
                                    className="dropdown-item text-danger"
                                    to="/delete-account"
                                >
                                    <i className="bi bi-trash3 me-2"></i>
                                    Delete Account
                                </Link>
                            </li>

                        </ul>

                    </div>
                </div>


                {/* ================= NAVIGATION ================= */}
                <div
                    id="userNavbar"
                    className="collapse navbar-collapse"
                >

                    <ul className="navbar-nav ms-lg-3">

                        {/* Dashboard */}
                        <li className="nav-item ">

                            <Link
                                className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`}
                                to="/user"
                            >
                                <i className="bi bi-house-door-fill text-primary me-2 "></i>
                                Dashboard
                            </Link>

                        </li>


                        {/* Notifications */}
                      <li className="nav-item">
    <Link
        className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`}
        to="/user/notifications"
    >
        <span
            className="position-relative d-inline-block"
            style={{ lineHeight: 1 }}
        >
            <i className="bi bi-bell-fill text-primary fs-5"></i>

            {hasUnreadNotifications && (
                <span
                    className="position-absolute bg-danger border border-light rounded-circle"
                    style={{
                        width: "10px",
                        height: "10px",
                        top: "-3px",
                        right: "1px"
                    }}
                ></span>
            )}
        </span>

        <span className="ms-2">
            Notifications
        </span>
    </Link>
</li>


                        {/* Activity */}
                        <li className="nav-item">

                            <Link
                                className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`}
                                to="/user/activity"
                            >
                                <i className="bi bi-clock-history text-primary me-2"></i>
                               My  Activity
                            </Link>

                        </li>

                    </ul>

                </div>


                {/* ================= DESKTOP PROFILE ================= */}
                <div className="d-none d-lg-block ms-3">

                    <div className="dropdown">

                        <button
                            className="btn p-0 border-0 d-flex align-items-center gap-2"
                            type="button"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                        >

                            <ProfilePhoto
                                profileUrl={user?.profile?.profileUrl}
                                username={user?.name}
                                size="45px"
                            />

                            <span
                                className={
                                    darkmode
                                        ? "text-light"
                                        : "text-dark"
                                }
                            >
                                {user?.name}
                            </span>

                            <i
                                className={`bi bi-chevron-down ${
                                    darkmode
                                        ? "text-light"
                                        : "text-dark"
                                }`}
                            ></i>

                        </button>


                        <ul
                            className={`dropdown-menu dropdown-menu-end shadow ${
                                darkmode ? "dropdown-menu-dark" : ""
                            }`}
                        >

                            {/* My Profile */}
                            <li>
                                <Link
                                    className="dropdown-item"
                                    to="/profile"
                                >
                                    <i className="bi bi-person-circle me-2"></i>
                                    My Profile
                                </Link>
                            </li>


                            {/* Change Password */}
                            <li>
                                <Link
                                    className="dropdown-item"
                                    to="/change-password"
                                >
                                    <i className="bi bi-key-fill me-2"></i>
                                    Change Password
                                </Link>
                            </li>


                            <li>
                                <hr className="dropdown-divider" />
                            </li>


                            {/* Logout */}
                            <li>
                                <button
                                    className="dropdown-item text-danger"
                                    onClick={handleLogout}
                                >
                                    <i className="bi bi-box-arrow-right me-2"></i>
                                    Logout
                                </button>
                            </li>


                            {/* Delete Account */}
                            <li>
                                <Link
                                    className="dropdown-item text-danger"
                                    to="/delete-account"
                                >
                                    <i className="bi bi-trash3-fill me-2"></i>
                                    Delete Account
                                </Link>
                            </li>

                        </ul>

                    </div>

                </div>

            </div>

        </nav>
    );
}






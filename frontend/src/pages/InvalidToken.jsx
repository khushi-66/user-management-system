import { Link } from "react-router-dom";
import lightLogo from "../assets/light-logo.png";

export default function InvalidToken() {
    return (
        <div
            className="container d-flex justify-content-center align-items-center"
            style={{ minHeight: "100vh" }}
        >
            <div
                className="card shadow-lg border-0 rounded-4 p-5 text-center"
                style={{ maxWidth: "500px", width: "100%" }}
            >
                <img
                    src={lightLogo}
                    alt="Logo"
                    style={{ height: "60px" }}
                    className="mx-auto mb-3"
                />

                <h1 className="display-3 text-danger">❌</h1>

                <h3 className="fw-bold mb-3">
                    Invalid Reset Link
                </h3>

                <p className="text-muted">
                    The password reset link is invalid or has already been used.
                    Please request a new password reset link.
                </p>

                <div className="d-grid gap-2 mt-4">
                    <Link
                        to="/forgot-password"
                        className="btn btn-primary"
                    >
                        Request New Reset Link
                    </Link>

                    <Link
                        to="/login"
                        className="btn btn-outline-secondary"
                    >
                        Back to Login
                    </Link>
                </div>
            </div>
        </div>
    );
}
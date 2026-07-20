import { Link } from "react-router-dom";
import lightLogo from "../assets/light-logo.png";

export default function ExpiredToken() {
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

                <h1 className="display-3 text-warning">⏳</h1>

                <h3 className="fw-bold mb-3">
                    Reset Link Expired
                </h3>

                <p className="text-muted">
                    This password reset link has expired for security reasons.
                    Please request a new password reset email to continue.
                </p>

                <div className="alert alert-warning mt-3">
                    <strong>Security Tip:</strong> Password reset links are valid
                    for a limited time to help protect your account.
                </div>

                <div className="d-grid gap-2 mt-4">
                    <Link
                        to="/forgot-password"
                        className="btn btn-primary"
                    >
                        Send New Reset Link
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
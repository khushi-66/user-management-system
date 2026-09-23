import { Link } from "react-router-dom";
import Footer from "./Footer";

export function AlreadyVerified()
{
   return (
    <div className="container text-center mt-5">
      <div className="card shadow p-5">
        <h1 className="text-primary">ℹ️ Email Already Verified</h1>

        <p className="mt-3">
          Your email address has already been verified.
        </p>

        <p className="text-muted">
          You can sign in using your registered email and password.
        </p>

        <Link to="/login" className="btn btn-primary mt-3">
          Go to LogIn
        </Link>
      </div>
    </div>
  );
}
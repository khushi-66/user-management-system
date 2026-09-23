import { Link } from "react-router-dom";

export function Expired()
{
   return (
    <div className="container text-center mt-5">
      <div className="card shadow p-5">
        <h1 className="text-warning">⏰ Verification Link Expired</h1>

        <p className="mt-3">
          Your verification link has expired.
        </p>

        <p className="text-muted">
          Please request a new verification email.
        </p>

        <Link to="/resend-verification" className="btn btn-warning mt-3">
          Resend Verification Email
        </Link>
      </div>
    </div>
  );
}
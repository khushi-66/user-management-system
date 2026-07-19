import { Link } from "react-router-dom";

  export function Invalid()
{
   return (
    <div className="container text-center mt-5">
      <div className="card shadow p-5">
        <h1 className="text-danger">❌ Invalid Verification Link</h1>

        <p className="mt-3">
          This verification link is invalid or has been modified.
        </p>

        <p className="text-muted">
          Please request a new verification email.
        </p>
  
        <Link to="/resend-verification" className="d-inline-block btn btn-danger bg-subtle mt-3">
          Resend Verification Email
        </Link>
      </div>
    </div>
  );
}
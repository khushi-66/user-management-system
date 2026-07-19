export function Verified()
{
     return (
    <div className="container text-center mt-5">
      <div className="card shadow p-5">
        <h1 className="text-success">✅ Email Verified</h1>

        <p className="mt-3">
          Your email has been verified successfully.
        </p>

        <p className="text-muted">
          You can now sign in to your account.
        </p>

        <Link to="/login" className="btn btn-success mt-3">
          Go to LogIn
        </Link>
      </div>
    </div>
  );
}
import { useContext } from "react";
import { themeContext } from "../components/ThemeProvider";

export default function Terms() {
  const { darkmode } = useContext(themeContext);

  return (
    <div
      className={`container mt-4 p-4 rounded shadow-sm ${
        darkmode ? "bg-dark text-light" : "bg-light"
      }`}
      style={{ maxWidth: "900px" }}
    >
      <h2 className="text-center mb-4">Terms & Conditions</h2>

      <div
        className="p-3 border rounded"
        style={{ maxHeight: "400px", overflowY: "auto" }}
      >
        <p>
          Welcome to our platform. By creating an account and using our services,
          you agree to the following terms and conditions.
        </p>

        <h5>1. User Responsibilities</h5>
        <p>
          You agree to provide accurate and complete information during signup.
          You are responsible for maintaining the confidentiality of your account
          credentials.
        </p>

        <h5>2. Account Security</h5>
        <p>
          You are responsible for all activities that occur under your account.
          Any unauthorized use must be reported immediately.
        </p>

        <h5>3. Acceptable Use</h5>
        <p>
          You agree not to misuse the platform, including sending spam, abusive
          content, or violating any applicable laws.
        </p>

        <h5>4. Email Verification</h5>
        <p>
          Your account will remain inactive until you verify your email address.
          Access to certain features may be restricted until verification is
          complete.
        </p>

        <h5>5. Limitation of Liability</h5>
        <p>
          We are not responsible for any damages resulting from misuse of the
          platform or unauthorized access to your account.
        </p>

        <h5>6. Changes to Terms</h5>
        <p>
          We reserve the right to update these terms at any time. Continued use
          of the platform implies acceptance of updated terms.
        </p>

        <h5>7. Contact</h5>
        <p>
          For any questions regarding these terms, please contact our support
          team.
        </p>
      </div>

      <div className="text-center mt-3">
        <p className="small text-muted">
          Last updated: April 2026
        </p>
      </div>
    </div>
  );
}
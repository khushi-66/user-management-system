import { useContext } from "react"
import {themeContext} from '../components/ThemeProvider';
 
export default function About() {

  const { darkmode } = useContext(themeContext);

  const cardClass = `card h-100 p-3 shadow-sm ${
    darkmode ? "bg-secondary text-light" : ""
  }`;

  return (
    <div className={`container py-4 ${darkmode ? "text-light" : ""}`}>

      {/* HEADER */}
      <h1 className="text-center mb-3">
        About User Management System
      </h1>

      <p className="text-center mb-4">
        A secure, scalable and modern system for managing users with authentication,
        authorization and analytics.
      </p>

      <div className="text-center mb-5">
        <button className={darkmode ? "btn btn-secondary" : "btn btn-primary"}>
          Get Started
        </button>
      </div>

      {/* FEATURES CARDS */}
      <div className="row g-4 mb-5">

        <div className="col-md-4">
          <div className={cardClass}>
            <h5>🔐 Security First</h5>
            <p>JWT authentication with role-based access</p>
          </div>
        </div>

        <div className="col-md-4">
          <div className={cardClass}>
            <h5>⚡ High Performance</h5>
            <p>Optimized backend using Spring Boot</p>
          </div>
        </div>

        <div className="col-md-4">
          <div className={cardClass}>
            <h5>📊 Smart Management</h5>
            <p>Admin dashboard with analytics</p>
          </div>
        </div>

      </div>

      {/* WHY SECTION */}
      <div className="mb-5">
        <h3>Why This System?</h3>
        <ul>
          <li>Manual user management is risky and slow</li>
          <li>No centralized authentication system</li>
          <li>Security and scalability issues in traditional apps</li>
        </ul>
      </div>

      {/* TECH STACK */}
      <div>
        <h3 className="mb-3">Tech Stack</h3>

        <div className="row g-3 text-center">

          {["Spring Boot", "React", "JWT Auth", "Bootstrap", "MySQL"].map((tech, i) => (
            <div className="col-6 col-md-2" key={i}>
              <div className={`p-2 rounded ${darkmode ? "bg-dark" : "bg-light"}`}>
                {tech}
              </div>
            </div>
          ))}

        </div>
      </div>

    </div>
  );
}
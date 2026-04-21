import { Link } from "react-router-dom";
import { useContext } from "react";
import { themeContext } from "../components/ThemeProvider";

export default function Footer() {
  const { darkmode } = useContext(themeContext);

  return (
    <footer className={`${darkmode ? "bg-secondary text-light" : "footer-light"} pt-4 pb-3 mt-5`}>
      <div className="container">
        <div className="row">

          {/* Project Info */}
          <div className="col-md-4 mb-3">
            <h5 className="fw-bold">User Management System</h5>
            <p style={{ fontSize: "14px" }}>
              A secure web application to manage users, roles, and authentication using modern technologies.
            </p>
          </div>

          {/* Navigation */}
          <div className="col-md-3 mb-3">
            <h6 className="fw-semibold">Navigation</h6>
            <ul className="list-unstyled">
              <li><Link to="/">Home</Link></li>
              <li><Link to="/features">Features</Link></li>
              <li><Link to="/contact">Contact</Link></li>
            </ul>
          </div>

          {/* Recruiter Section */}
          <div className="col-md-3 mb-3">
            <h6 className="fw-semibold">For Recruiters</h6>
            <ul className="list-unstyled">
                
              <li>
                <a 
                  href="https://github.com/khushi-66/user-managements-system" 
                  target="_blank" 
                  rel="noreferrer">
                  🔗 View Source Code
                </a>
              </li>
              <li>
                <a href="sahujii8277@gmail.com">
                  📧 Contact Me
                </a>
              </li>

              <a href="https://github.com/khushi-66" target="_blank" rel="noreferrer">
    <i className="bi bi-github fs-5 ms-2"></i>
  </a>

  <a href="https://linkedin.com/in/khushi-sahu989718b" target="_blank" rel="noreferrer">
    <i className="bi bi-linkedin fs-5 ms-2"></i>
  </a>

  <a href="https://twitter.com/khushi_666" target="_blank" rel="noreferrer">
    <i className="bi bi-twitter fs-5 ms-2"></i>
  </a>

  <a href="https://facebook.com/khushi_123" target="_blank" rel="noreferrer">
    <i className="bi bi-facebook fs-5 ms-2"></i>
  </a>

            </ul>
          </div>

          {/* Tech Stack */}
          <div className="col-md-2 mb-3">
            <h6 className="fw-semibold">Tech</h6>
            <p style={{ fontSize: "13px" }}>
              React <br />
              Bootstrap <br />
              JWT Auth
              <br/>
              SpringBoot
            </p>
          </div>

        </div>

        <hr />

        {/* Bottom */}
        <div className="text-center" style={{ fontSize: "13px" }}>
          © {new Date().getFullYear()} UMS | Built by You
        </div>
      </div>
    </footer>
  );
}
import { Link } from "react-router-dom";
import { useContext } from "react";
import darkLogo from "../assets/dark-logo.svg";
import lightLogo from "../assets/light-logo.png";
import { themeContext } from "../components/ThemeProvider";

export default function Navbar() {
  const {darkmode, setDarkMode} = useContext(themeContext);

  return (
    <nav className={`navbar navbar-expand-lg ${darkmode ? "navbar-dark bg-dark" : "navbar-light bg-light"} shadow-sm`}>
      <div className="container-fluid">

        <Link  className={`${darkmode?"border-secondary border-2 border  rounded-5":""}mb-0 navbar-brand d-flex align-items-center`}>
          <img
            style={{height:darkmode? "30px":"45px"} }
            src={darkmode ? darkLogo : lightLogo}
            alt="Logo"
          />
        </Link>

       
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbar"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        
        <div id="navbar" className="collapse navbar-collapse">
          <ul className={`${darkmode?"border-white bg-secondary btn-outline-white px-3 py-2 rounded-3":""} navbar-nav ms-auto align-items-lg-center`}>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
               to="/">Home</Link>
            </li>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
               to="/about">About</Link>
            </li>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
               to="/features">Features</Link>
            </li>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
              
              to="/contact">Contact</Link>
            </li>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
               to="/login">Login</Link>
            </li>

            <li className="nav-item">
              <Link className={`${ darkmode?"link-light":"link-dark"} mx-2 link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover`} 
               to="/signup">Signup</Link>
            </li>

            <li className="nav-item ms-3">
              <button
                className="btn btn-secondary btn-sm"
                onClick={() => setDarkMode(!darkmode)}
              >
                {darkmode ? "Light" : "Dark"}
              </button>
            </li>

          </ul>
        </div>

      </div>
    </nav>
  );
}
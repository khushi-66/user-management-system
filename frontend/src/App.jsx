
import { Link, Outlet, useLocation, useNavigate } from 'react-router-dom'
import Navbar from './pages/Navbar';
import Footer from './pages/Footer';
import ums from "./assets/ums.jpg"
import { useContext } from 'react';
import { themeContext } from './components/ThemeProvider';

  function App() {
   const location=useLocation();
   const navigate=useNavigate();
   console.log(location.pathname);
   const{darkmode,setDarkMode}=useContext(themeContext);
  return (
      <div className={ `${darkmode ? "bg-dark text-white": "" } container-fluid d-flex flex-column min-vh-100`}>
        <div className="row">
          <div className="col-12">
           <Navbar/>
           </div>
           </div>

             {
               location.pathname === '/'?(
                <>
               <div className="row align-items-center mt-3">

    {/* Left Side */}
    <div className="col-lg-6 text-center text-lg-start">
      <h1 className="fw-bold">
        Manage Users <span className={`${darkmode?"text-light":"text-primary"}`}>Efficiently</span>
      </h1>

      <p className={`${darkmode?"text-light":"text-muted"}`}>
        A powerful and secure User Management System designed to simplify
        user authentication, role-based access, and data management.
        Built for scalability and real-world applications.
      </p>

      <div className="mt-4 d-flex flex-sm-row">
        <div>
        <button className={`${darkmode?"btn-secondary":"btn btn-primary"} btn me-3`}  onClick={()=>navigate("/signup")}>Get Started</button>
        </div>
        <div className="">

        
        <button onClick={()=>navigate("/about")} className={` ${darkmode?"btn-outline-light":"btn-outline-secondary"} btn`}>Learn More</button>
      </div>
      </div>
    </div>

    {/* Right Side Image Card */}
    <div className="col-lg-6 mt-4 mt-lg-0">
      <div className="card border-0 shadow-sm p-3">
        <img
          src={ums}
          alt="User Management"
          className="img-fluid rounded"
        />
      </div>
    </div>
</div></>)
                 :(
                    <Outlet/>
                 )
                  }
           
         
          
         <div className="mt-auto"><Footer/>
          </div>

      </div>
      
    
  );
}

export default App;

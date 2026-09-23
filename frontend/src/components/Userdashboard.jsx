import { useContext, useEffect, useState } from "react";
import {  ProfilePhoto } from "./ProfilePhoto";
import UserNavbar from "../pages/UserNavbar";
import Footer from "../pages/Footer";
import { themeContext } from "./ThemeProvider";
import { Outlet, useLocation, useNavigate } from "react-router-dom";


export default function Userdashboard (){
    const[profileUrl,setProfileUrl]=useState(null);  
   const user= JSON.parse(localStorage.getItem("user"));
   const token= localStorage.getItem("token");
   const notifications = JSON.parse(
    localStorage.getItem("notifications")
) || [];
const[profileCompletion,setProfileCompletion]=useState(0);
const formatdate = (dateTime) => {
    if (!dateTime) return "No login history";

    const date = new Date(dateTime);

    const datePart = date.toLocaleDateString("en-US", {
        weekday: "long",
        month: "long",
        day: "numeric",
        year: "numeric"
    });

    const timePart = date.toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true
    });

    return `${datePart} `;
}
   const formattime = (dateTime) => {
    if (!dateTime) return "No login history";

    const date = new Date(dateTime);

    const datePart = date.toLocaleDateString("en-US", {
        weekday: "long",
        month: "long",
        day: "numeric",
        year: "numeric"
    });

    const timePart = date.toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true
    });

    return `${timePart} `;
}    

const calculateProfileCompletion = (profile) => {
    if (!profile) return 0;

    const fields = [
        profile.dob,
        profile.address,
        profile.profileUrl,
        profile.bio,
        profile.profession,
        profile.age
    ];

    const completedFields = fields.filter(
        field => field !== null && field !== undefined && field !== ""
    ).length;
 //setProfileCompletion(Math.round((completedFields / fields.length) * 100));
 setProfileCompletion(45);
    return Math.round((completedFields / fields.length) * 100);
};

useEffect(() => {
    calculateProfileCompletion(user.profile);
}, [user.profile]);

   const {darkmode, setDarkMode} = useContext(themeContext);
   const location=useLocation();
   
   console.log(user);
    return(
        <>
        <div className={ `${darkmode ? "bg-dark text-white": "" } container-fluid d-flex flex-column min-vh-100`}>
        <div className="row">
          <div className="col-12">
           <UserNavbar/>
           </div>
           </div>

             {
               location.pathname === '/user'?(
                <>
                 {/* ########################### AI profile based summary ############################### */}
             <div className="row">

             </div>
             {/* ########################### info cards ############################### */}
              <div className="row gap-3 justify-content-center mt-4">
                <div className="card col-sm-3 text-center mx-4 col-12 bg-light bg-opacity-10 shadow-sm">
                  <div className="card-body">
                    <h5 className="card-title"><i className="bi bi-person-fill me-1 text-primary"></i>Profile</h5>
                     <div
            className="progress mt-3"
            style={{ height: "10px" }}
            role="progressbar"
            aria-valuenow={profileCompletion}
            aria-valuemin="0"
            aria-valuemax="100"
        >
            <div
                className="progress-bar progress-bar-striped progress-bar-animated bg-primary"
                style={{ width: `${profileCompletion}%` }}
            ></div>
        </div>
                    <p className="card-text">Profile Completion: {profileCompletion}%<br></br>{profileCompletion < 100 ? "Complete your profile!" : "Profile complete !!"}</p>
                  </div>
                </div>
               <div className="card col-sm-3 text-center mx-4 col-12 bg-light bg-opacity-10 shadow-sm">
                  <div className="card-body">
                    <h5 className="card-title"><i className="bi bi-bell-fill me-1 text-primary"></i>Notifications</h5>
                    <p className="card-text">{notifications.filter(n => !n.read).length } unread notifications</p>
                  </div>
                </div>
                <div className="card col-sm-3 text-center mx-4 col-12 bg-light bg-opacity-10 shadow-sm">
                  <div className="card-body">
                    <h5 className="card-title"><i className="bi bi-clock-fill me-1 text-warning"></i>Last Login</h5>
                    <p className="card-text">{formatdate(user.lastLogin)} <br></br>at {formattime(user.lastLogin)}</p>
                    
                  </div>
                </div>
              </div>
             {/* ########################### skills advice ############################### */}
             <div className="row">

             </div>
               </>)
                 :(
                    <Outlet/>
                 )
                  }
           
         
          
         <div className="mt-auto"><Footer/>
          </div>
      </div>
         </>
    )
}
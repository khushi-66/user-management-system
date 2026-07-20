import { useState } from "react";
import Modal from "./Modal";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
    import lightLogo from "../assets/light-logo.png";

export default function Login(){
    const navigate=useNavigate();
    const[showmodal,setShowModal]=useState(false);
    const[modaldata,setModalData]=useState({
             type:"success",msg:"",title:"" });
    const [token,setToken]=useState();
    const[email,setEmail]=useState();
    const[loading,setLoading]=useState(false);
    const[isAuthenticate,setIsAuthenticate]=useState(false)
    const [password,setPassword]=useState();
    const [user,setUser]=useState({"name":"","id":"","role":""});


// ######################################## field validation ###################
    const handlevalidate=async(field,e)=>{
        if(field === 'email'){
            
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                  if(!emailRegex.test(e)){
                      setModalData({
                        type:"error",msg:"Email you entered is invalid",title:"Validation erorr"
                    });
                    setShowModal(true);
                  };
            if(!e  ){
                    setModalData({
                        type:"error",msg:"Email cannot be empty please enter your correct email address to login ",title:"validation error"
                    });
                    setShowModal(true);
                  }
                }
                  if(field === 'password'){
                  if(!e){
                    setModalData({
                        type:"error",msg:"password cannot be empty please enter your correct password",title:"validation erorr"
                    });
                    setShowModal(true);
                }}
                  }
// ######################################## field validation ########################
    

// ######################################## handle login #########################
             const handleLogin=async(e)=>{
                  e.preventDefault();
                  try{
                    setLoading(true);
                 const resp= await axios.post('http://localhost:9090/signin' ,{
                         email:email,
                        password:password
                    });
                    if (resp.data.Status === "success") {
                    const loggedInUser = {
    id: resp.data.userid,
    name: resp.data.username,
    role: resp.data.role
};
localStorage.setItem("token", resp.data.token);
localStorage.setItem("user", JSON.stringify(loggedInUser));
setUser(loggedInUser);
                          }
                          const u=JSON.parse(localStorage.getItem("user"));
                           console.log(u.id);
                          console.log(resp.data.role);
                           console.log(resp.data.userid);
                            console.log(resp.data.Message);
                             console.log(resp.data.username);
                    setEmail('');
                    setPassword('');
                    setModalData({
          type:"success",
           msg:
`Congratualations !! ${resp.data.username} you are logged in successfully`,
title:"Login Successfull"
        })
        setShowModal(true);
                }
               catch (err) {
    let message = "Something went wrong. Please try again.";

    if (err.response) {
        message = err.response.data?.message || "Login failed.";
    } else if (err.request) {
        message = "Unable to connect to the server. Please check your internet connection.";
    }

    setModalData({
        type: "error",
        title: "Login Failed",
        msg: message
    });

    setShowModal(true);
} finally {
    setLoading(false);
}



             }
// ######################################## handle login ###################
    
return(
         <>
          <div className="container d-flex justify-content-center">

{/* ################################ Popup Modal ################################ */}
               <Modal
                    show={showmodal}
                 onClose={()=>{setShowModal(false)
                    if(user.role ==='USER'){
                        navigate("/user");
                    }if(user.role ==='ADMIN'){
                        navigate("/admin");
                    }}}
                 title={modaldata.title}
                 message={modaldata.msg}
                 type = {modaldata.type}
                 darkmode={false}
                    />
{/* // ######################################## field validation ################### */}


                <div className="card mt-4 shadow p-4 m-4 " style={{width:"42%"}}>

{/* ###################################  Logo ################################### */}
                   <Link  className={`ps-0  pt-0 mb-0 navbar-brand d-flex justify-content-start`}>
                             <img
                               style={{height:"50px"} }
                               src={lightLogo}
                               alt="Logo"
                             />
                    </Link>
{/* ####################################   Logo  #################################### */}


                    <h2 className="text-primary text-center  mb-4">Login</h2>
                    <form onSubmit={handleLogin}>
          <div className="mb-3 row align-items-center">
            <label htmlFor="email" className="col-sm-4 col-form-label">
             Email 
            </label>
          <div className="col-sm-8">
              <input id="email" name="email"
                type="email"  required value={email} onChange={
                    (e)=>setEmail(e.target.value)}
                     onBlur={ ()=>{  handlevalidate("email",email)}}
                className="form-control"
                placeholder="Enter Your email or username"
              />
            </div>
          </div>
          <div className="mb-3 row align-items-center">
            <label required htmlFor="password" className="col-sm-4 col-form-label">
             Password
            </label>
          <div className="col-sm-8 ">
              <input id="password"
                type="password" name="password"
                className="form-control" value={password}
                placeholder="Enter Your Password" 
                onChange={
                    (e)=>setPassword(e.target.value)}
                     onBlur={ ()=>{  handlevalidate("password",password)}}
              />
            </div>
            <div className="d-flex justify-content-center">
<button disabled={loading}type="submit" className="mb-0 mt-4 btn btn-primary px-4 text-center">{loading ? <>
      <span className="spinner-border spinner-border-sm me-2"></span>
     Logging in...
    </>
    :"Login"}</button>
            </div>

            <div className="d-flex justify-content-start">
                <Link to="/forgot-password" className="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" >Forgot Password</Link>
            </div>
        </div>
</form>
                </div>
          </div>
</>
     );
}
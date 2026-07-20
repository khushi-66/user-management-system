import { Link, useNavigate } from "react-router-dom";
import lightLogo from "../assets/light-logo.png";
import Modal from "../components/Modal";
import { useContext, useState } from "react";
import axios from "axios";
import { themeContext } from "../components/ThemeProvider";
export default function ForgotPassword() {
const[showmodal,setShowModal]=useState(false);
const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:"" });
const {darkmode}=useContext(themeContext);
const[loading,setLoading]=useState(false);
    const [email, setEmail] = useState("");
const[canLogin,setCanLogin]=useState(false);
const navigate=useNavigate();

const validateEmail=async(e)=>{
        if(!e.trim()  ){
                    setModalData({
                        type:"error",msg:"Email cannot be empty please enter your correct email address to send password reset mail ",title:"Validation Error"
                    });
                    setShowModal(true);
                    return false;
                  }
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                  if(!emailRegex.test(e)){
                      setModalData({
                        type:"error",msg:"Email you entered is invalid",title:"Validation erorr"
                    });
                    setShowModal(true);
                  };
            
                }
// ######################################## field validation ########################
    
    const handleSubmit = async(e) => {
        e.preventDefault();
        setLoading(true);
        try{
            await axios.post(
        "http://localhost:9090/reset-password",{
            email:email
        }
     );
     setEmail("");
     setModalData({
          type:"success",
           msg:
"Password Reset link has been sent to your registered email address You can change your password to login your account",
title:"Action Required"
        })
        setShowModal(true);
    }
    catch(err){
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong please try again later",title:"Password  reset link was not sent",
        })
        setShowModal(true);
         
        } finally {
          setLoading(false);
        }
        console.log(email);
    }

  return (
    <div    style={{ minHeight: "70vh" }} className="container mt-5 d-flex align-items-center justify-content-center align-items-center">
      
      <Modal
           show={showmodal}
        onClose={()=>{setShowModal(false) }}
        title={modaldata.title}
        message={modaldata.msg}
        type = {modaldata.type}
        darkmode={false}
           />
      
      <div className="card shadow p-4 pt-0" style={{ width: "450px" }}>
         <Link  className={`ps-0   mb-0 navbar-brand d-flex align-items-center`}>
          <img
            style={{height:"50px"} }
            src={lightLogo}
            alt="Logo"
          />
           
        </Link>
        <h3 className="text-center mb-4">Forgot Password</h3>
<p className="text-center text-muted mb-4">
    Enter your registered email address and we'll send you a password reset link.
</p>
        <form  onSubmit={handleSubmit}>

          <div className="mb-3 row align-items-center">
            <label htmlForm="psb" className="col-sm-4 col-form-label">
               Email
            </label>

            <div className="col-sm-8">
              <input
                type="email" value={email} 
                onChange={(e)=>{setEmail(e.target.value)}} 
                onBlur={(e)=>{validateEmail(e.target.value)}}
                className="form-control" name="psb"
                placeholder="Enter email address"
              />
            </div>
          </div>
          <div className="text-center">
            <button type="submit" className="btn btn-primary">
              {loading ? (
        <>
            <span className="spinner-border spinner-border-sm me-2"></span>
            Sending...
        </>):"Send Reset Link"}
         </button>
          </div>
        </form>
<div className="text-center mt-3">
    <Link to="/login" className="text-decoration-none">
        ← Back to Login
    </Link>
</div>

<div className="alert alert-light mt-4">
    <small>
        <strong>Note:</strong>
        <ul className="mb-0 mt-2">
            <li>Reset link expires in 15 minutes.</li>
            <li>Check your Spam folder if you don't receive the email.</li>
            <li>Use the same email that is registered with your account.</li>
        </ul>
    </small>
</div>
      </div>
    </div>
  );
}
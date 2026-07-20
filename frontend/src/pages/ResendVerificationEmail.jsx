import { Link, useNavigate } from "react-router-dom";
import { useContext, useState } from "react";
import axios from "axios";
import Modal from "../components/Modal";
import { themeContext } from "../components/ThemeProvider";

export default function ResendVerificationEmail() {
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
                        type:"error",msg:"Email cannot be empty please enter your correct email address to resend verification mail ",title:"validation error"
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
        "http://localhost:9090/resend-verificationmail",{
            email:email
        }
     );
     setModalData({
          type:"success",
           msg:
"A  New Email verification link has been sent to your registered email address Please verify your email to activate your account",
title:"Action Required"
        })
        setShowModal(true);
    }
    catch(err){
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:"Verification Email not sent",
        })
        setShowModal(true);
         
        } finally {
          setLoading(false);
        }
        console.log(email);
    }

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

<Modal
     show={showmodal}
  onClose={()=>{setShowModal(false)
    if(canLogin === true){
       navigate("/login");
    }
  }}
  title={modaldata.title}
  message={modaldata.msg}
  type = {modaldata.type}
  darkmode={false}
     />

                <div className="col-md-5">

                    <div className="card shadow-lg border-0">

                        <div className="card-body p-4">

                            <h2 className="text-center text-primary mb-3">
                                📧 Resend Verification Email
                            </h2>

                            <p className="text-center text-muted">
                                Didn't receive your verification email?
                                Enter your registered email address below.
                            </p>

                            <form onSubmit={handleSubmit}>

                                <div className="mb-3">

                                    <label className="form-label">
                                        Email Address
                                    </label>

                                    <input
                                        type="email"
                                        className="form-control"
                                        placeholder="Enter Email"
                                        value={email}
                                        onChange={(e)=>{setEmail(e.target.value)}}
                                        onBlur={(e)=>{validateEmail(e.target.value)}}
                                        required
                                    />

                                </div>

                                <button
                                    className="btn btn-primary w-100">

                                    Resend Verification Email

                                </button>

                            </form>

                            <hr />

                            <div className="text-center">

                                <Link to="/login">
                                    Back to LogIn
                                </Link>

                            </div>

                            <div className="mt-4">

                                <small className="text-muted">

                                    <b>Tips</b>

                                    <ul className="mt-2">

                                        <li>Check your Spam folder.</li>

                                        <li>MakSe sure your email is correct.</li>

                                        <li>Verification link expires after 24 hours.</li>

                                    </ul>

                                </small>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}
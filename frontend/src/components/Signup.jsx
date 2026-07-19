import { useContext ,useRef,useState} from "react";
import { themeContext } from "../components/ThemeProvider";
import './signup.css';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';
import Modal from '../components/Modal';
export default function Signup(){
    const[canhide,setCanHide]=useState(false);
    const[isOTPSent,setIsOTPSent]=useState(false);
    const[isPhoneVerified,setIsPhoneVerified]=useState(false);
    const[timer,setTimer]=useState(60);
    const[canResend,setCanReSend]=useState(false);
    const[showmodal,setShowModal]=useState(false);
    const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:"" });
    const [termsError, setTermsError] = useState("");
    const {darkmode}=useContext(themeContext);
    const[form,setForm]=useState({ name:"",email:"",phone:"",password:"",confirmPsb:""});
    const[loading,setLoading]=useState(false);
    const[errors ,setErrors]=useState({});
    const[accepted,setAccepted]=useState(false);
    const isFormValid=form.name &&form.email &&form.phone &&  form.confirmPsb &&  accepted &&
     form.password &&Object.keys(errors).length === 0;
    const navigate=useNavigate();
    const[otp,setOTP]=useState(["","","",""]);
    const otpRef=useRef([]);
    const[canLogin,setCanLogin]=useState(false);
    
//######################  Form Validation start ######################
  const validate=(data,isaccepted)=>{
const err={}; 
if (!data.email) err.email = "Email required";
  else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(data.email))
    err.email = "Invalid email";

if(!data.name){
    err.name="Name is required";

}else if(data.name.length<2)
{
err.name=" Name is too Short"
}

if(!isaccepted){
    err.accepted="Please agree to terms and conditions before submitting form";

}
 if (!data.phone) {
  err.phone = "Phone number required";
}
else if (!/^[6-9]\d{9}$/.test(data.phone)) {
  err.phone = "Invalid Phone Number...";
}

       if(! data.password){
       err.password="Password required.....";
         }
       else if (data.password.length < 8) {
      err.password="At least 8 characters required";
    }
     else if (!/[a-z]/.test(data.password)) {
      err.password="One lowercase letter required";
    }
      else if (!/[A-Z]/.test(data.password)) {
      err.password="One uppercase letter required";
    }
     else if (!/\d/.test(data.password)) {
      err.password="One number required";
    }
     else if (!/[@$!%*?&]/.test(data.password)) {
      err.password="One special character required";
    }


    if(data.confirmPsb){
    if( data.confirmPsb !== data.password){
        err.confirmPsb="Password you entered does not match"
    }}

return err;
  }
// ############################## Form Validation End ####################################

// ############################## signup start ####################################
     const handleChange=(e)=>{
     const{name,value}=e.target;
    const updated=  {...form,[name]:value};

       setForm(updated);
        setErrors(validate(updated,accepted));
           if(updated.phone.length === 10){
 setModalData({
          type:"success",
           msg:
"To complete your signup, you’ll need to verify your phone number using an OTP.",
title:" Phone Verification Required"
        })
        setShowModal(true);

           }
        
          }

    const handleSubmit= async (e)=>{
      e.preventDefault();
      setLoading(true)
      const{name,email,phone,password}=form;
      try{
        const res=  await axios.post("http://localhost:9090/signup",form);
        setModalData({
          type:"success",
           msg:
"Your account has been created successfully A verification link has been sent to your registered email address Please verify your email to activate your account",
title:"Action Required"
        })
        setShowModal(true);
        setForm({ name:"",email:"",phone:"",password:"",confirmPsb:"" });
        setErrors({});
        setCanLogin(true);
//         setTimeout(() => {
//     navigate("/login");
// }, 3000);
         
           
      }
      catch(err){
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:" Signup Failed",
        })
        setShowModal(true);
         setErrors({});
        } finally {
          setLoading(false);
        }
        
      }
// ############################## signup end ####################################
 

  
// ############################## OTP Handling start ####################################
      const startTimer=()=>{
   
   
const interval=setInterval(() => {
   setTimer((prev)=>{

    if(prev<=1){
      clearInterval(interval);
      setCanReSend(true);
      return 0;
    }
    return prev-1;
   })
}, 1000);}

const sendOTP=async (e)=>{
     e.preventDefault();
     setLoading(true);
   try{
    const res=  await axios.get('http://localhost:9090/send-otp',{params:{phone:form.phone}});
    setIsOTPSent(true);
    startTimer();
    setModalData({
          type:"success",
          msg:
"OTP has been sent to your registered Phone Number Please verify your Phone to Create your account",
title:"Action Required"
        })
        setShowModal(true);
         setErrors({});
      }catch(err) {
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:" Internal Server Error OTP is Not sent",
        })
        setShowModal(true);
         
  }finally{
        setLoading(false);
  }
}
  
   const verifyOTP= async (e)=>{
        e.preventDefault();
        setLoading(true);
        try{
        const res=await axios.get('http://localhost:9090/verify-otp',{
         params:{ phone:form.phone, otp:otp.join("")}
        });
        setOTP(["", "", "", ""]);
        setErrors({});
        setModalData({
          type:"success",
          msg:"OTP Verified Successfully....",
          title:"OTP Verification Update"
        });

        setShowModal(true);
        setIsPhoneVerified(true);
        setCanHide(true)
      }catch(err){
          setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:" OTP Verification Failed ",
        })
        setShowModal(true);
      }finally{
        setLoading(false);
      }
}

const handleOTPChange= (val,index)=>{
  const updatedOTP=[...otp];
  updatedOTP[index]=val.slice(-1);
  setOTP(updatedOTP);

  if(val && index<3){
   otpRef.current[index+1].focus();
  }

  const err={};
  if(!/^\d*$/.test(val)){
      err.otp="OTP Must be Numbers";
      setErrors(err);
  }else{
    setErrors(prev => {
      const newErrors = { ...prev };
      delete newErrors.otp;
      return newErrors;
    });
  }
  if(otp.length < 4){
    err.otp="OTP must be four digits";
      setErrors(err);
  }

  
}

const handlePaste= (e)=>{
  e.preventDefault();
  const pastedData=e.clipboardData.getData("text") .replace(/\D/g, "").slice(0,4);
 if(!pastedData){
     return;
 }
 const otpArray=pastedData.split("");
  const updatedOTP = ["", "", "", ""];
  otpArray.forEach((element,i) => {
    updatedOTP[i]=element;
    }
   
  );
  setOTP(updatedOTP);
  if(otpArray.length-1>0)
  {
      otpRef.current[otpArray.length-1].focus();
  }
}

const handleKeyDown= (e,index)=>{
  if(e.key === 'Backspace' && !otp[index] && index>0){
    otpRef.current[index-1].focus();

  }
}
   // ############################## OTP Handling End ####################################
 

   

    

    return(

        <div className={`${darkmode?"bg-secondary text-light": "bg-light-subtle"}  rounded-3 border-secondary-subtle border p-0 container mt-4 custom-width`} >
            <div className="row  h-100">

{/* ############################## Modal Notification start ############################## */}
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
  darkmode={darkmode}
     />
{/* ############################## Modal Notification End ############################## */}



{/* ############################### signup form Start  ###########################################3 */}
               <div className="col-12 ">
                <div className={`card shadow-sm p-3 w-100 h-100 ${darkmode ? "bg-dark text-light" : ""}`}>
                    
                    <form action="" onSubmit={handleSubmit}>
                        <div className="text-center mb-2">
                            <button className={`btn  btn-lg ${darkmode ? "btn-outline-light" : "btn-primary"}`}>Signup Form</button>
                        </div>

                        <div className="mb-3">
<label htmlFor="name" className="form-label"> Name</label>
  <input type="text"  onChange={handleChange}name="name" value={form.name} 
   className={`form-control ${errors.name && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`}id="name" placeholder="Enter Your fullname" required></input>
{errors.name && <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>{errors.name}</div>}
</div>

<div className="mb-3">
  <label htmlFor="email" className="form-label">Email</label>
  <input  required type="email"
   onChange={handleChange}name="email" value={form.email} 
   className={`form-control ${errors.email && "is-invalid"}  ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`}
 id="email" placeholder="Enter your Email"></input>
 {errors.email && <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>{errors.email}</div>}

</div>

<div className="mb-3">
  <label htmlFor="password" className="form-label">Password</label>
  <input  required type="password" onChange={handleChange}name="password" value={form.password} 
   className={`form-control ${errors.password && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} 
  id="password" placeholder="Enter Strong Password"></input>
  {errors.password && <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>{errors.password}</div>}

</div>


<div className="mb-3">
  <label htmlFor="confirmpsb" className="form-label">Confirm Password</label>
  <input placeholder="Confirm Your Password" 
  required 
  onChange={handleChange} name="confirmPsb" value={form.confirmPsb} 
   className={`form-control ${errors.confirmPsb && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} id="confirmpsb" ></input>

{errors.confirmPsb && <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>{errors.confirmPsb}</div>}
</div>

{/* #################################### Phone Number start ################################### */}
<div className="mb-3">
  
  <label htmlFor="phone" className="form-label">Phone</label>

 
   
   <div className="row">

    <div className={`${ form.phone.length === 10 && !isPhoneVerified ?!canResend?"col-4 col-xxl-7": "col-xxl-9 col-6" :"col-12"}`}>
<input  required type="text"
  onChange={handleChange}name="phone" value={form.phone} 
  className={`form-control ${errors.phone && "is-invalid"}  ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} id="phone" placeholder="Enter 10 digit phone number"
 readOnly={isPhoneVerified}
></input>

    </div>

{ !isPhoneVerified && form.phone.length === 10 && !errors.phone &&(
      <div className={`${!canResend ? " col-7 col-xxl-5":"col-xxl-3 col-5"}`}>
     
  {
    !isPhoneVerified && form.phone.length === 10 && isOTPSent ?
    (
    <button  type="button"
    disabled={isOTPSent && !canResend}
    onClick={sendOTP}
    className={`btn text-white  ${darkmode ? "btn-secondary" :"btn-primary"}`}
    >
   {loading ? (
    <>
      <span className="spinner-border spinner-border-sm me-2"></span>
      Resending...
    </>
  ) : (
    "Resend OTP"
  )}
    { !canResend && ` in ${timer} S`}
    </button>)
    :
    (
    <button  onClick={sendOTP} type="button"className={`ms-1 btn  ${darkmode ? "btn-secondary" :"btn-primary"}`}>

      {loading ? (
    <>
      <span className="spinner-border spinner-border-sm me-2"></span>
      Sending...
    </>
  ) : (
    "Send OTP"
  )}
     
    </button>)
    }
   </div>  
      )
}
    
   </div>
  



{errors.phone && (
  <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>
    {errors.phone}
  </div>
)}

   
      <div className={`row mt-3 d-flex align-items-center ${canhide && "d-none"} ${!isPhoneVerified && !isOTPSent && "d-none"}`}>

       <div className="col-xxl-9 col-10">
        <label className="d-inline-block me-2" htmlFor="otp">Enter OTP</label>
        <div id="otp" className="d-inline">
          {
          otp.map((val,index)=>
          
         <input key={index} type="text" maxLength={1} style={{width:"34px",height:"33px"}} 
 value={val} 
 ref={((el)=>otpRef.current[index]=el)}
 className={`ms-1  form-control d-inline ${darkmode ?"bg-dark text-light border-secondary placeholder-light" : ""}`} 
         onChange={(e)=>handleOTPChange(e.target.value,index)}
         onKeyDown={(e)=>handleKeyDown(e,index)}
         onPaste={handlePaste}   
         />
 
          )}

       </div>
     </div>
     <div className={`col-xxl-3 col-5 ${!canResend && "mt-4"}`}>
    <button type="button"  onClick={verifyOTP}
     className={`btn btn-md  ${darkmode ? "btn-outline-light":"btn-primary"}`}>
      {loading ? (
    <>
      <span className="spinner-border spinner-border-sm me-2"></span>
      Verifying OTP...
    </>
  ) : (
    "Verify OTP"
  )}
      
      </button></div>
     </div>
 {errors.otp && (
  <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small`}>
    {errors.otp}
  </div>
)}
       {isPhoneVerified && (
    <div className={`${darkmode ?"text-light":"text-success"} mt-2 fw-semibold`}>
      Phone Verified Successfully ✓
    </div>
  )}
</div>
{/* #################################### Phone Number end ################################### */}


<div className="form-check mb-3">
  <input
  type="checkbox"
  className={`form-check-input ${errors.accepted && "is-invalid"} ${darkmode ?"bg-dark text-light border-secondary placeholder-light" : ""}`}
  id="terms"
  checked={accepted}
  onChange={(e) => {
  const checked = e.target.checked;
  setAccepted(checked);
  setErrors(validate(form, checked));
}
  }
/>
  <label className="form-check-label" htmlFor="terms">
    I agree to the{" "}
    <button  type="button" className={`btn ${darkmode?"btn-secondary":"btn-primary"} btn-sm`}   onClick={()=>navigate("/terms")} target="_blank">
      Terms & Conditions
    </button>
  </label>
  {errors.accepted && (
  <div className={` ${darkmode ?"error-light":"text-danger"} mt-1 small d-block`}>
    {errors.accepted}
  </div>
)}
</div>

    <div className="d-flex justify-content-center"
  title={!isFormValid ? "Please fill all fields correctly" : ""}
  style={{ display: "inline-block" }}
>
  <button
  disabled={!isFormValid || loading || !isPhoneVerified}
  type="submit" 
  className={`btn mt-2 btn-lg ${
    darkmode ? "btn-secondary" : "btn-primary"
  } ${( !isFormValid || loading ) ? "opacity-50" : ""}`}
>
  {loading ? (
    <>
      <span className="spinner-border spinner-border-sm me-2"></span>
      Submitting...
    </>
  ) : (
    "Submit"
  )}
</button>


</div>
    </form>  
      </div></div>
{/* ############################### signup form end  ###########################################3 */}

    </div>
</div>
   
    );
  }
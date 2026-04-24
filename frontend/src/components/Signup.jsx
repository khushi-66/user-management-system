import { useContext ,useState} from "react";
import { themeContext } from "../components/ThemeProvider";
import './signup.css';
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';
import Modal from '../components/Modal';
export default function Signup(){
     const[isphoneverified,setIsOtpVerififed]=useState(false);
     const[isOTPSent,setIsOTPSent]=useState(false);



     const[showmodal,setShowModal]=useState(false);
     const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:""
     });
     const [termsError, setTermsError] = useState("");
    const {darkmode}=useContext(themeContext);
    const[form,setForm]=useState({ name:"",email:"",phone:"",password:"",confirmPsb:""});
    const[loading,setLoading]=useState(false);
    const[errors ,setErrors]=useState({});
const[accepted,setAccepted]=useState(false);
const isFormValid=form.name &&form.email &&form.phone &&  form.confirmPsb &&  accepted &&
  form.password &&Object.keys(errors).length === 0;
  
   const navigate=useNavigate()
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
 if (data.phone && !/^[6-9]\d{9}$/.test(data.phone)) {
   err.phone="Invalid Phone Number...";
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

    const handleChange=(e)=>{
     const{name,value}=e.target;
    const updated=  {...form,[name]:value};
       setForm(updated);
        setErrors(validate(updated));
          }

    

    const handleSubmit= async (e)=>{
      e.preventDefault();
      setLoading(true)
      const{name,email,phone,password}=form;
      try{
        const res=  await axios.post("http://localhost:9090/contact",form);
        setModalData({
          type:"success",
           msg:
"Your account has been created successfully A verification link has been sent to your registered email address Please verify your email to activate your account",
title:"Action Required"
        })
        setShowModal(true);
        setForm({ name:"",email:"",phone:"",password:"" });
        setErrors({});
      }
      catch(err){
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:" Internal Server Error",
        })
        setShowModal(true);
         setErrors({});
        } finally {
          setLoading(false);
        }
        
      }


    

    return(

        <div className={`${darkmode?"bg-secondary text-light": "bg-light-subtle"}  rounded-3 border-secondary-subtle border p-0 container mt-4 custom-width`} >
            <div className="row  h-100">

{/* ############################## Modal Notification start ############################## */}
     <Modal
     show={showmodal}
  onClose={()=>setShowModal(false)}
  title={modaldata.title}
  message={modaldata.msg}
  type = {modaldata.type}
  darkmode={darkmode}
     />
{/* ############################## Modal Notification end ############################## */}



{/* ############################### signup form end  ###########################################3 */}
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
{errors.name && <div className="invalid-feedback">{errors.name}</div>}
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
 {errors.email && <div className="invalid-feedback">{errors.email}</div>}

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
  {errors.password && <div className="invalid-feedback">{errors.password}</div>}

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
{errors.confirmPsb && <div className="invalid-feedback">{errors.confirmPsb}</div>}
</div>

{/* #################################### Phone Number start ################################### */}
<div className="mb-3">
  <label htmlFor="phone" className="form-label">Phone</label>
  <input  required type="text"
  onChange={handleChange}name="phone" value={form.phone} 
  className={`form-control ${errors.phone && "is-invalid"}  ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} id="phone" placeholder="Enter 10 digit phone number"></input>
{errors.phone && <div className="invalid-feedback">{errors.phone}</div>}

   <div className="row mt-3 d-flex align-items-center">

       <div className="col-9 ">
        <p className="d-inline-block me-2">Enter OTP</p>
        <input type="text" maxLength={1} style={{width:"34px",height:"30px"}} className={` ms-1 form-control d-inline ${darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""}`} />
        <input type="text" style={{width:"34px",height:"30px"}} className="ms-1 form-control d-inline" />
        <input type="text" style={{width:"34px",height:"30px"}} className="ms-1 form-control d-inline" />
        <input type="text" style={{width:"34px",height:"30px"}} className=" ms-1 form-control d-inline" />
     </div>
     <div className="col-xxl-3 col-5">
    <button className={`btn btn-md  ${darkmode ? "btn-outline-light":"btn-primary"}`}>Verifiy OTP</button></div>
     </div>

     
</div>
{/* #################################### Phone Number end ################################### */}





<div className="form-check mb-3">
  <input
  type="checkbox"
  className={`form-check-input ${errors.accepted && "is-invalid"}`}
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
  <div className="invalid-feedback d-block">
    {errors.accepted}
  </div>
)}
</div>

    <div className="d-flex justify-content-center"
  title={!isFormValid ? "Please fill all fields correctly" : ""}
  style={{ display: "inline-block" }}
>
  <button
  disabled={!isFormValid || loading}
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
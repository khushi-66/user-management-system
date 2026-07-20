import { Link, useNavigate, useParams, useSearchParams } from "react-router-dom";
import lightLogo from "../assets/light-logo.png";
import Modal from "../components/Modal";
import { useState } from "react";
import axios from "axios";
export default function ChangePassword() {

const[showmodal,setShowModal]=useState(false);
const[searchParams]=useSearchParams();
 const token=searchParams.get("token");
const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:"" });
   const [password,setPassword] =useState();
const [confirmpassword,setConfirmPassword] =useState();
const[loading,setLoading]=useState(false);
const navigate=useNavigate();
const handleSubmit=async(e)=>{
   e.preventDefault();
   setLoading(true);
   try{
   const resp= await axios.post("http://localhost:9090/update-password",{
     password:password,token:token
   });

   if(resp.data.Status ==='invalid'){
      navigate("/invalid-token");
   }
   if(resp.data.Status ==='expired'){
      navigate("/expired-token");
   }
   console.log(resp.data.Status);
    if( resp.data.Status ==='success'){
      setPassword("");
      setConfirmPassword("");
   setModalData({
  type:"success",msg:"✅ Password updated successfully. Please login using your new password.",title:"Password updated"
                    });
                    setShowModal(true);
                  }

  }catch(err){
    setModalData({
                        type:"error",msg:"Something went wrong. Please try again later.",title:"Password Reset failed"
                    });
                    setShowModal(true);
  }finally{
    setLoading(false);
  }
}
    const validatePassword=async(field,e)=>{
            if(field ==='password'){
                if(!e.trim()  ){
                    setModalData({
                        type:"error",msg:"password cannot be empty please enter strong password  to reset password ",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
                  }
                  else if (e.length < 8) {
      
                       setModalData({
                        type:"error",msg:"At least 8 characters required" ,title:"validation error"
                    });
                    setShowModal(true);
                    return false;

    }
     else if (!/[a-z]/.test(e)) {
      setModalData({
                        type:"error",msg:"One lowercase letter required",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
    }
      else if (!/[A-Z]/.test(e)) {
      
      setModalData({
                        type:"error",msg:"One uppercase letter required",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
    }
     else if (!/\d/.test(e)) {
      setModalData({
                        type:"error",msg:"One number required",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
    }
     else if (!/[@$!%*?&]/.test(e)) {
      setModalData({
                        type:"error",msg:"One special character required",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
    }
      }

      if(field ==='confirmpassword'){

        if(!e.trim()  ){
                    setModalData({
                        type:"error",msg:"Please confirm password to update password ",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
                  }

                  

              if(password !== e ){
                    setModalData({
                        type:"error",msg:"Password you entered doesnot match ",title:"validation error"
                    });
                    setShowModal(true);
                    return false;
                  }
      }

          
   }

  return (
    <div  style={{ maxWidth: "460px", width: "100%" }} className="align-items-center container mt-5 d-flex justify-content-center">
      <Modal
           show={showmodal}
        onClose={()=>{setShowModal(false)
            if(modaldata.type === 'success'){
              navigate("/login");
            }
         }}
        title={modaldata.title}
        message={modaldata.msg}
        type = {modaldata.type}
        darkmode={false}
           />
      
      <div className="card shadow p-4 pt-0" style={{ width: "100%" }}>
         <Link  className={`ps-0   mb-0 navbar-brand d-flex align-items-center`}>
          <img
            style={{height:"50px"} }
            src={lightLogo}
            alt="Logo"
          />
          
        </Link>
        <h3 className="text-center text-primary fw-bold">
    Change Password
</h3>

<p className="text-center text-muted">
    Create a new strong password to secure your account.
</p>
        <form  onSubmit={handleSubmit}>
          <div className="mb-3 row align-items-center">
            <label htmlForm="psb" className="col-sm-4 col-form-label">
              New Password
            </label>

            <div className="col-sm-8">
              <input
                type="password" value={password} 
                onChange={(e)=>{setPassword(e.target.value)}} 
                onBlur={(e)=>{validatePassword("password",e.target.value)}}
                className="form-control" name="psb"
                placeholder="Enter Strong password"
              />
            </div>
          </div>

          <div className="mb-3 row align-items-center">
            <label className="col-sm-4 col-form-label" htmlFor="confirmpsb">
               Confirm Password
            </label>

            <div className="col-sm-8">
              <input name="confirmpsb"
                type="password" value={confirmpassword} 
                onChange={(e)=>{setConfirmPassword(e.target.value)}} 
                onBlur={(e)=>{validatePassword("confirmpassword",e.target.value)}}
                className="form-control"
                placeholder="Confirm  password"
              />
            </div>
          </div>

          <div className="text-center">
            <button disabled={loading} type="submit" className="btn btn-primary">
               {loading ? (
        <>
            <span className="spinner-border spinner-border-sm me-2"></span>
            Updating...
        </>
    ) : (
        "Update Password"
    )}
            </button>
          </div>

        </form>
        <div className="alert alert-light mt-3">
    <small>
        <b>Password must contain:</b>
        <ul className="mb-0 mt-2">
            <li>Minimum 8 characters</li>
            <li>One uppercase letter</li>
            <li>One lowercase letter</li>
            <li>One number</li>
            <li>One special character</li>
        </ul>
    </small>
</div>
      </div>
    </div>
  );
}
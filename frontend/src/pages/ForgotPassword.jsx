import { Link } from "react-router-dom";
import lightLogo from "../assets/light-logo.png";
import Modal from "../components/Modal";
export default function ForgotPassword() {
const[showmodal,setShowModal]=useState(false);
const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:"" });

  return (
    <div className="container mt-5 d-flex justify-content-center align-items-center">
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
      
      <div className="card shadow p-4 pt-0" style={{ width: "450px" }}>
         <Link  className={`ps-0   mb-0 navbar-brand d-flex align-items-center`}>
          <img
            style={{height:"50px"} }
            src={lightLogo}
            alt="Logo"
          />
          
        </Link>
        <h3 className="text-center mb-4">Forgot Password</h3>

        <form>
          <div className="mb-3 row align-items-center">
            <label className="col-sm-4 col-form-label">
              New Password
            </label>

            <div className="col-sm-8">
              <input
                type="password"
                className="form-control"
                placeholder="Enter New Password"
              />
            </div>
          </div>

          <div className="text-center">
            <button type="submit" className="btn btn-primary">
              Change Password
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
import { useContext ,useState} from "react";
import { themeContext } from "../components/ThemeProvider";

import axios from 'axios';
import Modal from '../components/Modal';
export default function Contact(){
     const[showmodal,setShowModal]=useState(false);
     const[modaldata,setModalData]=useState({
         type:"success",msg:"",title:""
     });
    const {darkmode}=useContext(themeContext);
    const[form,setForm]=useState({ name:"",email:"",phone:"",message:"" ,subject:"" });
    const[loading,setLoading]=useState(false);
    const[errors ,setErrors]=useState({});
const isFormValid=form.name &&form.email &&form.phone &&
  form.subject && form.message &&Object.keys(errors).length === 0;
     
  const validate=(data)=>{
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

 if (data.phone && !/^[6-9]\d{9}$/.test(data.phone)) {
   err.phone="Invalid Phone Number...";
}
if(! data.subject)
{
err.subject="Subject required.....";
}

if (!data.message) {
  err.message = "Message required";
} else if (data.message.length < 10) {
  err.message = "Minimum 10 characters";
}
return err;
  }

    const handleChange=(e)=>{
     const updated=  {...form,[e.target.name]:e.target.value};
     setForm(updated);
        setErrors(validate(updated));
    }

    const handleSubmit= async (e)=>{

      e.preventDefault();
      setLoading(true)
      try{
        const res=  await axios.post("http://localhost:9090/contact",form);
        setModalData({
          type:"success", msg:"Thank you for reaching out! We have received your message and will get back to you as soon as possible.",title:"Message Sent Successfully"
        })
        setShowModal(true);
        setForm({ name:"",email:"",phone:"",message:"",subject:"" });
        setErrors({});
      }
      catch(err){
        setModalData({
          type:"error", msg:err.response?.data?.message || "Something went wrong",title:"Submission Limit Reached",
        })
        setShowModal(true);
         setErrors({});
        } finally {
          setLoading(false);
        }
        
      }


    

    return(

        <div className={`${darkmode?"bg-secondary text-light": "bg-light-subtle"}  rounded-3 border-secondary-subtle border p-4 container mt-4`}>
            <div className="row g-4 h-100 w-100 d-flex">

{/* ############################## Modal Notitfication start ############################## */}
     <Modal
     show={showmodal}
  onClose={()=>setShowModal(false)}
  title={modaldata.title}
  message={modaldata.msg}
  type = {modaldata.type}
  darkmode={darkmode}
     />
{/* ############################## Modal Notitfication end ############################## */}



{/* ############################### Left side end  ###########################################3 */}
               <div className="col-12 col-md-6">
                <div className={`card shadow-sm p-4 w-100 h-100 ${darkmode ? "bg-dark text-light" : ""}`}>
                    
                    <form action="" onSubmit={handleSubmit}>
                        <div className="text-center mb-2">
                            <button className={`btn  btn-lg ${darkmode ? "btn-outline-light" : "btn-primary"}`}>Contact Us</button>
                        </div>

                        <div className="mb-3">
<label htmlFor="name" className="form-label"> Name</label>
  <input type="text"  onChange={handleChange}name="name" value={form.name} 
   className={`form-control ${errors.name && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`}id="name" placeholder="mahi soni" required></input>
{errors.name && <div className="invalid-feedback">{errors.name}</div>}
</div>

<div className="mb-3">
  <label htmlFor="phone" className="form-label">Phone</label>
  <input  required type="text"
  onChange={handleChange}name="phone" value={form.phone} 
  className={`form-control ${errors.phone && "is-invalid"}  ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} id="phone" placeholder="8345673450"></input>
{errors.phone && <div className="invalid-feedback">{errors.phone}</div>}
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
 id="email" placeholder="name@example.com"></input>
 {errors.email && <div className="invalid-feedback">{errors.email}</div>}

</div>

<div className="mb-3">
  <label htmlFor="subject" className="form-label">Subject</label>
  <input  required type="text" onChange={handleChange}name="subject" value={form.subject} 
   className={`form-control ${errors.subject && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} 
  id="subject" placeholder="e.g. Login Issue, Feature Request, Collaboration"></input>
  {errors.subject && <div className="invalid-feedback">{errors.subject}</div>}

</div>


<div className="mb-3">
  <label htmlFor="msg" className="form-label">Message</label>
  <textarea placeholder="Type Your Message Here ...." 
  required 
  onChange={handleChange} name="message" value={form.message} 
   className={`form-control ${errors.message && "is-invalid"} ${
  darkmode 
    ? "bg-dark text-light border-secondary placeholder-light" 
    : ""
}`} id="msg" rows="3"></textarea>
{errors.message && <div className="invalid-feedback">{errors.message}</div>}
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
      Sending...
    </>
  ) : (
    "Submit"
  )}
</button>


</div>
    </form>  
      </div></div>
{/* ############################### Left side end  ###########################################3 */}
 

{/* ############################### right side start ###########################################3 */}
               <div className="col-12 col-md-6">
                 <div className={`card shadow-sm p-4 w-100 h-100 ${darkmode ? "bg-dark text-light" : ""}`}>
                     <h4 className="mb-3 mt-1">Reach Us</h4>
                     {/* PHONE */}
    <p>
      <i className="bi bi-telephone"></i> +91 9876543210
    </p>
{/* EMAIL */}
    <p className={`btn w-100 mt-2 ${darkmode ? "btn-outline-light" : "btn-primary"}`}>
      <i className="bi bi-envelope"></i> sahujii8277@gmail.com
    </p>
{/* ADDRESS */}
    <p>
      <i className="bi bi-geo-alt"></i> Jabalpur, Madhya Pradesh, India
    </p>

{/* BUTTONS */}
    <a href="tel:+919876543210" className={`btn w-100 mt-2 ${
  darkmode ? "btn-outline-success" : "btn-success"
}`}>
      Call Support 📞
    </a>
    <a 
      href="https://mail.google.com/mail/?view=cm&to=sahujii8277@gmail.com"
      target="_blank"
      className={`btn w-100 mt-2 ${
  darkmode ? "btn-outline-light" : "btn-primary"}`}>
      Email Us 📧
    </a>
                         <a  href="https://www.google.com/maps/place/Madhotal/@23.197235,79.9048431,17z/data=!3m1!4b1!4m6!3m5!1s0x3981b1cd79d0244b:0x4297fcccf7310612!8m2!3d23.197235!4d79.907418!16s%2Fg%2F11g0hmws8y?entry=ttu&g_ep=EgoyMDI2MDQxNS4wIKXMDSoASAFQAw%3D%3D"
      target="_blank"
      className={`btn w-100 mt-2 ${
  darkmode ? "btn-outline-secondary text-light" : "btn-secondary"
}`}> View Location 📍</a>
{/* Map..... */}
                    <div className= "mt-2 mb-0 pb-0 rounded" style={{height:"340px"}}>
                     <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3667.2588621281575!2d79.90484307509733!3d23.19723497905174!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3981b1cd79d0244b%3A0x4297fcccf7310612!2sMadhotal!5e0!3m2!1sen!2sin!4v1776582310701!5m2!1sen!2sin" 
                     
                     style={{ border: 0, width: "100%", height: "100%" }}
                     allowFullScreen="" loading="lazy" 
                     referrerPolicy="no-referrer-when-downgrade">
                        </iframe></div>
                    </div>   
                </div>
{/* ##################################### right side  end ##############################            */}
                   

    </div>
</div>
   
    );
  }
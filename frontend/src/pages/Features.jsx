import { useContext } from "react"
import { themeContext } from "../components/ThemeProvider"


export default function Features() {

  const { darkmode } = useContext(themeContext);

  const cardClass = `card h-100 p-3 text-center shadow-sm ${
    darkmode ? "bg-secondary text-light" : ""
  }`;

  return (
    <div className={darkmode ? "bg-dark text-light min-vh-100" : "bg-light text-dark min-vh-100"}>

      <div className="container py-5 mt-auto">

        {/* HEADER */}
        <div className="text-center mb-5">
          <h1>System Features</h1>
          <p>Explore all functionalities of the User Management System</p>
        </div>

        {/* USER FEATURES */}
        <h3 className="mb-3">👤 User Features</h3>
        <div className="row g-4 mb-5">

          {[
            {icon:"bi-person-plus", title:"Signup", desc:"Register with OTP verification"},
            {icon:"bi-phone", title:"Phone OTP", desc:"Verify phone number securely"},
            {icon:"bi-envelope-check", title:"Email Verification", desc:"Activate account"},
            {icon:"bi-key", title:"Login", desc:"JWT based secure login"},
            {icon:"bi-arrow-repeat", title:"Forgot Password", desc:"Reset via OTP"},
            {icon:"bi-person-circle", title:"Profile", desc:"Update profile details"},
            {icon:"bi-box-arrow-right", title:"Logout", desc:"Secure logout"},
            {icon:"bi-trash", title:"Delete Account", desc:"Permanent deletion option"},
          ].map((f,i)=>(
            <div className="col-12 col-md-6 col-lg-3" key={i}>
              <div className={cardClass}>
                <i className={`bi ${f.icon} fs-1 text-primary`}></i>
                <h6 className="mt-2">{f.title}</h6>
                <p>{f.desc}</p>
              </div>
            </div>
          ))}

        </div>

        {/* SECURITY */}
        <h3 className="mb-3">🔐 Security Features</h3>
        <div className="row g-4 mb-5">

          {[
            {icon:"bi-shield-lock", title:"JWT Auth"},
            {icon:"bi-lock", title:"Secure APIs"},
            {icon:"bi-person-lock", title:"Role-Based Access"},
            {icon:"bi-shield-check", title:"Protected Routes"},
          ].map((f,i)=>(
            <div className="col-12 col-md-6 col-lg-3" key={i}>
              <div className={cardClass}>
                <i className={`bi ${f.icon} fs-1 text-success`}></i>
                <h6 className="mt-2">{f.title}</h6>
              </div>
            </div>
          ))}

        </div>

        {/* ADMIN */}
        <h3 className="mb-3">🧑‍💼 Admin Features</h3>
        <div className="row g-4 mb-5">

          {[
            {icon:"bi-people", title:"View Users"},
            {icon:"bi-person-x", title:"Block User"},
            {icon:"bi-person-check", title:"Activate User"},
            {icon:"bi-journal-text", title:"Audit Logs"},
          ].map((f,i)=>(
            <div className="col-12 col-md-6 col-lg-3" key={i}>
              <div className={cardClass}>
                <i className={`bi ${f.icon} fs-1 text-warning`}></i>
                <h6 className="mt-2">{f.title}</h6>
              </div>
            </div>
          ))}

        </div>

        {/* ANALYTICS */}
        <h3 className="mb-3">📊 Analytics (Admin Only)</h3>
        <div className="row g-4">

          {[ {icon:"bi-pencil-square", title:"CRUD Operations"},
            {icon:"bi-trash-fill", title:"Bulk Delete"},
            {icon:"bi-people-fill", title:"Total Users"},
            {icon:"bi-person-check-fill", title:"Active Users"},
            {icon:"bi-person-x-fill", title:"Blocked Users"},
            {icon:"bi-person-dash-fill", title:"Inactive Users"},
          ].map((f,i)=>(
            <div className="col-12 col-md-6 col-lg-3" key={i}>
              <div className={cardClass}>
                <i className={`bi ${f.icon} fs-1 text-danger`}></i>
                <h6 className="mt-2">{f.title}</h6>
              </div>
            </div>
          ))}

        </div>
        <h3 className="mb-3">🔍 Data Management</h3>
<div className="row g-4 mb-5">

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-search fs-1 text-primary"></i>
      <h6>Search</h6>
      <p>Find users by name, email or phone</p>
    </div>
  </div>

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-sort-alpha-down fs-1 text-success"></i>
      <h6>Sorting</h6>
      <p>Sort users by name, date or status</p>
    </div>
  </div>

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-funnel fs-1 text-warning"></i>
      <h6>Filtering</h6>
      <p>Filter users based on role and status</p>
    </div>
  </div>

</div>
<h3 className="mb-3">🎨 UI Features</h3>
<div className="row g-4 mb-5">

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-moon fs-1 text-info"></i>
      <h6>Theme Toggle</h6>
      <p>Switch between dark and light mode</p>
    </div>
  </div>

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-phone fs-1 text-info"></i>
      <h6>Responsive Design</h6>
      <p>Optimized for mobile and desktop</p>
    </div>
  </div>

  <div className="col-md-4">
    <div className={cardClass}>
      <i className="bi bi-grid fs-1 text-info"></i>
      <h6>Reusable Components</h6>
      <p>Modular and maintainable UI</p>
    </div>
  </div>

</div>
      </div>
    </div>
  );
}
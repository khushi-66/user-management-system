import axios from "axios";
import { useEffect, useState } from "react";

 export default function Activity() {
  const[activities,setActivities]=useState([]);
  const user= JSON.parse(localStorage.getItem("user"));
  const token=localStorage.getItem('token');

  const formatDate = (dateTime) => {
        if (!dateTime) return "";

        const date = new Date(dateTime);

        return date.toLocaleDateString("en-US", {
            weekday: "long",
            month: "long",
            day: "numeric",
            year: "numeric"
        });
    };

    const formatTime = (dateTime) => {
        if (!dateTime) return "";

        const date = new Date(dateTime);

        return date.toLocaleTimeString("en-US", {
            hour: "2-digit",
            minute: "2-digit",
            hour12: true
        });
    };
const fetchActivity=async()=>{
  try{
    const response=await axios.get(`http://localhost:9090/activities/${user.id}`,  {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
               
        
        
     if(response.data.status==='successfull'){
          setActivities(   response.data.activities);
        
          
     }
  }catch(error){
    console.error('Error fetching activity:',error);
  }};

  useEffect(()=>{
    
    fetchActivity();
    
  },activities);

  return (
   
        <div className="container-fluid mt-5">

            {activities.length === 0 ? (

                <div className="text-center">
                    <h3>No Activities available</h3>
                </div>

            ) : (

                <div>

                    {activities.map((activity, index) => (

                        <div
                            key={activity.logId || index}
                            className="card mb-3 border-0 shadow-sm"
                        >

                            <div className="card-body">

                                <div className="d-flex justify-content-between align-items-start">

                                    {/* Left Side */}
                                    <div className="pe-3">

                                        <h5 className="fw-bold text-primary mb-2">
                                            <i className="bi bi-clock-history me-2"></i>
                                            {activity.action}
                                        </h5>

                                        <p className="card-text text-muted mb-0">
                                            {activity.description}
                                        </p>

                                    </div>

                                    {/* Right Side */}
                                    <div className="text-end flex-shrink-0">

                                        <div className="fw-semibold text-secondary">
                                            {formatDate(activity.time)}
                                        </div>

                                        <div className="small text-muted mt-1">
                                            {formatTime(activity.time)}
                                        </div>

                                    </div>

                                </div>

                            </div>

                        </div>

                    ))}

                </div>
            )}

        </div>
    );
}


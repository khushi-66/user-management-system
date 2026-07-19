import { useState } from "react";

export default function Userdashboard (){
   const user= JSON.parse(localStorage.getItem("user"));
   const token= localStorage.getItem("token");
    console.log(user);
console.log(token);
    return(
        <>
         <h1  className="text-center">Welcome {user.name} !!</h1>
        </>
    )
}
import { createContext, useEffect,useState } from "react";

export  const themeContext=createContext();

export default function ThemeProvider({children})
{
const[darkmode,setDarkMode]=useState(()=>{
    return localStorage.getItem("theme"==="dark")
})

useEffect(()=>{
    localStorage.setItem("theme",darkmode?"dark":"light")
},[darkmode])
 return(
<themeContext.Provider value={{darkmode,setDarkMode}}>
{children}
</themeContext.Provider>
 ) ;  
}